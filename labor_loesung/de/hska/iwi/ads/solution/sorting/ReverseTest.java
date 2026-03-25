package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.Reverse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Provides a suite of tests for Reverse implementations.
 * The test class for your implementation should use this
 * class as a base class. Add additional tests to your
 * test class.
 */
public class ReverseTest {

    /**
     Creates and returns an implementation of the Reverse interface.
     Override this method in the subclass
     with, for instance, a ReverseArray implementation
     <pre>
     @Override
     public &lt;E extends Comparable&lt;E>> Reverse&lt;E> createReverse() {
     return new ReverseArray&lt;E>();
     }
     </pre>
     */
    public <E extends Comparable<E>> Reverse<E> createReverse() {
        return new ReverseArray<>();
    }

    @Test
    void testReverseIntegerEntireArray() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {1, 2, 3, 4, 5};
        reverse.reverse(a, 0, 4);
        assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, a);
    }

    @Test
    void testReverseIntegerEntireArray1() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {1, 2, 3, 4};
        reverse.reverse(a, 0, 3);
        assertArrayEquals(new Integer[]{4, 3, 2, 1}, a);
    }

    @Test
    void testReverseIntegerSubrange() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {0, 2, 4, 7, 9, 10, 11};
        reverse.reverse(a, 1, 3);
        assertArrayEquals(new Integer[]{0, 7, 4, 2, 9, 10, 11}, a);
    }

    @Test
    void testReverseIntegerSubrange1() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {0, 2, 4, 7, 9, 10, 11};
        reverse.reverse(a, 2, 5);
        assertArrayEquals(new Integer[]{0, 2, 10, 9, 7, 4, 11}, a);
    }

    @Test
    void testReverseIntegerSingleElement() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {42};
        reverse.reverse(a, 0, 0);
        assertArrayEquals(new Integer[]{42}, a);
    }

    @Test
    void testReverseIntegerSingleElement1() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {1, 2, 3, 4, 5};
        reverse.reverse(a, 2, 2);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, a);
    }

    @Test
    void testReverseIntegerTwoElements() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {1, 2};
        reverse.reverse(a, 0, 1);
        assertArrayEquals(new Integer[]{2, 1}, a);
    }

    @Test
    void testReverseIntegerTwoElements1() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {10, 20, 30};
        reverse.reverse(a, 1, 2);
        assertArrayEquals(new Integer[]{10, 30, 20}, a);
    }

    @Test
    void testReverseIntegerFirstTwoElements() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {10, 20, 30};
        reverse.reverse(a, 0, 1);
        assertArrayEquals(new Integer[]{20, 10, 30}, a);
    }

    @Test
    void testReverseIntegerOutsideRangeUnchanged() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {10, 20, 30, 40, 50};
        reverse.reverse(a, 1, 3);
        assertEquals(10, a[0]);
        assertEquals(50, a[4]);
    }

    @Test
    void testReverseIntegerDoubleReverseRestoresOriginal() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {7, 3, 9, 1, 5};
        Integer[] original = a.clone();
        reverse.reverse(a, 0, 4);
        reverse.reverse(a, 0, 4);
        assertArrayEquals(original, a);
    }

    @Test
    void testReverseStringArray() {
        Reverse<String> reverse = createReverse();
        String[] a = {"a", "black", "car", "moving"};
        reverse.reverse(a, 0, 3);
        assertArrayEquals(new String[]{"moving", "car", "black", "a"}, a);
    }

    @Test
    void testReverseStringArray1() {
        Reverse<String> reverse = createReverse();
        String[] a = {"a", "black", "car", "moving", "over"};
        reverse.reverse(a, 1, 3);
        assertArrayEquals(new String[]{"a", "moving", "car", "black", "over"}, a);
    }

    @Test
    void testReverseIntegerWithNulls() {
        Reverse<Integer> reverse = createReverse();
        Integer[] a = {null, 1, null, 2, null};
        reverse.reverse(a, 0, 4);
        assertArrayEquals(new Integer[]{null, 2, null, 1, null}, a);
    }

    @Test
    void testReverseIntegerLargeArray() {
        Reverse<Integer> reverse = createReverse();
        int n = 1000;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = i;
        reverse.reverse(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            assertEquals(n - 1 - i, a[i]);
        }
    }
}
