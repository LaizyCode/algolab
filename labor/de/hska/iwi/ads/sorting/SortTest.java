package de.hska.iwi.ads.sorting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Basis-Testklasse für Sortieralgorithmen.
 */
public abstract class SortTest {

    /**
     * Muss in der Unterklasse implementiert werden, um die
     * konkrete Mergesort-Instanz zurückzugeben.
     */
    public abstract <E extends Comparable<E>> Sort<E> createSort();

    @Test
    void testSortIntegerArray() {
        Sort<Integer> sorter = createSort();
        Integer[] a = {10, 3, 7, 2, 0, 11, 4};
        Integer[] expected = {0, 2, 3, 4, 7, 10, 11};

        sorter.sort(a);
        assertArrayEquals(expected, a, "Das Integer-Array sollte aufsteigend sortiert sein.");
    }

    @Test
    void testSortAlreadySorted() {
        Sort<Integer> sorter = createSort();
        Integer[] a = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testSortReverseSorted() {
        Sort<Integer> sorter = createSort();
        Integer[] a = {5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5};

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testSortWithDuplicates() {
        Sort<Integer> sorter = createSort();
        Integer[] a = {3, 1, 2, 3, 1};
        Integer[] expected = {1, 1, 2, 3, 3};

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testSortEmptyArray() {
        Sort<Integer> sorter = createSort();
        Integer[] a = {};
        Integer[] expected = {};

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testSortSingleElement() {
        Sort<Integer> sorter = createSort();
        Integer[] a = {42};
        Integer[] expected = {42};

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testSortStringArray() {
        Sort<String> sorter = createSort();
        String[] a = {"utah", "roads", "public", "a"};
        String[] expected = {"a", "public", "roads", "utah"};

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testSortNullArray() {
        Sort<Integer> sorter = createSort();
        Integer[] a = null;
        assertThrows(NullPointerException.class, () -> sorter.sort(a));
    }

    @Test
    void testSortStability() {
        // Mergesort ist stabil. Bei komplexen Objekten mit gleichem Key
        // sollte die relative Reihenfolge erhalten bleiben.
        Sort<Tuple> sorter = createSort();
        Tuple t1 = new Tuple(1, "A");
        Tuple t2 = new Tuple(1, "B");
        Tuple[] a = {t2, t1}; // Beide haben Key 1, aber B kam vor A

        sorter.sort(a);

        // Bei Stabilität muss t2 (B) vor t1 (A) bleiben, wenn der Key identisch ist
        assertSame(t2, a[0]);
        assertSame(t1, a[1]);
    }

    // Hilfsklasse für Stabilitätstest
    private static class Tuple implements Comparable<Tuple> {
        int key;
        String val;
        Tuple(int k, String v) { this.key = k; this.val = v; }
        @Override
        public int compareTo(Tuple o) { return Integer.compare(this.key, o.key); }
    }
}