package de.hska.iwi.ads.solution.list;

import de.hska.iwi.ads.dictionary.MapTest;

import java.util.Map;

public class DoubleLinkedListImplTest extends MapTest {
    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new DoubleLinkedList<>();
    }
}
