package yeamy.sql.ds;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

class DsExtendField9 extends DsField {
    private final ExtendFactory<?> factory;
    private final List<DsField> list;
    private final VarHandle vh;

    public static DsExtendField9 create(InternalDsFactory<?> factory, Class<?> type, Field field, ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap) throws ReflectiveOperationException {
        ExtendFactory<?> extend = new ExtendFactory<>(factory, field.getType());
        List<DsField> list = factory.createDsFields(rs, fieldMap, true);
        return list.size() == 0 ? null : new DsExtendField9(extend, type, field, list);
    }

    DsExtendField9(ExtendFactory<?> factory, Class<?> type, Field field, List<DsField> list) throws ReflectiveOperationException {
        super(field, -1);
        this.factory = factory;
        this.list = list;
        this.vh = MethodHandles.lookup().findVarHandle(type, field.getName(), field.getType());
    }

    @Override
    int sortInt() {
        return 2;
    }

    @Override
    public void read(ResultSet rs, Object t, InternalDsFactory<?> factory) throws SQLException, ReflectiveOperationException {
        vh.set(t, this.factory.read(rs, this.list));
    }
}