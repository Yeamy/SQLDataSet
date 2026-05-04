package yeamy.utils.array;

public class ShortArray {

	private int length = 0;
	private short[] array;

	public ShortArray() {
		this(16);
	}

	public ShortArray(int length) {
		array = new short[length];
	}

	private void expand(int targetLength) {
		short[] dest = new short[targetLength];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;

	}

	public void add(short n) {
		int index = length;
		if (index == array.length) {
			expand(array.length + 16);
		}
		array[index] = n;
		++length;
	}

	public void add(short... n) {
		int index = length;
		if (index + n.length <= array.length) {
			expand(array.length + n.length + 16);
		}
		System.arraycopy(n, n.length, array, index, n.length);
		length += n.length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('[');
		boolean f = true;
		for (int i = 0; i < length; i++) {
			if (f) {
				f = false;
			} else {
				sb.append(',');
			}
			sb.append(array[i]);
		}
		sb.append(']');
		return sb.toString();
	}

	public void trimToSize() {
		short[] dest = new short[length];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;
	}

    public short get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, short data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

	public short[] getArray() {
		short[] dest = new short[length];
		System.arraycopy(array, 0, dest, 0, length);
		return dest;
	}

	public int length() {
		return length;
	}
}
