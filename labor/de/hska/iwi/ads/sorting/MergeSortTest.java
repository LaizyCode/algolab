package de.hska.iwi.ads.sorting;

import de.hska.iwi.ads.solution.sorting.Mergesort;

public class MergeSortTest extends SortTest {

    @Override
    public <E extends Comparable<E>> Sort<E> createSort() {
        return new Mergesort<E>();
    }
}