package de.hska.iwi.ads.solution.tree;

import de.hska.iwi.ads.dictionary.MapTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest extends MapTest {

    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new BinarySearchTree<>();
    }

    // --- put: Rückgabewert-Tests ---

    @Test
    void putReturnsNullForNewKey() {
        Map<Integer, String> map = createMap();
        assertNull(map.put(1, "Eins"));
    }

    @Test
    void putReturnsOldValueForExistingKey() {
        Map<Integer, String> map = createMap();
        map.put(1, "Eins");
        assertEquals("Eins", map.put(1, "One"));
    }

    @Test
    void putOverwriteUpdatesValue() {
        Map<Integer, String> map = createMap();
        map.put(1, "Eins");
        map.put(1, "One");
        assertEquals("One", map.get(1));
    }

    @Test
    void putOverwriteDoesNotChangeSize() {
        Map<Integer, String> map = createMap();
        map.put(1, "Eins");
        map.put(1, "One");
        assertEquals(1, map.size());
    }

    // --- Einfügereihenfolge / Baumstruktur ---

    @Test
    void putAscendingOrder() {
        Map<Integer, String> map = createMap();
        for (int i = 1; i <= 5; i++) {
            map.put(i, "V" + i);
        }
        assertEquals(5, map.size());
        for (int i = 1; i <= 5; i++) {
            assertEquals("V" + i, map.get(i));
        }
    }

    @Test
    void putDescendingOrder() {
        Map<Integer, String> map = createMap();
        for (int i = 5; i >= 1; i--) {
            map.put(i, "V" + i);
        }
        assertEquals(5, map.size());
        for (int i = 1; i <= 5; i++) {
            assertEquals("V" + i, map.get(i));
        }
    }

    // --- get: Nicht vorhandene Schlüssel ---

    @Test
    void getOnEmptyMap() {
        Map<Integer, String> map = createMap();
        assertNull(map.get(42));
    }

    @Test
    void getKeyNotPresent() {
        Map<Integer, String> map = createMap();
        map.put(10, "Zehn");
        map.put(20, "Zwanzig");
        assertNull(map.get(15));
    }

    // --- Einzelnes Element ---

    @Test
    void singleElementGetAndSize() {
        Map<Integer, String> map = createMap();
        map.put(5, "Fünf");
        assertEquals(1, map.size());
        assertEquals("Fünf", map.get(5));
    }

    // --- String-Schlüssel (Comparable) ---

    @Test
    void stringKeys() {
        Map<String, Integer> map = createMap();
        map.put("Apfel", 1);
        map.put("Birne", 2);
        map.put("Kirsche", 3);
        assertEquals(3, map.size());
        assertEquals(2, map.get("Birne"));
        assertNull(map.get("Banane"));
    }

    // --- Mehrfaches Überschreiben ---

    @Test
    void multipleOverwrites() {
        Map<Integer, String> map = createMap();
        map.put(1, "A");
        map.put(1, "B");
        map.put(1, "C");
        assertEquals(1, map.size());
        assertEquals("C", map.get(1));
    }

    // --- NullPointerException ---

    @Test
    void putNullValue() {
        Map<Integer, String> map = createMap();
        // null als Value sollte kein Problem sein
        map.put(1, null);
        assertEquals(1, map.size());
        assertNull(map.get(1));
    }

    @Test
    void getNullKeyThrows() {
        Map<Integer, String> map = createMap();
        assertThrows(NullPointerException.class, () -> map.get(null));
    }

    @Test
    void putNullKeyThrows() {
        Map<Integer, String> map = createMap();
        assertThrows(NullPointerException.class, () -> map.put(null, "X"));
    }

    // --- Größerer Baum ---

    @Test
    void largerTree() {
        Map<Integer, String> map = createMap();
        for (int i = 0; i < 100; i++) {
            map.put(i, "V" + i);
        }
        assertEquals(100, map.size());
        assertEquals("V0", map.get(0));
        assertEquals("V50", map.get(50));
        assertEquals("V99", map.get(99));
        assertNull(map.get(100));
    }
}