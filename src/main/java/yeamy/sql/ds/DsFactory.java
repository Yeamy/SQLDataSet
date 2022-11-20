package yeamy.sql.ds;

import yeamy.sql.DsIgnore;
import yeamy.sql.DsObserver;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DsFactory<T> {

	private HashMap<Class<?>, DsAdapter> map;
	private final List<DsField> fields;
	private final Class<T> type;

	public DsFactory(Class<T> type) {
		this.type = type;
		LinkedList<DsField> list = new LinkedList<>();
		Field[] fields = type.getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(DsIgnore.class)) {
				continue;
			}
			DsField f = DsField.get(field, this);
			if (f != null) {
				if (f.isBaseType()) {
					list.addFirst(f);
				} else {
					list.addLast(f);
				}
			}
		}
		this.fields = list;
	}

	public void addAdapter(Class<?> fieldType, DsAdapter adapter) {
		if (map == null) {
			map = new HashMap<>();
		}
		map.put(fieldType, adapter);
	}

	public DsAdapter getAdapter(Class<?> fieldType) {
		if (map == null) {
			return null;
		}
		while (true) {
			if (fieldType == null) {
				return null;
			}
			DsAdapter adapter = map.get(fieldType);
			if (adapter == null) {
				fieldType = fieldType.getSuperclass();
			} else {
				return adapter;
			}
		}
	}

	List<DsField> findColumnIndex(ResultSet rs) {
		List<DsField> fields = new ArrayList<>();
		for (DsField dsField : this.fields) {
			if (dsField.findColumnIndex(rs)) {
				fields.add(dsField);
			}
		}
		return fields;
	}

	T read(ResultSet rs, List<DsField> list) throws SQLException, InstantiationException, IllegalAccessException {
		T t = type.newInstance();
		for (DsField f : list) {
			f.read(rs, t);
		}
		if (t instanceof DsObserver) {
			((DsObserver) t).onDsFinish();
		}
		return t;
	}

	public T read(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField> list = findColumnIndex(rs);
		if (rs.next()) {
			return read(rs, list);
		}
		return null;
	}

	public void readArray(Collection<T> out, ResultSet rs, int limit)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField> list = findColumnIndex(rs);
		while (rs.next()) {
			if (limit-- <= 0) {
				break;
			}
			out.add(read(rs, list));
		}
	}

	public void readArray(Collection<T> out,  ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsField> list = findColumnIndex(rs);
		while (rs.next()) {
			out.add(read(rs, list));
		}
	}

}
