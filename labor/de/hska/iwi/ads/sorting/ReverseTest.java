package de.hska.iwi.ads.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public abstract class ReverseTest {

    /**
     * Muss in der Unterklasse implementiert werden.
     */
    public abstract <E extends Comparable<E>> Reverse<E> createReverse();

    @Test
    void testReverseFullArray() {
        Reverse<Integer> rev = createReverse();
        Integer[] a = {1, 2, 3, 4, 5};
        Integer[] expected = {5, 4, 3, 2, 1};

        rev.reverse(a, 0, a.length - 1);
        assertArrayEquals(expected, a);
    }

    @Test
    void testReversePartialRange() {
        Reverse<Integer> rev = createReverse();
        // Nur die Mitte (Index 1 bis 3, also Werte 2, 3, 4) umdrehen
        Integer[] a = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 4, 3, 2, 5};

        rev.reverse(a, 1, 3);
        assertArrayEquals(expected, a);
    }

    @Test
    void testReverseSingleElement() {
        Reverse<Integer> rev = createReverse();
        Integer[] a = {42};
        Integer[] expected = {42};

        rev.reverse(a, 0, 0);
        assertArrayEquals(expected, a);
    }

    @Test
    void testReverseEmptyRange() {
        Reverse<Integer> rev = createReverse();
        Integer[] a = {1, 2, 3};
        Integer[] expected = {1, 2, 3};

        // from > to sollte nichts ändern oder stabil bleiben
        rev.reverse(a, 2, 1);
        assertArrayEquals(expected, a);
    }

    @Test
    void testReverseStringArray() {
        Reverse<String> rev = createReverse();
        String[] a = {"A", "B", "C"};
        String[] expected = {"C", "B", "A"};

        rev.reverse(a, 0, 2);
        assertArrayEquals(expected, a);
    }

    @Test
    void testReverseNullArray() {
        Reverse<Integer> rev = createReverse();
        assertThrows(NullPointerException.class, () -> rev.reverse(null, 0, 0));
    }
}