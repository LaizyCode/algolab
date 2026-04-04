package de.hska.iwi.ads.solution.hashtable;

import de.hska.iwi.ads.dictionary.AbstractHashMap;

public class Hashtable<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {
    public Hashtable(int capacity) {
        super(capacity);
    }

    public V get(Object o) {
        K key = (K) o;
        if (key == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < hashtable.length; i++) {
            int currentHash = hash((key.hashCode() + (i * i)), hashtable.length);
            if(hashtable[currentHash] == null){
                return null;
            }

            if (hashtable[currentHash].getKey().equals(key)){
                return hashtable[currentHash].getValue();
            }
        }

        return null;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < hashtable.length; i++) {
            int currentHash = hash((key.hashCode() + (i * i)), hashtable.length);
            if (hashtable[currentHash] == null) {
                hashtable[currentHash] = new SimpleEntry<>(key, value);
                size++;
                return null;
            }
            if (hashtable[currentHash].getKey().equals(key)) {
                V oldValue = hashtable[currentHash].getValue();
                hashtable[currentHash].setValue(value);
                return oldValue;
            }
        }

        throw new DictionaryFullException();
    }

    private int hash(int keyHah, int length) {
        return Math.floorMod(keyHah, length);
    }

}
