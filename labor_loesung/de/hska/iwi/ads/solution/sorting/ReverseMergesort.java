package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.AbstractMergesort;

public class ReverseMergesort<E extends  Comparable<E>> extends ParentMerge<E> {

    private final ReverseArray<E> reverseArray = new ReverseArray<>();

    public void merge(E[] a, int left, int mid, int right){
        int pointerLeft = left;
        int pointerRight = right;

        reverseArray.reverse(a, mid, right);

        for(int i = left; i <= right; i++){
            if(b[pointerLeft].compareTo(b[pointerRight]) <= 0){
                b[i] =  a[pointerLeft];
                pointerLeft++;
            }
            else{
                b[i] = a[pointerRight];
                pointerRight--;
            }
        }

        for(int i = left; i<=right; i++){
            a[i] = b[i];
        }
    }
}
