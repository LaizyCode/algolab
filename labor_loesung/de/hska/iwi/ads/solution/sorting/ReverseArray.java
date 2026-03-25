package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.Reverse;

public class ReverseArray<E extends Comparable<E>> implements Reverse<E> {
    @Override
    public void reverse(E[] a, int from, int to) {
        if(from < 0 || to >= a.length) return;

        while (from < to) {
            E helper = a[to];
            a[to] = a[from];
            a[from] = helper;
            from++;
            to--;
        }
    }
}
