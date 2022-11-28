package yeamy.sql.ds;

import yeamy.sql.DsIgnore;
import yeamy.sql.DsObserver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * The factory to generate java bean,
 * add custom types' adapter to create fields value.
 * You can cache the factory to avoid too much reflection.
 */
public class DsFactory<T> {

	private HashMap<Class<?>, DsAdapter> map;
	private final List<DsField> fields;
	private final Class<T> type;
	private final Constructor<T> constructor;

	/**
	 * @param type type to generate
	 */
	public DsFactory(Class<T> type) {
		this.type = type;
		Constructor<T> constructor;
		try {
			constructor = type.getDeclaredConstructor();
		} catch (Exception e) {
			constructor = null;
		}
		this.constructor = constructor;
        ArrayList<DsField> list = new ArrayList<>();
		Field[] fields = type.getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(DsIgnore.class)) {
				continue;
			}
            list.add(DsField.get(field, this));
		}
        Collections.sort(list);
		this.fields = list;
	}

	/**
	 * add a field type adapter
	 * @param fieldType type of field
	 * @param adapter adapter to read field
	 */
	public void addAdapter(Class<?> fieldType, DsAdapter adapter) {
		if (map == null) {
			map = new HashMap<>();
		}
		map.put(fieldType, adapter);
	}

	/**
	 * get a field type adapter
	 * @param fieldType type of field
	 * @return  adapter to read field
	 */
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

	List<DsColumnIndex> findColumnIndex(ResultSet rs) {
		List<DsColumnIndex> fields = new ArrayList<>();
		for (DsField dsField : this.fields) {
            DsColumnIndex i = dsField.findColumnIndex(rs);
            if (i != null) {
                fields.add(i);
            }
		}
		return fields;
	}

	@SuppressWarnings("deprecation")
    T read(ResultSet rs, List<DsColumnIndex> list) throws SQLException, InstantiationException, IllegalAccessException {
		T t = null;
		if (constructor != null) {
			try {
				t = constructor.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (t == null) {
			try {
				t = type.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		for (DsColumnIndex i : list) {
			i.read(rs, t);
		}
		if (t instanceof DsObserver) {
			((DsObserver) t).onDsFinish();
		}
		return t;
	}

	public T read(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		List<DsColumnIndex> list = findColumnIndex(rs);
		if (rs.next()) {
			return read(rs, list);
		}
		return null;
	}

	public void readArray(Collection<T> out, ResultSet rs, int limit)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsColumnIndex> list = findColumnIndex(rs);
		while (rs.next()) {
			if (limit-- <= 0) {
				break;
			}
			out.add(read(rs, list));
		}
	}

	public void readArray(Collection<T> out,  ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<DsColumnIndex> list = findColumnIndex(rs);
		while (rs.next()) {
			out.add(read(rs, list));
		}
	}

}
