package yeamy.utils.array;

public class ByteArray {

	private int length = 0;
	private byte[] array;

	public ByteArray() {
		this(16);
	}

	public ByteArray(int size) {
		array = new byte[size];
	}

	private void expand(int targetLength) {
		byte[] dest = new byte[targetLength];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;

	}

	public void add(byte n) {
		int index = length;
		if (index == array.length) {
			expand(array.length + 16);
		}
		array[index] = n;
		++length;
	}

	public void add(byte... n) {
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
		byte[] dest = new byte[length];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;
	}

	public byte[] getArray() {
		byte[] dest = new byte[length];
		System.arraycopy(array, 0, dest, 0, length);
		return dest;
	}

	public int length() {
		return length;
	}
}
