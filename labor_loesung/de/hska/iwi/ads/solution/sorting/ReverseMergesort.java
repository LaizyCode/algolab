package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.AbstractMergesort;
import de.hska.iwi.ads.sorting.Reverse;

public class ReverseMergesort<E extends Comparable<E>> extends AbstractMergesort<E> {

    private final Reverse<E> reverse = new ReverseArray<>();

    @Override
    protected void mergesort(E[] a, int left, int right) {
        if(left < right) {
            int middle = (left + right) / 2;
            mergesort(a, left, middle);
            mergesort(a, middle + 1, right);
            merge(a, left, middle, right);
        }
    }

    private void merge(E[] a, int left, int middle, int right) {

        this.reverse.reverse(a, middle + 1, right);

        int leftIndex = left;
        int rightIndex = right;

        for(int i = left; i <= right; i++) {
            if(a[leftIndex].compareTo(a[rightIndex]) <= 0) {
                b[i] = a[leftIndex];
                leftIndex++;
            } else {
                b[i] = a[rightIndex];
                rightIndex--;
            }
        }

        for(int i = left; i <= right; i++) {
            a[i] = b[i];
        }
    }
}
