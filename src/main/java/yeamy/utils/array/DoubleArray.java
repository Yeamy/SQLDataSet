package yeamy.utils.array;

public class DoubleArray {

	private int length = 0;
	private double[] array;

	public DoubleArray() {
		this(16);
	}

	public DoubleArray(int length) {
		array = new double[length];
	}

	private void expand(int targetLength) {
		double[] dest = new double[targetLength];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;

	}

	public void add(double n) {
		int index = length;
		if (index == array.length) {
			expand(array.length + 16);
		}
		array[index] = n;
		++length;
	}

	public void add(double... n) {
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
		double[] dest = new double[length];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;
	}

    public double get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, double data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

	public double[] getArray() {
		double[] dest = new double[length];
		System.arraycopy(array, 0, dest, 0, length);
		return dest;
	}
}
