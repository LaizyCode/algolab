package de.hska.iwi.ads.solution.hashtable;

import de.hska.iwi.ads.dictionary.AbstractDictionary;
import de.hska.iwi.ads.dictionary.AbstractHashMap;

import java.util.Iterator;

public class Hashtable<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {
    public Hashtable(int capacity) {
        super(capacity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object o) {
        if(o == null) throw new NullPointerException();
        K key = (K) o;

        int index = Math.floorMod(key.hashCode(), hashtable.length);

        for(int i = 0; i < hashtable.length; i++){
            int probe = Math.floorMod(index + i * i, hashtable.length);

            if(hashtable[probe] == null){
                return null;
            }
            if(hashtable[probe].getKey().equals(key)){
                return hashtable[probe].getValue();
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if(key == null) throw new NullPointerException();

        int index = Math.floorMod(key.hashCode(), hashtable.length);

        for(int i = 0; i < hashtable.length; i++){
            int probe = Math.floorMod(index + i + i, hashtable.length);

            if(hashtable[probe] == null){
                size++;
                hashtable[probe] = new SimpleEntry<>(key, value);
                return null;
            }
            if(hashtable[probe].getKey().equals(key)){
                V oldValue = hashtable[probe].getValue();
                hashtable[probe].setValue(value);
                return oldValue;
            }
        }
        throw new AbstractDictionary.DictionaryFullException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }
}
