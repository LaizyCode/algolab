package de.hska.iwi.ads.dictionary;

import de.hska.iwi.ads.solution.list.DoubleLinkedList;

import java.util.Map;

public class LinkedListTest extends MapTest{
    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new DoubleLinkedList<>();
    }
}
