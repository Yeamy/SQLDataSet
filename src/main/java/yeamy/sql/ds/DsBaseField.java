package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsBaseField extends DsField {
    private final DsType dsType;

    public static DsField create(Field field, DsType dsType, ResultSet rs) {
        int columnIndex = findColumnIndex(field, rs);
        return columnIndex == -1 ? null : new DsBaseField(field, columnIndex, dsType);
    }

    DsBaseField(Field field, int columnIndex, DsType dsType) {
        super(field, columnIndex);
        this.dsType = dsType;
    }

    @Override
    int sortInt() {
        return 0;
    }

    @Override
    public void read(ResultSet rs, Object t, InternalDsFactory<?> factory) throws SQLException, ReflectiveOperationException {
        switch (dsType) {
            case _boolean:
            case Boolean:
                field.setBoolean(t, rs.getBoolean(columnIndex));
                break;
            case _byte:
            case Byte:
                field.setByte(t, rs.getByte(columnIndex));
                break;
            case _short:
            case Short:
                field.setShort(t, rs.getShort(columnIndex));
                break;
            case _int:
            case Integer:
                field.setInt(t, rs.getInt(columnIndex));
                break;
            case _long:
            case Long:
                field.setLong(t, rs.getLong(columnIndex));
                break;
            case _float:
            case Float:
                field.setFloat(t, rs.getFloat(columnIndex));
                break;
            case _double:
            case Double:
                field.setDouble(t, rs.getDouble(columnIndex));
                break;
            case BigDecimal:
                field.set(t, rs.getBigDecimal(columnIndex));
                break;
            case String:
                field.set(t, rs.getString(columnIndex));
                break;
            case Date:
                field.set(t, rs.getDate(columnIndex, factory.getCalendar()));
                break;
            case Time:
                field.set(t, rs.getTime(columnIndex, factory.getCalendar()));
                break;
            case Timestamp:
                field.set(t, rs.getTimestamp(columnIndex, factory.getCalendar()));
                break;
            case URL:
                field.set(t, rs.getURL(columnIndex));
                break;
            case Blob:
                field.set(t, rs.getBlob(columnIndex));
                break;
            case Ref:
                field.set(t, rs.getRef(columnIndex));
                break;
            case Clob:
                field.set(t, rs.getClob(columnIndex));
            case NClob:
                field.set(t, rs.getNClob(columnIndex));
                break;
            case SQLXML:
                field.set(t, rs.getSQLXML(columnIndex));
                break;
            case Array:
                field.set(t, rs.getArray(columnIndex));
                break;
            default:
        }
    }
}