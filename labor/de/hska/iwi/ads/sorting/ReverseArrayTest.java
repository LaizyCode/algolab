package de.hska.iwi.ads.sorting;

import de.hska.iwi.ads.solution.sorting.ReverseArray;

public class ReverseArrayTest extends ReverseTest {

    @Override
    public <E extends Comparable<E>> Reverse<E> createReverse() {
        return new ReverseArray<E>();
    }
}