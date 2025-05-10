package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class DsField implements Comparable<DsField> {
    final Field field;
    final int columnIndex;

    public DsField(Field field, int columnIndex) {
        this.field = field;
        this.columnIndex = columnIndex;
    }

    abstract void read(ResultSet rs, Object t) throws SQLException, ReflectiveOperationException;

    void setAccessible() {
        int flag = field.getModifiers();
        if (!Modifier.isPublic(flag) || Modifier.isFinal(flag)) {
            field.setAccessible(true);
        }
    }

    static int findColumnIndex(Field field, ResultSet rs) {
        DsColumn ann = field.getAnnotation(DsColumn.class);
        if (ann != null && ann.value() != null && !ann.value().isEmpty()) {
            try {
                return rs.findColumn(ann.value());
            } catch (SQLException ignored) {
            }
        } else {
            try {
                return rs.findColumn(field.getName());
            } catch (SQLException e) {
                try {
                    return rs.findColumn(name2underline(field.getName()));
                } catch (SQLException ignored) {
                }
            }
        }
        return -1;
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