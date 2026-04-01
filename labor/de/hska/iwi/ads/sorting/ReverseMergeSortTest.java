package de.hska.iwi.ads.sorting;

import de.hska.iwi.ads.solution.sorting.ReverseMergesort;

public class ReverseMergeSortTest extends SortTest{

    @Override
    public <E extends Comparable<E>> Sort<E> createSort() {
        return new ReverseMergesort<>();
    }
}
