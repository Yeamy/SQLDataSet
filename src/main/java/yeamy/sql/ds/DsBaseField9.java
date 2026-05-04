package yeamy.sql.ds;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsBaseField9 extends DsField {
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
    public void read(ResultSet rs, Object t, InternalDsFactory<?> factory) throws SQLException, ReflectiveOperationException {
        switch (dsType) {
            case _boolean:
                field.setBoolean(t, rs.getBoolean(columnIndex));
                break;
            case Boolean:
                field.set(t, rs.getBoolean(columnIndex));
                break;
            case _byte:
                field.setByte(t, rs.getByte(columnIndex));
                break;
            case Byte:
                field.set(t, rs.getByte(columnIndex));
                break;
            case _short:
                field.setShort(t, rs.getShort(columnIndex));
                break;
            case Short:
                field.set(t, rs.getShort(columnIndex));
                break;
            case _int:
                field.setInt(t, rs.getInt(columnIndex));
                break;
            case Integer:
                field.set(t, rs.getInt(columnIndex));
                break;
            case _long:
                field.setLong(t, rs.getLong(columnIndex));
                break;
            case Long:
                field.set(t, rs.getLong(columnIndex));
                break;
            case _float:
                field.setFloat(t, rs.getFloat(columnIndex));
                break;
            case Float:
                field.set(t, rs.getFloat(columnIndex));
                break;
            case _double:
                field.setDouble(t, rs.getDouble(columnIndex));
                break;
            case Double:
                field.set(t, rs.getDouble(columnIndex));
                break;
            case BigDecimal:
                vh.set(t, rs.getBigDecimal(columnIndex));
                break;
            case String:
                vh.set(t, rs.getString(columnIndex));
                break;
            case Date:
                vh.set(t, rs.getDate(columnIndex, factory.getCalendar()));
                break;
            case Time:
                vh.set(t, rs.getTime(columnIndex, factory.getCalendar()));
                break;
            case Timestamp:
                vh.set(t, rs.getTimestamp(columnIndex, factory.getCalendar()));
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