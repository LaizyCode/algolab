package de.hska.iwi.ads.dictionary;

import de.hska.iwi.ads.solution.hashtable.Hashtable;

import java.util.Map;

public class HashMapTest extends MapTest {

    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new Hashtable<>(25);
    }
}