package yeamy.sql.ds;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class DsExtendField9 extends DsField {
    private final InternalDsFactory<?> factory;
    private final List<DsField> list;
    private final VarHandle vh;

    public static DsExtendField9 create(Class<?> type, Field field, ResultSet rs) throws ReflectiveOperationException {
        InternalDsFactory<?> factory = new InternalDsFactory<>(field.getType());
        List<DsField> list = factory.createDsFields(rs, true);
        return list.get(0).columnIndex == -1 ? null : new DsExtendField9(type, field, factory, list);
    }

    DsExtendField9(Class<?> type, Field field, InternalDsFactory<?> factory, List<DsField> list) throws ReflectiveOperationException {
        super(field, list.get(0).columnIndex);
        this.factory = factory;
        this.list = list;
        this.vh = MethodHandles.lookup().findVarHandle(field.getType(), field.getName(), type);
    }

    @Override
    public int compareTo(DsField o) {
        return o instanceof DsExtendField9 ? 0 : 1;
    }

    @Override
    public void read(ResultSet rs, Object t) throws SQLException, ReflectiveOperationException {
        vh.set(this.factory.read(rs, this.list));
    }
}