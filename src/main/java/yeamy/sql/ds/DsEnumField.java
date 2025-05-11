package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsEnumField extends DsField {
    private final Class<?> type;
    private final Method method;

    public static DsField create(Field field, ResultSet rs) {
        int columnIndex = findColumnIndex(field, rs);
        return columnIndex == -1 ? null : new DsEnumField(field, columnIndex);
    }

    DsEnumField(Field field, int columnIndex) {
        super(field, columnIndex);
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
    int sortInt() {
        return 0;
    }

    @Override
    public void read(ResultSet rs, Object t) throws SQLException, ReflectiveOperationException {
        String data = rs.getString(columnIndex);
        Object obj = method.invoke(type, data);
        field.set(t, obj);
    }
}