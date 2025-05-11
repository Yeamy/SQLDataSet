package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

class DsReaderField extends DsField {
    private final DsFieldReader<?> reader;

    public static DsField create(Field field, ResultSet rs, DsFieldReader<?> reader) {
        int columnIndex = findColumnIndex(field, rs);
        return columnIndex == -1 ? null : new DsReaderField(field, columnIndex, reader);
    }

    private DsReaderField(Field field, int columnIndex, DsFieldReader<?> reader) {
        super(field, columnIndex);
        this.reader = reader;
    }

    @Override
    public int sortInt() {
        return 1;
    }

    @Override
    public void read(ResultSet rs, Object t) throws SQLException, ReflectiveOperationException {
        field.set(t, reader.read(rs, columnIndex));
    }
}
