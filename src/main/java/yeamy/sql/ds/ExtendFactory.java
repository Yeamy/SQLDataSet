package yeamy.sql.ds;

import java.util.Calendar;

public class ExtendFactory<T> extends InternalDsFactory<T> {
    private final InternalDsFactory<?> parent;

    ExtendFactory(InternalDsFactory<?> parent, Class<T> type) {
        super(type);
        this.parent = parent;
    }

    @Override
    Calendar getCalendar() {
        return parent.getCalendar();
    }

}
