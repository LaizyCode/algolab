package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.Reverse;

public class ReverseArray<E extends Comparable<E>> implements Reverse<E> {

    @Override
    public void reverse(E[] a, int from, int to) {
        if(from < 0 || to >= a.length || (from > to)){
            return;
        }

        E[] reversed = (E[]) new Comparable[to-from + 1];
        E[] result = (E[]) new Comparable[a.length];

        int localCount = 0;
        for(int i = to; i >= from; i--){
            reversed[localCount++] = a[i];
        }
        int reverseCount = 0;

        for(int i = 0; i < a.length; i++){
            if(i > to || from > i){
                result[i] = a[i];
            }
            else{
                result[i] = reversed[reverseCount++];
            }
        }

        for(int i = 0; i< a.length; i++) {
            a[i] = result[i];
        }
    }
}
