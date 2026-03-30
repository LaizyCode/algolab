package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.AbstractMergesort;
import de.hska.iwi.ads.sorting.Sort;

public class Mergesort<E extends Comparable<E>> extends AbstractMergesort<E> implements Sort<E> {

    @Override
    protected void mergesort(E[] a, int left, int right) {
        if(left < right){
            int mid = (left + right) / 2;
            mergesort(a, left, mid);
            mergesort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    public void merge(E[] a, int left, int mid, int right){
        int pointerLeft = left;
        int pointerRight = mid + 1;

        for(int i = left; i <= right; i++){
            if( pointerRight > right || (pointerLeft <= mid && a[pointerLeft].compareTo(a[pointerRight]) <= 0)){
                b[i] =  a[pointerLeft];
                pointerLeft++;
            }
            else{
                b[i] = a[pointerRight];
                pointerRight++;
            }
        }

        for(int i = left; i<=right; i++){
            a[i] = b[i];
        }
    }
}
