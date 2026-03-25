package de.hska.iwi.ads.solution.sorting;

public class ReverseMergesortImplTest extends MergesortTest {

    @Override
    public <E extends Comparable<E>> ReverseMergesort<E> createSorter() {
        return new ReverseMergesort<>();
    }
}