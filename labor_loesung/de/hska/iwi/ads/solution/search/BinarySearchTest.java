package de.hska.iwi.ads.solution.search;

import de.hska.iwi.ads.search.Search;
import de.hska.iwi.ads.search.SearchTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest extends SearchTest {

    @Override
    public <E extends Comparable<E>> Search<E> createSearch() {
        return new BinarySearch<>();
    }

    // -----------------------------------------------------------------------
    // Randfälle: key < a[left] → soll left - 1 zurückgeben
    // -----------------------------------------------------------------------

    /** key kleiner als das kleinste Element im gesamten Array → -1 */
    @Test
    void testKeyBelowLeftBoundary_fullArray() {
        Search<Integer> search = createSearch();
        Integer[] a = {5, 10, 15, 20};
        // left=0, key=3 < a[0]=5 → erwartet: 0-1 = -1
        assertEquals(-1, search.search(a, 3));
    }

    /** key kleiner als a[left] bei eingeschränktem Suchbereich */
    @Test
    void testKeyBelowLeftBoundary_subArray() {
        Search<Integer> search = createSearch();
        Integer[] a = {1, 5, 10, 15, 20};
        // left=2, a[2]=10, key=7 < 10 → erwartet: 2-1 = 1
        assertEquals(1, search.search(a, 7, 2, 4));
    }

    /** key gleich Integer.MIN_VALUE, also garantiert kleiner als alles */
    @Test
    void testKeyMinValue() {
        Search<Integer> search = createSearch();
        Integer[] a = {-100, -50, 0, 50, 100};
        // key < a[0] → erwartet: -1
        assertEquals(-1, search.search(a, Integer.MIN_VALUE));
    }

    // -----------------------------------------------------------------------
    // Randfälle: key > a[right] → soll right + 1 zurückgeben
    // -----------------------------------------------------------------------

    /** key größer als das größte Element im gesamten Array → a.length */
    @Test
    void testKeyAboveRightBoundary_fullArray() {
        Search<Integer> search = createSearch();
        Integer[] a = {5, 10, 15, 20};
        // right=3, key=99 > a[3]=20 → erwartet: 3+1 = 4
        assertEquals(4, search.search(a, 99));
    }

    /** key größer als a[right] bei eingeschränktem Suchbereich */
    @Test
    void testKeyAboveRightBoundary_subArray() {
        Search<Integer> search = createSearch();
        Integer[] a = {1, 5, 10, 15, 20};
        // right=2, a[2]=10, key=12 > 10 → erwartet: 2+1 = 3
        assertEquals(3, search.search(a, 12, 0, 2));
    }

    /** key gleich Integer.MAX_VALUE */
    @Test
    void testKeyMaxValue() {
        Search<Integer> search = createSearch();
        Integer[] a = {1, 2, 3};
        // key > a[2] → erwartet: 3
        assertEquals(3, search.search(a, Integer.MAX_VALUE));
    }

    // -----------------------------------------------------------------------
    // Fall 3: a[left] ≤ key ≤ a[right], key nicht enthalten
    //         → kleinster Index i mit a[i-1] < key < a[i]
    // -----------------------------------------------------------------------

    /** Lücke in der Mitte des Arrays */
    @Test
    void testKeyNotFoundGapInMiddle() {
        Search<Integer> search = createSearch();
        Integer[] a = {1, 3, 5, 9, 11};
        // key=7 liegt zwischen a[2]=5 und a[3]=9 → erwartet: 3
        assertEquals(3, search.search(a, 7));
    }

    /** Lücke direkt nach dem ersten Element */
    @Test
    void testKeyNotFoundGapAfterFirst() {
        Search<Integer> search = createSearch();
        Integer[] a = {2, 10, 20, 30};
        // key=5 liegt zwischen a[0]=2 und a[1]=10 → erwartet: 1
        assertEquals(1, search.search(a, 5));
    }

    /** Lücke direkt vor dem letzten Element */
    @Test
    void testKeyNotFoundGapBeforeLast() {
        Search<Integer> search = createSearch();
        Integer[] a = {2, 10, 20, 30};
        // key=25 liegt zwischen a[2]=20 und a[3]=30 → erwartet: 3
        assertEquals(3, search.search(a, 25));
    }

    /** Fall 3 in einem eingeschränkten Teilbereich */
    @Test
    void testKeyNotFoundGap_subArray() {
        Search<Integer> search = createSearch();
        Integer[] a = {1, 3, 5, 9, 11, 15};
        // Suche in [1..4]: a[1]=3, a[4]=11, key=8 → zwischen a[2]=5 und a[3]=9 → erwartet: 3
        assertEquals(3, search.search(a, 8, 1, 4));
    }

    // -----------------------------------------------------------------------
    // Duplikate: niederwertigsten (am weitesten links stehenden) Index liefern
    // -----------------------------------------------------------------------

    /** Alle Elemente identisch, key vorhanden → Index 0 */
    @Test
    void testAllDuplicates_keyFound() {
        Search<Integer> search = createSearch();
        Integer[] a = {7, 7, 7, 7, 7};
        assertEquals(0, search.search(a, 7));
    }

    /** Duplikate am linken Rand */
    @Test
    void testDuplicatesAtLeft() {
        Search<Integer> search = createSearch();
        Integer[] a = {2, 2, 2, 5, 8};
        assertEquals(0, search.search(a, 2));
    }

    /** Duplikate in der Mitte */
    @Test
    void testDuplicatesInMiddle() {
        Search<Integer> search = createSearch();
        Integer[] a = {1, 4, 4, 4, 9};
        assertEquals(1, search.search(a, 4));
    }

    /** Duplikate am rechten Rand */
    @Test
    void testDuplicatesAtRight() {
        Search<Integer> search = createSearch();
        Integer[] a = {1, 3, 8, 8, 8};
        assertEquals(2, search.search(a, 8));
    }

    /** Sehr langes Array mit vielen Duplikaten (prüft Θ(log n), nicht sequentiell) */
    @Test
    void testLargeArrayManyDuplicates() {
        Search<Integer> search = createSearch();
        Integer[] a = new Integer[1024];
        java.util.Arrays.fill(a, 0, 512, 1);
        java.util.Arrays.fill(a, 512, 1024, 2);
        assertEquals(0, search.search(a, 1));
        assertEquals(512, search.search(a, 2));
    }

    // -----------------------------------------------------------------------
    // Einzel-Element-Array
    // -----------------------------------------------------------------------

    /** Einzel-Element – key gefunden */
    @Test
    void testSingleElementFound() {
        Search<Integer> search = createSearch();
        Integer[] a = {42};
        assertEquals(0, search.search(a, 42));
    }

    /** Einzel-Element – key kleiner → -1 */
    @Test
    void testSingleElementKeyBelow() {
        Search<Integer> search = createSearch();
        Integer[] a = {42};
        assertEquals(-1, search.search(a, 10));
    }

    /** Einzel-Element – key größer → 1 */
    @Test
    void testSingleElementKeyAbove() {
        Search<Integer> search = createSearch();
        Integer[] a = {42};
        assertEquals(1, search.search(a, 99));
    }

    // -----------------------------------------------------------------------
    // Negative Zahlen
    // -----------------------------------------------------------------------

    /** Alle negativen Zahlen, key gefunden */
    @Test
    void testNegativeNumbersFound() {
        Search<Integer> search = createSearch();
        Integer[] a = {-50, -30, -10, -5, -1};
        assertEquals(2, search.search(a, -10));
    }

    // -----------------------------------------------------------------------
    // Strings – erweiterte Fälle
    // -----------------------------------------------------------------------

    /** String kleiner als alle Elemente → -1 */
    @Test
    void testStringKeyBelow() {
        Search<String> search = createSearch();
        String[] a = {"banana", "cherry", "date", "fig"};
        // "apple" < "banana" → erwartet: -1
        assertEquals(-1, search.search(a, "apple"));
    }

    /** String größer als alle Elemente → a.length */
    @Test
    void testStringKeyAbove() {
        Search<String> search = createSearch();
        String[] a = {"banana", "cherry", "date", "fig"};
        // "mango" > "fig" → erwartet: 4
        assertEquals(4, search.search(a, "mango"));
    }

    /** String-Lücke in der Mitte (Fall 3) */
    @Test
    void testStringKeyNotFoundGap() {
        Search<String> search = createSearch();
        String[] a = {"apple", "cherry", "fig", "mango"};
        // "date" liegt zwischen "cherry"(1) und "fig"(2) → erwartet: 2
        assertEquals(2, search.search(a, "date"));
    }

    // -----------------------------------------------------------------------
    // Ausnahmen
    // -----------------------------------------------------------------------

    /** right zeigt exakt auf letztes gültiges Element – kein Fehler */
    @Test
    void testValidBoundaryExactRight() {
        Search<Integer> search = createSearch();
        Integer[] a = {0, 1, 2, 3, 4, 5};
        assertDoesNotThrow(() -> search.search(a, 5, 0, 5));
    }

    /** right > a.length-1 → ArrayIndexOutOfBoundsException */
    @Test
    void testOutOfBoundsRightExact() {
        Search<Integer> search = createSearch();
        Integer[] a = {0, 1, 2, 3, 4, 5};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> search.search(a, 3, 0, 6));
    }

    /** left < 0 → ArrayIndexOutOfBoundsException */
    @Test
    void testOutOfBoundsLeftNegative() {
        Search<Integer> search = createSearch();
        Integer[] a = {0, 1, 2, 3, 4, 5};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> search.search(a, 3, -1, 5));
    }
}
