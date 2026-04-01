package de.hska.iwi.ads.solution.hashtable;

import de.hska.iwi.ads.dictionary.AbstractDictionary;
import de.hska.iwi.ads.dictionary.MapTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class HashtableTest extends MapTest {

    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return createMap(20);
    }

    private <K extends Comparable<K>, V> Map<K, V> createMap(int size) {
        return new Hashtable<>(size);
    }

    // =====================================================================
    // Tests for DictionaryFullException
    // =====================================================================

    @Test
    void testPutFullTableThrowsException() {
        Map<Integer, String> map = createMap(3);
        map.put(1, "Eins");
        map.put(2, "Zwei");
        map.put(3, "Drei");
        assertThrows(AbstractDictionary.DictionaryFullException.class,
                () -> map.put(4, "Vier"));
    }

    @Test
    void testPutFullTableSize1() {
        Map<Integer, String> map = createMap(1);
        map.put(1, "Eins");
        assertThrows(AbstractDictionary.DictionaryFullException.class,
                () -> map.put(2, "Zwei"));
    }

    @Test
    void testPutFullTableWithCollisions() {
        // Keys that likely collide (same hash % table size)
        Map<Integer, String> map = createMap(5);
        map.put(0, "Null");
        map.put(5, "Fünf");   // hash collision with 0 (mod 5)
        map.put(10, "Zehn");  // hash collision with 0 (mod 5)
        map.put(1, "Eins");
        map.put(2, "Zwei");
        assertThrows(AbstractDictionary.DictionaryFullException.class,
                () -> map.put(3, "Drei"));
    }

    // =====================================================================
    // Tests for UnsupportedOperationException on remove
    // =====================================================================

    @Test
    void testRemoveThrowsUnsupportedOperationException() {
        Map<Integer, String> map = createMap(10);
        map.put(1, "Eins");
        assertThrows(UnsupportedOperationException.class,
                () -> map.remove(1));
    }

    @Test
    void testRemoveNonExistingThrowsUnsupportedOperationException() {
        Map<Integer, String> map = createMap(10);
        assertThrows(UnsupportedOperationException.class,
                () -> map.remove(42));
    }

    // =====================================================================
    // Tests for put return value (old value on update, null on insert)
    // =====================================================================

    @Test
    void testPutReturnsNullOnNewKey() {
        Map<Integer, String> map = createMap(10);
        assertNull(map.put(1, "Eins"));
    }

    @Test
    void testPutReturnsOldValueOnUpdate() {
        Map<Integer, String> map = createMap(10);
        map.put(1, "Eins");
        String oldValue = map.put(1, "One");
        assertEquals("Eins", oldValue);
        assertEquals("One", map.get(1));
    }

    @Test
    void testPutUpdateDoesNotChangeSize() {
        Map<Integer, String> map = createMap(10);
        map.put(1, "Eins");
        map.put(2, "Zwei");
        assertEquals(2, map.size());
        map.put(1, "One");
        assertEquals(2, map.size());
    }

    @Test
    void testPutMultipleUpdates() {
        Map<Integer, String> map = createMap(10);
        map.put(1, "Eins");
        assertEquals("Eins", map.put(1, "One"));
        assertEquals("One", map.put(1, "Uno"));
        assertEquals("Uno", map.get(1));
        assertEquals(1, map.size());
    }

    // =====================================================================
    // Tests for update on full table (must still terminate)
    // =====================================================================

    @Test
    void testUpdateExistingKeyInFullTable() {
        Map<Integer, String> map = createMap(3);
        map.put(1, "Eins");
        map.put(2, "Zwei");
        map.put(3, "Drei");
        // Update existing key in a full table must work
        String old = map.put(2, "Two");
        assertEquals("Zwei", old);
        assertEquals("Two", map.get(2));
        assertEquals(3, map.size());
    }

    @Test
    void testGetInFullTable() {
        Map<Integer, String> map = createMap(3);
        map.put(1, "Eins");
        map.put(2, "Zwei");
        map.put(3, "Drei");
        // get must terminate even in a full table
        assertEquals("Eins", map.get(1));
        assertEquals("Zwei", map.get(2));
        assertEquals("Drei", map.get(3));
    }

    @Test
    void testGetNonExistingKeyInFullTable() {
        Map<Integer, String> map = createMap(3);
        map.put(1, "Eins");
        map.put(2, "Zwei");
        map.put(3, "Drei");
        // get for non-existing key must terminate, not loop forever
        assertNull(map.get(99));
    }

    // =====================================================================
    // Tests for quadratic probing / collision handling
    // =====================================================================

    @Test
    void testCollidingKeysStoredAndRetrieved() {
        // Use a small table to force collisions
        Map<Integer, String> map = createMap(7);
        map.put(0, "Null");
        map.put(7, "Sieben");   // collides with 0 (mod 7)
        map.put(14, "Vierzehn"); // collides with 0 (mod 7)
        assertEquals("Null", map.get(0));
        assertEquals("Sieben", map.get(7));
        assertEquals("Vierzehn", map.get(14));
        assertEquals(3, map.size());
    }

    @Test
    void testManyCollidingKeys() {
        Map<Integer, String> map = createMap(11);
        // All keys have hash % 11 == 0
        map.put(0, "A");
        map.put(11, "B");
        map.put(22, "C");
        map.put(33, "D");
        map.put(44, "E");
        assertEquals(5, map.size());
        assertEquals("A", map.get(0));
        assertEquals("B", map.get(11));
        assertEquals("C", map.get(22));
        assertEquals("D", map.get(33));
        assertEquals("E", map.get(44));
    }

    @Test
    void testUpdateCollidingKey() {
        Map<Integer, String> map = createMap(7);
        map.put(0, "Null");
        map.put(7, "Sieben");
        String old = map.put(7, "Seven");
        assertEquals("Sieben", old);
        assertEquals("Seven", map.get(7));
        assertEquals("Null", map.get(0));
        assertEquals(2, map.size());
    }

    // =====================================================================
    // Edge case tests
    // =====================================================================

    @Test
    void testSingleElementTable() {
        Map<Integer, String> map = createMap(1);
        assertNull(map.put(42, "FortyTwo"));
        assertEquals(1, map.size());
        assertEquals("FortyTwo", map.get(42));
    }

    @Test
    void testGetOnEmptyTable() {
        Map<Integer, String> map = createMap(10);
        assertNull(map.get(1));
    }

    @Test
    void testNullValuePut() {
        Map<Integer, String> map = createMap(10);
        map.put(1, null);
        assertEquals(1, map.size());
        assertNull(map.get(1));
    }

    @Test
    void testLargeNumberOfEntries() {
        Map<Integer, String> map = createMap(101);
        for (int i = 0; i < 50; i++) {
            map.put(i, "Wert" + i);
        }
        assertEquals(50, map.size());
        for (int i = 0; i < 50; i++) {
            assertEquals("Wert" + i, map.get(i));
        }
    }

    @Test
    void testNegativeKeys() {
        Map<Integer, String> map = createMap(10);
        map.put(-1, "MinusEins");
        map.put(-2, "MinusZwei");
        assertEquals("MinusEins", map.get(-1));
        assertEquals("MinusZwei", map.get(-2));
        assertEquals(2, map.size());
    }

    @Test
    void testStringKeys() {
        Map<String, Integer> map = createMap(10);
        map.put("Eins", 1);
        map.put("Zwei", 2);
        map.put("Drei", 3);
        assertEquals(1, map.get("Eins"));
        assertEquals(2, map.get("Zwei"));
        assertEquals(3, map.get("Drei"));
        assertNull(map.get("Vier"));
        assertEquals(3, map.size());
    }
}