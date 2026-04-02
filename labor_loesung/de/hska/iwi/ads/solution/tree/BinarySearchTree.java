package de.hska.iwi.ads.solution.tree;

import de.hska.iwi.ads.dictionary.AbstractBinaryTree;

public class BinarySearchTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object o) {
        if(o == null) throw new NullPointerException();
        if(size == 0) return null;

        K key = (K) o;

        Node currentNode = root;
        while(currentNode != null) {
            int compare = key.compareTo(currentNode.entry.getKey());
            if(compare < 0 ) {
                currentNode = currentNode.left;
            } else if(compare > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode.entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if(key == null) throw new NullPointerException();
        if(size == 0) {
            root = new Node(key, value);
            size++;
            return null;
        }

        Node currentNode = root;
        while(true) {
            int compare = key.compareTo(currentNode.entry.getKey());
            if(compare < 0) {
                if(currentNode.left == null) {
                    currentNode.left = new Node(key, value);
                    size++;
                    return null;
                } else {
                    currentNode = currentNode.left;
                }
            } else if (compare > 0) {
                if(currentNode.right == null) {
                    currentNode.right = new Node(key, value);
                    size++;
                    return null;
                } else {
                    currentNode = currentNode.right;
                }
            } else {
                V oldValue = currentNode.entry.getValue();
                currentNode.entry.setValue(value);
                return oldValue;
            }
        }
    }

}
