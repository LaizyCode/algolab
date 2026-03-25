package de.hska.iwi.ads.solution.list;

import de.hska.iwi.ads.dictionary.AbstractDoubleLinkedList;

import java.util.Iterator;
import java.util.AbstractMap.SimpleEntry;

public class DoubleLinkedList<K extends Comparable<K>, V> extends AbstractDoubleLinkedList<K, V> {

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object o) {
        if(o == null) throw new NullPointerException();
        K key = (K) o;
        Entry<K, V> possibleEntry = getEntryByKey(key);
        if(possibleEntry != null) {
            return possibleEntry.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if(key == null) throw new NullPointerException();

        Entry<K, V> possibleEntry = getEntryByKey(key);
        if(possibleEntry != null) {
            V oldValue = possibleEntry.getValue();
            possibleEntry.setValue(value);
            return oldValue;
        }

        Entry<K, V> newEntry = new SimpleEntry<>(key, value);
        ListElement newHead = new ListElement(newEntry, null, head);
        if(head != null) {
            head.previous = newHead;
        }
        head = newHead;
        size++;
        return null;
    }

    private Entry<K, V> getEntryByKey(K key) {
        Iterator<Entry<K, V>> iter = iterator();
        while(iter.hasNext()) {
            Entry<K, V> currentEntry = iter.next();
            if(currentEntry.getKey().equals(key)) {
                return currentEntry;
            }
        }
        return null;
    }
}
