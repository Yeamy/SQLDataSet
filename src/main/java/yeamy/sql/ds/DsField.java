package yeamy.sql.ds;

import yeamy.sql.DsColumn;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

class DsField {
    private static final Calendar calendar = Calendar.getInstance();
    private final Field field;
    private final DsType dsType;
    private final DsAdapter adapter;
    transient int columnIndex = -1;

    static DsField get(Field field, DsFactory<?> factory) {
        DsType dsType = DsType.getDsType(field);
        if (dsType == DsType.Extra) {// extra type
            Class<?> type = field.getType();
            DsAdapter adapter = factory.getAdapter(type);
            if (adapter == null) {// not defined
                if (field.isAnnotationPresent(DsColumn.class)) {
                    return null;
                } else {
                    adapter = new DsExAdapter(type);
                    factory.addAdapter(type, adapter);
                    return new DsField(field, dsType, adapter);
                }
            } else {
                return new DsField(field, dsType, adapter);
            }
        } else {// base type
            return new DsField(field, dsType, null);
        }
    }

    private DsField(Field field, DsType dsType, DsAdapter adapter) {
        this.field = field;
        this.dsType = dsType;
        this.adapter = adapter;
    }

    boolean isBaseType() {
        return adapter == null;
    }

    boolean findColumnIndex(ResultSet rs) {
        if (adapter != null && adapter instanceof DsExAdapter) {
            DsExAdapter exAdapter = (DsExAdapter) adapter;
            return exAdapter.findColumnIndex(rs);
        }
        DsColumn ann = field.getAnnotation(DsColumn.class);
        if (ann != null && ann.value() != null && ann.value().length() > 0) {
            try {
                columnIndex = rs.findColumn(ann.value());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                columnIndex = rs.findColumn(field.getName());
            } catch (Exception e) {
                try {
                    columnIndex = rs.findColumn(name2underline(field.getName()));
                } catch (Exception e2) {
                    e.printStackTrace();
                }
            }
        }
        return columnIndex != -1;
    }

    private static String name2underline(String name) {
        StringBuilder sb = new StringBuilder(name);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.setCharAt(i, (char) (c + 32));
                sb.insert(i, '_');
                i++;
            }
        }

        return sb.toString();
    }

    void read(ResultSet rs, Object t) throws SQLException, InstantiationException, IllegalAccessException {
        if (adapter != null) {
            field.set(adapter.read(field, rs, columnIndex), t);
            return;
        }
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
                field.set(t, rs.getDate(columnIndex, calendar));
                break;
            case Time:
                field.set(t, rs.getTime(columnIndex, calendar));
                break;
            case Timestamp:
                field.set(t, rs.getTimestamp(columnIndex, calendar));
                break;
            case URL:
                field.set(t, rs.getURL(columnIndex));
                break;
            case Blob:
                field.set(t, rs.getBlob(columnIndex));
                break;
            default:
        }
    }
}