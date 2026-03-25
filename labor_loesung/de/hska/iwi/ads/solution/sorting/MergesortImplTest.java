package de.hska.iwi.ads.solution.sorting;

public class MergesortImplTest extends MergesortTest {

    @Override
    public <E extends Comparable<E>> Mergesort<E> createSorter() {
        return new Mergesort<>();
    }
}