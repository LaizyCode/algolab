package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.AbstractMergesort;
import de.hska.iwi.ads.sorting.Sort;

public abstract class ParentMerge<E extends Comparable<E>> extends AbstractMergesort<E> implements Sort<E> {

    @Override
    protected void mergesort(E[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergesort(a, left, mid);
            mergesort(a, mid + 1, right);
            this.merge(a, left, mid, right);
        }
    }

    protected abstract void merge(E[] a, int left, int mid, int right);
}

