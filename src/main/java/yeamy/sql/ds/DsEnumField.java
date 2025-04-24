package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsEnumField implements DsField {
    private final Field field;
    private final Class<?> type;
    private final Method method;

    DsEnumField(Field field) {
        this.field = field;
        this.type = field.getType();
        Method method;
        try {
            method = type.getMethod("valueOf", String.class);
        } catch (Exception e) {
            method = null;
        }
        this.method = method;
    }

    @Override
    public int compareTo(DsField o) {
        return o instanceof DsBaseField ? 1
                : o instanceof DsEnumField ? 0
                : -1;
    }

    @Override
    public DsColumnIndex findColumnIndex(ResultSet rs) {
        int index = DsField.findColumnIndex(field, rs);
        return index == -1 ? null : (r, t) -> read(r, t, index);
    }

    private void read(ResultSet rs, Object t, int columnIndex) throws SQLException, IllegalAccessException {
        try {
            String data = rs.getString(columnIndex);
            Object obj = method.invoke(type, data);
            field.set(t, obj);
        } catch (IllegalArgumentException | InvocationTargetException e) {
        }
    }
}