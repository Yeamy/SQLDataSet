package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

class DsExtendField extends DsField {
    private final List<DsField> list;

    public static DsExtendField create(Field field, ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap)
            throws ReflectiveOperationException {
        InternalDsFactory<?> factory = new InternalDsFactory<>(field.getType());
        List<DsField> list = factory.createDsFields(rs, fieldMap, false);
        return list.get(0).columnIndex == -1 ? null : new DsExtendField(field, list);
    }

    DsExtendField(Field field, List<DsField> list) {
        super(field, list.get(0).columnIndex);
        this.list = list;
    }

    @Override
    int sortInt() {
        return 2;
    }

    @Override
    public void read(ResultSet rs, Object t, InternalDsFactory<?> factory) throws SQLException, ReflectiveOperationException {
        field.set(t, factory.read(rs, this.list));
    }
}