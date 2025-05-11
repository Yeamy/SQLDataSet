package yeamy.sql.ds;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * The factory to generate java bean,
 * add custom types' adapter to create fields value.
 * You can cache the factory to avoid too much reflection.
 */
class InternalDsFactory<T> {
    protected final Class<T> type;
    private Constructor<T> constructor;
    private static final Unsafe unsafe;
    private static final boolean java9;

    static {
        boolean b = true;
        try {
            java.lang.invoke.MethodHandles.lookup();
        } catch (Exception ignored) {
            b = false;
        }
        java9 = b;
        Unsafe theUnsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            theUnsafe = (Unsafe) f.get(null);
        } catch (Exception ignored) {
        }
        unsafe = theUnsafe;
    }

    /**
     * @param type type to generate
     */
    InternalDsFactory(Class<T> type) {
        this.type = type;
        if (unsafe != null) {
            try {
                this.constructor = type.getDeclaredConstructor();
            } catch (Exception ignored2) {
            }
        }
    }

    private boolean newApi(boolean multiple) {
        return multiple && java9;
    }

    private DsField createDsField(Field field, ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap,
                                  boolean newApi) throws ReflectiveOperationException {
        if (field.isAnnotationPresent(DsIgnore.class)) {
            return null;
        }
        DsType dsType = DsType.getDsType(field);
        if (dsType == DsType.Extra) {// extra type
            if (fieldMap != null) {
                DsFieldReader<?> reader = fieldMap.get(field.getType());
                if (reader != null) {
                    return newApi
                            ? DsReaderField9.create(type, field, rs, reader)
                            : DsReaderField.create(field, rs, reader);
                }
            } else if (field.isAnnotationPresent(DsColumn.class)) {// cannot read
                return null;
            }
            return newApi
                    ? DsExtendField9.create(type, field, rs, fieldMap)
                    : DsExtendField.create(field, rs, fieldMap);
        } else if (dsType == DsType.Enum) {
            return newApi
                    ? DsEnumField9.create(field, rs)
                    : DsEnumField.create(field, rs);
        } else {// base type
            return newApi
                    ? DsBaseField9.create(type, field, dsType, rs)
                    : DsBaseField.create(field, dsType, rs);
        }
    }

    List<DsField> createDsFields(ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap,
                                 boolean multiple) throws ReflectiveOperationException {
        boolean newApi = newApi(multiple);
        ArrayList<DsField> list = new ArrayList<>();
        Field[] fields = type.getFields();
        for (Field field : fields) {
            DsField f = createDsField(field, rs, fieldMap, newApi);
            if (f != null) {
                f.setAccessible();
                list.add(f);
            }
        }
        list.sort(Comparator.comparingInt(DsField::sortInt));
        return list;
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    private T createInstance() throws ReflectiveOperationException {
        if (unsafe != null) {
            return (T) unsafe.allocateInstance(type);
        } else if (constructor != null) {
            return constructor.newInstance();
        } else {
            return type.newInstance();
        }
    }

    T read(ResultSet rs, List<DsField> list) throws SQLException, ReflectiveOperationException {
        T t = createInstance();
        for (DsField i : list) {
            i.read(rs, t);
        }
        if (t instanceof DsObserver) {
            ((DsObserver) t).onDsFinish();
        }
        return t;
    }

    T read(ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap) throws SQLException, ReflectiveOperationException {
        List<DsField> list = createDsFields(rs, fieldMap, false);
        if (rs.next()) {
            return read(rs, list);
        }
        return null;
    }

    void readArray(ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap, Collection<T> out, int limit) throws SQLException, ReflectiveOperationException {
        List<DsField> list = createDsFields(rs, fieldMap, true);
        while (rs.next()) {
            if (limit-- <= 0) {
                break;
            }
            out.add(read(rs, list));
        }
    }

    void readArray(ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap, Collection<T> out) throws SQLException, ReflectiveOperationException {
        List<DsField> list = createDsFields(rs, fieldMap, true);
        while (rs.next()) {
            out.add(read(rs, list));
        }
    }

}
