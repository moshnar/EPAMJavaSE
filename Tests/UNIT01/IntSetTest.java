package UNIT01;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntSetTest {

    private static final int[] values = {
            -1, -32, -63,
            -64, -120, Integer.MIN_VALUE,
            0, 1, 3,
            32, 33, 64,
            65, 500, 10000,
            Integer.MAX_VALUE
    };

    private static final int[] notPresentedValues = {
            2, 4, 5, 72, Integer.MAX_VALUE - 1,
            -2, -4, -5, -72, Integer.MIN_VALUE + 1,
    };

    private IntSet set;

    @Before
    public void setup() {
        set = new IntSet(values);
    }

    @Test
    public void testContains() {
        for (int value : values) {
            assertTrue(set.contains(value));
        }

        for (int value : notPresentedValues) {
            assertFalse(set.contains(value));
        }
    }

    @Test
    public void testRemove()
    {
        set.remove(-32);
        set.remove(32);

        assertFalse(set.contains(32));
        assertFalse(set.contains(-32));
    }

    @Test
    public void testUnion() {

        final int[] ints = {6, 177, -55};

        IntSet another = new IntSet(ints);

        IntSet united = set.union(another);

        for (int num : values) {
            assertTrue(united.contains(num));
        }

        for (int num : ints) {
            assertTrue(united.contains(num));
        }

        for (int num : notPresentedValues) {
            assertFalse(united.contains(num));
        }
    }

    @Test
    public void testSubset() {

        IntSet subset = new IntSet(new int[]{
                -64, -120, Integer.MIN_VALUE,
                0, 1, 3,
                32, 33, 64,
        });

        assertTrue(subset.isSubsetOf(set));

        assertFalse(subset.isSubsetOf(new IntSet(notPresentedValues)));
    }

    @Test
    public void testIntersection() {

        IntSet another = new IntSet(new int[]{
                -64, -120, Integer.MIN_VALUE,
                0, 1, 3, 5,
                32, 33, 64,
        });

        IntSet intersected = set.intersection(another);

        assertTrue(intersected.contains(-64));
        assertTrue(intersected.contains(-120));
        assertTrue(intersected.contains(32));

        assertFalse(intersected.contains(5));
    }

    @Test
    public void testDifference() {

        IntSet another = new IntSet(new int[]{
                -64, -120, Integer.MIN_VALUE,
                0, 1, 3, 5,
                1333, 33, 64,
        });

        IntSet difference = set.difference(another);

        assertTrue(difference.contains(5));
        assertTrue(difference.contains(1333));

        assertFalse(difference.contains(-64));
        assertFalse(difference.contains(33));
    }
}

