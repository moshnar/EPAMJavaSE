package UNIT01;


public class IntSet {

    private long[][] data = {{0}, {0}};


    public IntSet(int[] data) {
        for (int num : data) {
            add(num);
        }
    }

    private IntSet(long[][] data) {
        this.data = data;
    }

    public void add(int value) {

        ensureCapacity(value);

        data[getRootIndex(value)][getIndex(value)] |= 1L << Math.abs(value);
    }

    public void remove(int value) {
        getRoot(value)[getIndex(value)] &= ~(1L << Math.abs(value));
    }

    public boolean contains(int value) {

        if (getRoot(value).length <= getIndex(value)) {
            return false;
        }

        return (getPartition(value) & (1L << Math.abs(value))) != 0;
    }

    public IntSet union(IntSet set) {

        long[][] newData = new long[2][];

        for (int i = 0; i < 2; i++) {
            final long[] shortest = data[i].length < set.data[i].length ? data[i] : set.data[i];
            final long[] longest = data[i] == shortest ? set.data[i] : data[i];

            long[] tmp = new long[longest.length];
            System.arraycopy(longest, 0, tmp, 0, longest.length);

            for (int j = 0; j < shortest.length; j++) {
                tmp[j] |= shortest[j];
            }
            newData[i] = tmp;

        }

        return new IntSet(newData);
    }

    public IntSet intersection(IntSet set) {

        final long[][] newData = {{0}, {0}};

        for (int i = 0; i < 2; i++) {

            final long[] shortest = data[i].length < set.data[i].length ? data[i] : set.data[i];
            final long[] longest = data[i] == shortest ? set.data[i] : data[i];

            long[] tmp = new long[shortest.length];

            for (int j = 0; j < shortest.length; j++) {
                tmp[j] = shortest[j] & longest[j];
            }

            newData[i] = tmp;
        }

        return new IntSet(newData);
    }

    public IntSet difference(IntSet set) {

        final long[][] newData = {{0}, {0}};

        for (int i = 0; i < 2; i++) {

            final long[] shortest = data[i].length < set.data[i].length ? data[i] : set.data[i];
            final long[] longest = data[i] == shortest ? set.data[i] : data[i];

            long[] tmp = new long[longest.length];
            System.arraycopy(
                    longest,
                    shortest.length,
                    tmp,
                    shortest.length,
                    longest.length - shortest.length
            );

            for (int j = 0; j < shortest.length; j++) {
                tmp[j] = shortest[j] ^ longest[j];
            }

            newData[i] = tmp;
        }

        return new IntSet(newData);
    }

    public boolean isSubsetOf(IntSet set) {

        for (int i = 0; i < 2; i++) {
            if (set.data[i].length < data[i].length) {
                return false;
            }
            for (int index = 0; index < data[i].length; index++) {
                if (data[i][index] == 0) {
                    continue;
                }
                if ((set.data[i][index] & data[i][index]) == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private void ensureCapacity(int value) {

        final int index = getIndex(value);
        final long[] root = getRoot(value);

        if (root.length > index) {
            return;
        }

        final long[] tmp = new long[index + 1];

        System.arraycopy(root, 0, tmp, 0, root.length);

        data[getRootIndex(value)] = tmp;
    }

    private long[] getRoot(int value) {
        return data[getRootIndex(value)];
    }

    private int getRootIndex(int value) {
        return value < 0 ? 0 : 1;
    }

    private int getIndex(int value) {
        return Math.abs(value/64);
    }

    private long getPartition(int value) {
        return getRoot(value)[getIndex(value)];
    }
}







