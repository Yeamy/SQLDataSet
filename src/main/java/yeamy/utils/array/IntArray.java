package yeamy.utils.array;

public class IntArray {

    private int length = 0;
    private int[] array;

    public IntArray() {
        this(16);
    }

    public IntArray(int length) {
        array = new int[length];
    }

    private void expand(int targetLength) {
        int[] dest = new int[targetLength];
        System.arraycopy(array, 0, dest, 0, length);
        this.array = dest;

    }

    public void add(int n) {
        int index = length;
        if (index == array.length) {
            expand(array.length + 16);
        }
        array[index] = n;
        ++length;
    }

    public void add(int... n) {
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
        int[] dest = new int[length];
        System.arraycopy(array, 0, dest, 0, length);
        this.array = dest;
    }

    public int get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, int data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

    public int[] getArray() {
        int[] dest = new int[length];
        System.arraycopy(array, 0, dest, 0, length);
        return dest;
    }

    public int length() {
        return length;
    }
}
