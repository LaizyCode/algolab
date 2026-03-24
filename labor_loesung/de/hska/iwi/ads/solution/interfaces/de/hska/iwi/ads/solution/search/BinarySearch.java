package de.hska.iwi.ads.solution.interfaces.de.hska.iwi.ads.solution.search;

import de.hska.iwi.ads.search.Search;

import java.util.Objects;

public class BinarySearch implements Search<Integer> {

    @Override
    public int search(Integer[] a, Integer key) {
        int left = 0;
        int right = a.length;
        int compare;
        while(left < right){
            int mid = left + right / 2;
            compare = a[mid].compareTo(key);

            switch (compare){
                case 0:
                    return mid;
                    break;
                case 1:
                    right = mid;
                    break;
                case -1:
                    left = mid+1;
                    break;
            }
        }
    }
}
