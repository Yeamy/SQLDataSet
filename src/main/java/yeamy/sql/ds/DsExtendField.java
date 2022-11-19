package yeamy.sql.ds;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

class DsExtendField implements DsField {
	private Field field;
	private DsFactory<?> factory;

	DsExtendField(Field field, Class<?> clz) {
		this.field = field;
		this.factory = new DsFactory<>(clz);
	}

	@Override
	public DsColumnIndex findColumnIndex(ResultSet rs, HashMap<String, Integer> colMap) {
		List<DsColumnIndex> list = factory.findColumnIndex(rs, colMap);
		return new DsExColumnIndex(this, list);
	}

	@Override
	public void read(ResultSet rs, Object t, DsColumnIndex index)
			throws SQLException, InstantiationException, IllegalAccessException {
		DsExColumnIndex i = (DsExColumnIndex) index;
		field.set(t, factory.read(rs, i.list));
	}

	private static class DsExColumnIndex implements DsColumnIndex {
		private DsExtendField field;
		private List<DsColumnIndex> list;

		public DsExColumnIndex(DsExtendField field, List<DsColumnIndex> list) {
			this.field = field;
			this.list = list;
		}

		@Override
		public void read(ResultSet rs, Object t) {
			try {
				field.read(rs, t, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}