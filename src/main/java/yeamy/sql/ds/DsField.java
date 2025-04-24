package yeamy.sql.ds;

import yeamy.sql.DsColumn;

import java.lang.reflect.Field;
import java.sql.ResultSet;

interface DsField extends Comparable<DsField> {

    DsColumnIndex findColumnIndex(ResultSet rs);

    static DsField get(Field field) {
        DsType dsType = DsType.getDsType(field);
        if (dsType == DsType.Extra) {// extra type
            Class<?> type = field.getType();
            if (field.isAnnotationPresent(DsColumn.class)) {// cannot read
                return null;
            } else {
                return new DsExtendField(field, type);
            }
        } else if (dsType == DsType.Enum) {
            return new DsEnumField(field);
        } else {// base type
            return new DsBaseField(field, dsType);
        }
    }

    static int findColumnIndex(Field field, ResultSet rs) {
        DsColumn ann = field.getAnnotation(DsColumn.class);
        if (ann != null && ann.value() != null && ann.value().length() > 0) {
            try {
                return rs.findColumn(ann.value());
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            try {
                return rs.findColumn(field.getName());
            } catch (Exception e) {
                try {
                    return rs.findColumn(name2underline(field.getName()));
                } catch (Exception e2) {
                    e.printStackTrace();
                    return -1;
                }
            }
        }
    }

    static String name2underline(String name) {
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
}