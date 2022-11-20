package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

class DsExAdapter implements DsAdapter {
    private final DsFactory<?> factory;
    private transient List<DsField> list;

    DsExAdapter(Class<?> clz) {
        factory = new DsFactory<>(clz);
    }

    @Override
    public Object read(Field field, ResultSet rs, int columnIndex)
            throws SQLException, InstantiationException, IllegalAccessException {
        return factory.read(rs, list);
    }

    boolean findColumnIndex(ResultSet rs) {
        list = factory.findColumnIndex(rs);
        return list.size() > 0;
    }
}