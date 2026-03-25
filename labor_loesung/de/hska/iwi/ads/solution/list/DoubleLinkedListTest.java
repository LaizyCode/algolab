package de.hska.iwi.ads.solution.list;

import de.hska.iwi.ads.dictionary.MapTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoubleLinkedListTest extends MapTest {

    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new DoubleLinkedList<>();
    }

    @Test
    void testPutIntoEmptyListReturnsNull() {
        Map<Integer, String> map = createMap();
        assertNull(map.put(1, "eins"));
    }

    @Test
    void testPutIntoEmptyListSizeIsOne() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertEquals(1, map.size());
    }

    // =========================================================================
    // PUT – Mehrere verschiedene Schlüssel
    // =========================================================================

    @Test
    void testPutMultipleDistinctKeys() {
        Map<Integer, String> map = createMap();
        assertNull(map.put(1, "eins"));
        assertNull(map.put(2, "zwei"));
        assertNull(map.put(3, "drei"));
        assertEquals(3, map.size());
    }

    @Test
    void testPutManyElementsSizeCorrect() {
        Map<Integer, String> map = createMap();
        int n = 100;
        for (int i = 0; i < n; i++) {
            map.put(i, "val" + i);
        }
        assertEquals(n, map.size());
    }

    @Test
    void testPutManyElementsAllRetrievable() {
        Map<Integer, String> map = createMap();
        int n = 100;
        for (int i = 0; i < n; i++) {
            map.put(i, "val" + i);
        }
        for (int i = 0; i < n; i++) {
            assertEquals("val" + i, map.get(i));
        }
    }

    // =========================================================================
    // PUT – Duplikat-Schlüssel (Überschreiben)
    // =========================================================================

    @Test
    void testPutDuplicateKeyReturnsOldValue() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        String oldValue = map.put(1, "EINS_NEU");
        assertEquals("eins", oldValue);
    }

    @Test
    void testPutDuplicateKeySizeUnchanged() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        map.put(1, "EINS_NEU");
        assertEquals(1, map.size());
    }

    @Test
    void testPutDuplicateKeyNewValueRetrievable() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        map.put(1, "EINS_NEU");
        assertEquals("EINS_NEU", map.get(1));
    }

    @Test
    void testPutMultipleOverwritesSameKey() {
        Map<Integer, String> map = createMap();
        map.put(1, "a");
        assertEquals("a", map.put(1, "b"));
        assertEquals("b", map.put(1, "c"));
        assertEquals("c", map.get(1));
        assertEquals(1, map.size());
    }

    @Test
    void testPutOverwriteOtherKeysUnaffected() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        map.put(2, "zwei");
        map.put(3, "drei");
        map.put(2, "ZWEI_NEU");
        assertEquals("eins", map.get(1));
        assertEquals("ZWEI_NEU", map.get(2));
        assertEquals("drei", map.get(3));
    }

    @Test
    void testPutOverwriteAfterManyInserts() {
        Map<Integer, String> map = createMap();
        for (int i = 0; i < 50; i++) {
            map.put(i, "val" + i);
        }
        // Erstes Element (am Ende der Kette)
        assertEquals("val0", map.put(0, "NEU_0"));
        assertEquals("NEU_0", map.get(0));

        // Letztes Element (am Anfang der Kette)
        assertEquals("val49", map.put(49, "NEU_49"));
        assertEquals("NEU_49", map.get(49));

        // Mittleres Element
        assertEquals("val25", map.put(25, "NEU_25"));
        assertEquals("NEU_25", map.get(25));

        assertEquals(50, map.size());
    }

    // =========================================================================
    // PUT – Null-Werte
    // =========================================================================

    @Test
    void testPutNullValue() {
        Map<Integer, String> map = createMap();
        assertNull(map.put(1, null));
        assertEquals(1, map.size());
    }

    @Test
    void testPutOverwriteNullValueReturnsNull() {
        Map<Integer, String> map = createMap();
        map.put(1, null);
        assertNull(map.put(1, "eins"));
        assertEquals("eins", map.get(1));
    }

    @Test
    void testPutOverwriteWithNullReturnsOldValue() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertEquals("eins", map.put(1, null));
        assertNull(map.get(1));
    }

    // =========================================================================
    // GET – Leere und nicht-leere Liste
    // =========================================================================

    @Test
    void testGetFromEmptyListReturnsNull() {
        Map<Integer, String> map = createMap();
        assertNull(map.get(1));
    }

    @Test
    void testGetExistingKeyReturnsValue() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertEquals("eins", map.get(1));
    }

    @Test
    void testGetNonExistingKeyReturnsNull() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertNull(map.get(2));
    }

    @Test
    void testGetSingleElementList() {
        Map<Integer, String> map = createMap();
        map.put(42, "antwort");
        assertEquals("antwort", map.get(42));
    }

    // =========================================================================
    // GET – Position in der Kette (Anfang, Mitte, Ende)
    // =========================================================================

    @Test
    void testGetFirstInsertedElement() {
        // Erstes eingefügtes Element liegt am Ende der Kette
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        map.put(2, "zwei");
        map.put(3, "drei");
        assertEquals("eins", map.get(1));
    }

    @Test
    void testGetLastInsertedElement() {
        // Letztes eingefügtes Element liegt am Anfang der Kette (head)
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        map.put(2, "zwei");
        map.put(3, "drei");
        assertEquals("drei", map.get(3));
    }

    @Test
    void testGetMiddleElement() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        map.put(2, "zwei");
        map.put(3, "drei");
        assertEquals("zwei", map.get(2));
    }

    // =========================================================================
    // GET – Idempotenz und Seiteneffektfreiheit
    // =========================================================================

    @Test
    void testGetSameKeyMultipleTimesIdempotent() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertEquals("eins", map.get(1));
        assertEquals("eins", map.get(1));
        assertEquals("eins", map.get(1));
    }

    @Test
    void testGetDoesNotChangeSize() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        map.put(2, "zwei");
        map.get(1);
        map.get(2);
        map.get(99);
        assertEquals(2, map.size());
    }

    // =========================================================================
    // GET und PUT – Zusammenspiel
    // =========================================================================

    @Test
    void testGetAfterOverwriteReturnsNewValue() {
        Map<Integer, String> map = createMap();
        map.put(1, "alt");
        map.put(1, "neu");
        assertEquals("neu", map.get(1));
    }

    @Test
    void testMixedPutGetOperations() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertEquals("eins", map.get(1));

        map.put(2, "zwei");
        assertEquals("eins", map.get(1));
        assertEquals("zwei", map.get(2));

        map.put(1, "EINS_NEU");
        assertEquals("EINS_NEU", map.get(1));
        assertEquals("zwei", map.get(2));
        assertEquals(2, map.size());

        map.put(3, "drei");
        assertEquals("EINS_NEU", map.get(1));
        assertEquals("zwei", map.get(2));
        assertEquals("drei", map.get(3));
        assertEquals(3, map.size());
    }

    // =========================================================================
    // Schlüssel-Grenzfälle (negativ, 0, MAX/MIN)
    // =========================================================================

    @Test
    void testNegativeKeys() {
        Map<Integer, String> map = createMap();
        map.put(-1, "minus_eins");
        map.put(-100, "minus_hundert");
        assertEquals("minus_eins", map.get(-1));
        assertEquals("minus_hundert", map.get(-100));
    }

    @Test
    void testZeroKey() {
        Map<Integer, String> map = createMap();
        map.put(0, "null_wert");
        assertEquals("null_wert", map.get(0));
    }

    @Test
    void testMinAndMaxIntegerKeys() {
        Map<Integer, String> map = createMap();
        map.put(Integer.MAX_VALUE, "max");
        map.put(Integer.MIN_VALUE, "min");
        assertEquals("max", map.get(Integer.MAX_VALUE));
        assertEquals("min", map.get(Integer.MIN_VALUE));
        assertEquals(2, map.size());
    }

    // =========================================================================
    // Generische Typen – String-Schlüssel
    // =========================================================================

    @Test
    void testStringKeys() {
        Map<String, Integer> map = createMap();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertEquals(1, map.get("a"));
        assertEquals(2, map.get("b"));
        assertEquals(3, map.get("c"));
        assertNull(map.get("d"));
    }

    @Test
    void testStringKeyOverwrite() {
        Map<String, Integer> map = createMap();
        map.put("key", 10);
        assertEquals(10, map.put("key", 20));
        assertEquals(20, map.get("key"));
        assertEquals(1, map.size());
    }

    // =========================================================================
    // Leere Liste / Map-Methoden
    // =========================================================================

    @Test
    void testEmptyListSizeIsZero() {
        Map<Integer, String> map = createMap();
        assertEquals(0, map.size());
    }

    @Test
    void testEmptyListIsEmpty() {
        Map<Integer, String> map = createMap();
        assertTrue(map.isEmpty());
    }

    @Test
    void testAfterPutIsNotEmpty() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertFalse(map.isEmpty());
    }

    @Test
    void testContainsKeyExisting() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertTrue(map.containsKey(1));
    }

    @Test
    void testContainsKeyNonExisting() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertFalse(map.containsKey(2));
    }

    // =========================================================================
    // Remove – UnsupportedOperationException
    // =========================================================================

    @Test
    void testRemoveThrowsUnsupportedOperationException() {
        Map<Integer, String> map = createMap();
        map.put(1, "eins");
        assertThrows(UnsupportedOperationException.class, () -> map.remove(1));
    }
}
