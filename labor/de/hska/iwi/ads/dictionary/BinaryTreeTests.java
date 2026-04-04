package de.hska.iwi.ads.dictionary;

import de.hska.iwi.ads.solution.tree.BinarySearchTree;

import java.util.Map;

public class BinaryTreeTests extends MapTest{
    @Override
    public <K extends Comparable<K>, V> Map<K, V> createMap() {
        return new BinarySearchTree<>();
    }
}
