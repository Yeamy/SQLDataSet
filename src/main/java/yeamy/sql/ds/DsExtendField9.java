package yeamy.sql.ds;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

class DsExtendField9 extends DsField {
    private final List<DsField> list;
    private final VarHandle vh;

    public static DsExtendField9 create(Class<?> type, Field field, ResultSet rs, Map<Class<?>, DsFieldReader<?>> fieldMap) throws ReflectiveOperationException {
        InternalDsFactory<?> factory = new InternalDsFactory<>(field.getType());
        List<DsField> list = factory.createDsFields(rs, fieldMap, true);
        return list.get(0).columnIndex == -1 ? null : new DsExtendField9(type, field, list);
    }

    DsExtendField9(Class<?> type, Field field, List<DsField> list) throws ReflectiveOperationException {
        super(field, list.get(0).columnIndex);
        this.list = list;
        this.vh = MethodHandles.lookup().findVarHandle(type, field.getName(), field.getType());
    }

    @Override
    int sortInt() {
        return 2;
    }

    @Override
    public void read(ResultSet rs, Object t, InternalDsFactory<?> factory) throws SQLException, ReflectiveOperationException {
        vh.set(t, factory.read(rs, this.list));
    }
}