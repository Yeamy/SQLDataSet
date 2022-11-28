package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;

class DsExtendField implements DsField {
    private final Field field;
    private final DsFactory<?> factory;

    DsExtendField(Field field, Class<?> clz) {
        this.field = field;
        this.factory = new DsFactory<>(clz);
    }

    @Override
    public int compareTo(DsField o) {
        return 2;
    }

    @Override
    public DsColumnIndex findColumnIndex(ResultSet rs) {
        List<DsColumnIndex> list = factory.findColumnIndex(rs);
        return (r, t) -> field.set(t, factory.read(r, list));
    }

}