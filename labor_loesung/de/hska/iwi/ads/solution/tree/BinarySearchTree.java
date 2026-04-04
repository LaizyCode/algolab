package de.hska.iwi.ads.solution.tree;

import de.hska.iwi.ads.dictionary.AbstractBinaryTree;

public class BinarySearchTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {

    public V put(K key, V value){
        if(key == null){
            throw new NullPointerException();
        }

        if(root == null){
            root = new Node(key, value);
            size++;
            return null;
        }

        Node currentNode = root;

        while(true){
            if(currentNode == null){
                return null;
            }
            int comparison = currentNode.entry.getKey().compareTo(key);

            if(comparison == 0){
                V oldValue = currentNode.entry.getValue();
                currentNode.entry.setValue(value);
                return oldValue;
            }

            if (comparison < 0) {
                if (currentNode.right == null){
                    currentNode.right = new Node(key, value);
                    size++;
                    return null;
                }
                currentNode = currentNode.right;
            }

            if (comparison > 0) {
                if (currentNode.left == null){
                    currentNode.left = new Node(key, value);
                    size++;
                    return null;
                }
                currentNode = currentNode.left;
            }

        }
    }

    public V get(Object o){
        K key = (K) o;
        if(key == null){
            throw new NullPointerException();
        }
        Node currentNode = root;
        while (true){
            if(currentNode == null){
                return null;
            }
            int comparison = currentNode.entry.getKey().compareTo(key);

            if (comparison == 0){
                return currentNode.entry.getValue();
            }

            if (comparison < 0){
                currentNode = currentNode.right;
            }

            if (comparison > 0){
                currentNode = currentNode.left;
            }
        }
    }
}
