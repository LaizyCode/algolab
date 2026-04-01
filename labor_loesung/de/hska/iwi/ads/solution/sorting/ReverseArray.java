package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.Reverse;

public class ReverseArray<E extends Comparable<E>> implements Reverse<E> {

    @Override
    public void reverse(E[] a, int from, int to) {
        if(from < 0 || to >= a.length) return;

        while (from < to) {
            E temp = a[from];
            a[from] = a[to];
            a[to] = temp;
            from++;
            to--;
        }
    }
}
