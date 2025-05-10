package yeamy.sql.ds;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The factory to generate java bean,
 * add custom types' adapter to create fields value.
 * You can cache the factory to avoid too much reflection.
 */
public class InternalDsFactory<T> implements DsFactory<T> {
    protected final Class<T> type;
    private Unsafe unsafe;
    private Constructor<T> constructor;

    /**
     * @param type type to generate
     */
    public InternalDsFactory(Class<T> type) {
        this.type = type;
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            this.unsafe = (Unsafe) f.get(null);
        } catch (Exception ignored) {
            try {
                this.constructor = type.getDeclaredConstructor();
            } catch (Exception ignored2) {
            }
        }
    }

    private boolean newApi(boolean multiple) {
        if (multiple) {
            try {
                Class.forName("java.lang.invoke.MethodHandles");
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    DsField createDsField(Field field, ResultSet rs, boolean newApi) throws ReflectiveOperationException {
        if (field.isAnnotationPresent(DsIgnore.class)) {
            return null;
        }
        DsType dsType = DsType.getDsType(field);
        if (dsType == DsType.Extra) {// extra type
            if (field.isAnnotationPresent(DsColumn.class)) {// cannot read
                return null;
            } else {
                return newApi ? DsExtendField9.create(type, field, rs)
                        : DsExtendField.create(field, rs);
            }
        } else if (dsType == DsType.Enum) {
            return newApi ? DsEnumField9.create(field, rs)
                    : DsEnumField.create(field, rs);
        } else {// base type
            return newApi ? DsBaseField9.create(type, field, dsType, rs)
                    : DsBaseField.create(field, dsType, rs);
        }
    }

    List<DsField> createDsFields(ResultSet rs, boolean multiple) throws ReflectiveOperationException {
        boolean newApi = newApi(multiple);
        ArrayList<DsField> list = new ArrayList<>();
        Field[] fields = type.getFields();
        for (Field field : fields) {
            DsField f = createDsField(field, rs, newApi);
            if (f != null) {
                f.setAccessible();
                list.add(f);
            }
        }
        Collections.sort(list);
        return list;
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

    @Override
    public T read(ResultSet rs) throws SQLException, ReflectiveOperationException {
        List<DsField> list = createDsFields(rs, false);
        if (rs.next()) {
            return read(rs, list);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private T createInstance() throws ReflectiveOperationException {
        if (unsafe != null) {
            try {
                return (T) unsafe.allocateInstance(type);
            } catch (ReflectiveOperationException ignored) {
            }
        } else if (constructor != null) {
            try {
                return constructor.newInstance();
            } catch (ReflectiveOperationException ignored) {
            }
        }
        return type.newInstance();
    }

    @Override
    public void readArray(Collection<T> out, ResultSet rs, int limit) throws SQLException, ReflectiveOperationException {
        List<DsField> list = createDsFields(rs, true);
        while (rs.next()) {
            if (limit-- <= 0) {
                break;
            }
            out.add(read(rs, list));
        }
    }

    @Override
    public void readArray(Collection<T> out, ResultSet rs) throws SQLException, ReflectiveOperationException {
        List<DsField> list = createDsFields(rs, true);
        while (rs.next()) {
            out.add(read(rs, list));
        }
    }

}
