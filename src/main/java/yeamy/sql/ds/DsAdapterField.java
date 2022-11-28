package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;

class DsAdapterField implements DsField {
    private final Field field;
    private final DsAdapter adapter;

    DsAdapterField(Field field, DsAdapter adapter) {
        this.field = field;
        this.adapter = adapter;
    }

    @Override
    public int compareTo(DsField o) {
        return 3;
    }

    @Override
    public DsColumnIndex findColumnIndex(ResultSet rs) {
        int index = DsField.findColumnIndex(field, rs);
        return index == -1 ? null : (r, t) -> field.set(t, adapter.read(field, r, index));
    }

}