package de.hska.iwi.ads.solution.search;

import de.hska.iwi.ads.search.Search;

public class BinarySearch<E extends Comparable<E>> implements Search<E> {
    @Override
    public int search(E[] a, E key, int left, int right) {
        return 0;
    }

    @Override
    public int search(E[] a, E key) {
        int left = 0;
        int right = a.length;
        int middle = 0;
        int compare = 0;
        int index = -1;

        if(key.compareTo(a[left]) < 0) {
            return left - 1;
        }

        if(key.compareTo(a[right - 1]) > 0) {
            return right;
        }

        while (left < right - 1){
            middle = (left + right) / 2;
            compare = key.compareTo(a[middle]);
            switch (compare) {
                case 1:
                    left = middle + 1;
                    break;
                case 0:
                    index = middle;
                case -1:
                    right = middle;
                    break;
            }
        }

        return index;
    }
}
