package yeamy.sql.ds;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

class DsBaseField9 extends DsField {
    private static final Calendar calendar = Calendar.getInstance();
    private final DsType dsType;
    private final VarHandle vh;

    public static DsField create(Class<?> type, Field field, DsType dsType, ResultSet rs) throws ReflectiveOperationException {
        int columnIndex = findColumnIndex(field, rs);
        return columnIndex == -1 ? null : new DsBaseField9(type, field, columnIndex, dsType);
    }

    DsBaseField9(Class<?> type, Field field, int columnIndex, DsType dsType) throws ReflectiveOperationException {
        super(field, columnIndex);
        this.dsType = dsType;
        this.vh = MethodHandles.lookup().findVarHandle(type, field.getName(), field.getType());
    }

    @Override
    int sortInt() {
        return 0;
    }

    @Override
    public void read(ResultSet rs, Object t) throws SQLException, ReflectiveOperationException {
        switch (dsType) {
            case _boolean:
            case Boolean:
                vh.set(t, rs.getBoolean(columnIndex));
                break;
            case _byte:
            case Byte:
                vh.set(t, rs.getByte(columnIndex));
                break;
            case _short:
            case Short:
                vh.set(t, rs.getShort(columnIndex));
                break;
            case _int:
            case Integer:
                vh.set(t, rs.getInt(columnIndex));
                break;
            case _long:
            case Long:
                vh.set(t, rs.getLong(columnIndex));
                break;
            case _float:
            case Float:
                vh.set(t, rs.getFloat(columnIndex));
                break;
            case _double:
            case Double:
                vh.set(t, rs.getDouble(columnIndex));
                break;
            case BigDecimal:
                vh.set(t, rs.getBigDecimal(columnIndex));
                break;
            case String:
                vh.set(t, rs.getString(columnIndex));
                break;
            case Date:
                vh.set(t, rs.getDate(columnIndex, calendar));
                break;
            case Time:
                vh.set(t, rs.getTime(columnIndex, calendar));
                break;
            case Timestamp:
                vh.set(t, rs.getTimestamp(columnIndex, calendar));
                break;
            case URL:
                vh.set(t, rs.getURL(columnIndex));
                break;
            case Blob:
                vh.set(t, rs.getBlob(columnIndex));
                break;
            default:
        }
    }
}