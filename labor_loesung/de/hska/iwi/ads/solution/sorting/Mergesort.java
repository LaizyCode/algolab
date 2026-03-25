package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.AbstractMergesort;

public class Mergesort<E extends Comparable<E>> extends AbstractMergesort<E> {
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
        int leftIndex = left;
        int rightIndex = middle + 1;

        for(int i = left; i <= right; i++) {
            if (rightIndex > right ||
                    (leftIndex <= middle && a[leftIndex].compareTo(a[rightIndex]) <= 0)) {
                b[i] = a[leftIndex];
                leftIndex++;
            } else {
                b[i] = a[rightIndex];
                rightIndex++;
            }
        }

        for(int i = left; i <= right; i++) {
            a[i] = b[i];
        }
    }
}
