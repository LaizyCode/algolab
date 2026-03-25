package de.hska.iwi.ads.solution.sorting;

import de.hska.iwi.ads.sorting.Reverse;

/**
 * Tests for the ReverseArray implementation.
 * Inherits all tests from {@link ReverseTest}.
 */
public class ReverseArrayTest extends ReverseTest {

    @Override
    public <E extends Comparable<E>> Reverse<E> createReverse() {
        return new ReverseArray<>();
    }
}
