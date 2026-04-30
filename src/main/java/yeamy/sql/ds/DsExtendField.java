package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

class DsExtendField extends DsField {
    private final ExtendFactory<?> factory;
    private final List<DsField> list;

    public static DsExtendField create(InternalDsFactory<?> factory, Field field, ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap)
            throws ReflectiveOperationException {
        ExtendFactory<?> extend = new ExtendFactory<>(factory, field.getType());
        List<DsField> list = extend.createDsFields(rs, fieldMap, false);
        return list.size() == 0 ? null : new DsExtendField(extend, field, list);
    }

    DsExtendField(ExtendFactory<?> factory, Field field, List<DsField> list) {
        super(field, -1);
        this.factory = factory;
        this.list = list;
    }

    @Override
    int sortInt() {
        return 2;
    }

    @Override
    public void read(ResultSet rs, Object t, InternalDsFactory<?> parent) throws SQLException, ReflectiveOperationException {
        field.set(t, this.factory.read(rs, this.list));
    }
}