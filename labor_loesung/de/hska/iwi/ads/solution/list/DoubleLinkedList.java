package de.hska.iwi.ads.solution.list;

import de.hska.iwi.ads.dictionary.AbstractDoubleLinkedList;

public class DoubleLinkedList<K extends Comparable<K>, V> extends AbstractDoubleLinkedList<K, V> {
    public V get(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        K key = (K) o;
        ListElement listElement = head;
        Entry<K, V> entry = searchEntry(key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        Entry<K, V> searchedEntry = searchEntry(key);
        if (searchedEntry != null) {
            V oldValue = searchedEntry.getValue();
            searchedEntry.setValue(value);
            return oldValue;
        }
        Entry<K, V> newEntry = new SimpleEntry<>(key, value);
        ListElement listElement;
        if (head != null) {
            listElement = new ListElement(newEntry, null, head);
            head.previous = listElement;
        } else {
            listElement = new ListElement(newEntry, null, null);
        }
        size++;
        head = listElement;

        return null;
    }

    public Entry<K, V> searchEntry(K key) {
        ListElement listElement = head;
        if (head == null) {
            return null;
        }
        while (listElement != null) {
            if (listElement.entry.getKey().equals(key)) {
                return listElement.entry;
            }
            listElement = listElement.next;
        }
        return null;
    }
}
