package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.AbstractMergesort;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract base test class for Mergesort implementations.
 * Concrete test classes should extend this class and implement createSorter().
 */
public abstract class MergesortTest {

    /**
     * Factory method for creating a sorter instance.
     * Must be implemented by subclasses.
     */
    public abstract <E extends Comparable<E>> AbstractMergesort<E> createSorter();

    // =========================================================================
    // Randfälle / Randgrößen
    // =========================================================================

    @Test
    void testEmptyArray() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{}, a);
    }

    @Test
    void testSingleElement() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {42};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{42}, a);
    }

    @Test
    void testTwoElementsSorted() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {1, 2};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2}, a);
    }

    @Test
    void testTwoElementsUnsorted() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {2, 1};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2}, a);
    }

    @Test
    void testTwoEqualElements() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {7, 7};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{7, 7}, a);
    }

    @Test
    void testNullArray() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = null;
        assertThrows(NullPointerException.class, () -> sorter.sort(a));
    }

    // =========================================================================
    // Bereits (teilweise) sortierte Eingaben
    // =========================================================================

    @Test
    void testAlreadySorted() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {1, 2, 3, 4, 5};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, a);
    }

    @Test
    void testReverseOrder() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {5, 4, 3, 2, 1};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, a);
    }

    @Test
    void testSortedLeftHalfUnsortedRight() {
        // Linke Hälfte bereits sortiert, rechte nicht
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {1, 2, 3, 9, 5};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 9}, a);
    }

    @Test
    void testUnsortedLeftHalfSortedRight() {
        // Rechte Hälfte bereits sortiert, linke nicht
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {4, 2, 6, 7, 8};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{2, 4, 6, 7, 8}, a);
    }

    @Test
    void testRotatedArray() {
        // Sortiertes Array um k Positionen rotiert ("fast sortiert")
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {4, 5, 6, 1, 2, 3};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, a);
    }

    // =========================================================================
    // Duplikate
    // =========================================================================

    @Test
    void testRandomOrder() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {3, 1, 4, 1, 5, 9, 2};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 1, 2, 3, 4, 5, 9}, a);
    }

    @Test
    void testDuplicates() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {2, 3, 2, 1, 3};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2, 2, 3, 3}, a);
    }

    @Test
    void testAllElementsEqual() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {5, 5, 5, 5, 5};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{5, 5, 5, 5, 5}, a);
    }

    @Test
    void testOneUniqueElementRest() {
        // Nur ein einziger anderer Wert, der nach oben "wandert"
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {3, 3, 3, 1, 3};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 3, 3, 3, 3}, a);
    }

    @Test
    void testManyDuplicatesInterspersed() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {1, 2, 1, 2, 1, 2, 1};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 1, 1, 1, 2, 2, 2}, a);
    }

    // =========================================================================
    // Negative Zahlen / Vorzeichen-Grenzfälle
    // =========================================================================

    @Test
    void testNegativeNumbers() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {-3, -1, -4, 2, 0};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{-4, -3, -1, 0, 2}, a);
    }

    @Test
    void testAllNegativeNumbers() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {-1, -5, -3, -2, -4};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{-5, -4, -3, -2, -1}, a);
    }

    @Test
    void testMinAndMaxInteger() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE, 1, -1};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, a);
    }

    @Test
    void testMixedPositiveNegativeWithDuplicates() {
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {-2, 3, -2, 0, 3, -1, 0};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{-2, -2, -1, 0, 0, 3, 3}, a);
    }

    // =========================================================================
    // Arraygrößen: Zweierpotenzen vs. ungerade Längen
    // =========================================================================

    @Test
    void testOddLength() {
        // Ungerade Länge – testet Aufteilung bei nicht-gerader Mitte
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {7, 3, 5, 1, 9, 4, 2, 6, 8};   // 9 Elemente
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testPowerOfTwoLength() {
        // 2er-Potenz – saubere rekursive Hälften
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {8, 6, 4, 2, 7, 5, 3, 1};       // 8 = 2³ Elemente
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, a);
    }

    @Test
    void testNonPowerOfTwoLength() {
        // Keine 2er-Potenz (6 = 2·3)
        AbstractMergesort<Integer> sorter = createSorter();
        Integer[] a = {6, 1, 5, 2, 4, 3};
        sorter.sort(a);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, a);
    }

    // =========================================================================
    // Strings
    // =========================================================================

    @Test
    void testStrings() {
        AbstractMergesort<String> sorter = createSorter();
        String[] a = {"Banane", "Apfel", "Kirsche"};
        sorter.sort(a);
        assertArrayEquals(new String[]{"Apfel", "Banane", "Kirsche"}, a);
    }

    @Test
    void testStringsWithSamePrefix() {
        // Lexikographischer Vergleich bei gemeinsamem Präfix
        AbstractMergesort<String> sorter = createSorter();
        String[] a = {"abc", "ab", "abcd", "a"};
        sorter.sort(a);
        assertArrayEquals(new String[]{"a", "ab", "abc", "abcd"}, a);
    }

    @Test
    void testStringsWithDuplicates() {
        AbstractMergesort<String> sorter = createSorter();
        String[] a = {"Birne", "Apfel", "Birne", "Apfel", "Mango"};
        sorter.sort(a);
        assertArrayEquals(new String[]{"Apfel", "Apfel", "Birne", "Birne", "Mango"}, a);
    }

    @Test
    void testSingleCharStrings() {
        AbstractMergesort<String> sorter = createSorter();
        String[] a = {"z", "a", "m", "b", "z", "a"};
        sorter.sort(a);
        assertArrayEquals(new String[]{"a", "a", "b", "m", "z", "z"}, a);
    }

    // =========================================================================
    // Datumstypen
    // =========================================================================

    @Test
    void testDates() {
        LocalDate date = LocalDate.of(2018, Month.SEPTEMBER, 10);
        AbstractMergesort<ChronoLocalDate> sorter = createSorter();

        LocalDate[] dates = {
                date.plusDays(5),
                date.minusMonths(2),
                date,
                date.plusMonths(2),
                date.minusDays(5)
        };

        sorter.sort(dates);

        assertArrayEquals(new LocalDate[]{
                date.minusMonths(2),
                date.minusDays(5),
                date,
                date.plusDays(5),
                date.plusMonths(2)
        }, dates);
    }

    @Test
    void testDatesWithDuplicates() {
        LocalDate base = LocalDate.of(2024, Month.JANUARY, 1);
        AbstractMergesort<ChronoLocalDate> sorter = createSorter();

        LocalDate[] dates = {
                base.plusDays(3),
                base,
                base.plusDays(1),
                base,                 // Duplikat
                base.plusDays(3)      // Duplikat
        };

        sorter.sort(dates);

        assertArrayEquals(new LocalDate[]{
                base,
                base,
                base.plusDays(1),
                base.plusDays(3),
                base.plusDays(3)
        }, dates);
    }

    // =========================================================================
    // Großes Array – Korrektheit gegen Arrays.sort
    // =========================================================================

    @Test
    void testLargeRandomArray() {
        AbstractMergesort<Integer> sorter = createSorter();
        Random rng = new Random(42);        // fester Seed → reproduzierbar

        int n = 1_000;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = rng.nextInt(500);        // viele Duplikate durch engen Wertebereich
        }

        Integer[] expected = a.clone();
        Arrays.sort(expected);              // Java-Referenzsortierung (stabil, korrekt)

        sorter.sort(a);

        assertArrayEquals(expected, a,
                "Großes Array (n=1000) stimmt nicht mit Arrays.sort überein");
    }

    @Test
    void testLargeAlreadySortedArray() {
        AbstractMergesort<Integer> sorter = createSorter();
        int n = 512;                        // 2er-Potenz
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = i;

        Integer[] expected = a.clone();
        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void testLargeReverseSortedArray() {
        AbstractMergesort<Integer> sorter = createSorter();
        int n = 512;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = n - i;

        Integer[] expected = a.clone();
        Arrays.sort(expected);

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }
}