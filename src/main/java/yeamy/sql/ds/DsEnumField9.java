package yeamy.sql.ds;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsEnumField9 extends DsField {
    private final MethodHandle handle;

    public static DsField create(Field field, ResultSet rs) throws ReflectiveOperationException {
        int columnIndex = findColumnIndex(field, rs);
        return columnIndex == -1 ? null : new DsEnumField9(field, columnIndex);
    }

    DsEnumField9(Field field, int columnIndex) throws ReflectiveOperationException {
        super(field, columnIndex);
        Class<?> type = field.getType();
        this.handle = MethodHandles.lookup().findStatic(type, "valueOf", MethodType.methodType(type, String.class));
    }

    @Override
    public int compareTo(DsField o) {
        return o instanceof DsBaseField ? 1
                : o instanceof DsEnumField9 ? 0
                : -1;
    }

    @Override
    public void read(ResultSet rs, Object t) throws SQLException, ReflectiveOperationException {
        String data = rs.getString(columnIndex);
        try {
            field.set(t, handle.invoke(data));
        } catch (Throwable ignored) {
        }
    }
}