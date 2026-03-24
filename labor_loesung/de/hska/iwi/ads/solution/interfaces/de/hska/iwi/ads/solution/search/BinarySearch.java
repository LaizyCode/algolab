package de.hska.iwi.ads.solution.interfaces.de.hska.iwi.ads.solution.search;

import de.hska.iwi.ads.search.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinarySearch implements Search<Integer> {

    @Override
    public int search(Integer[] a, Integer key, int left, int right) {
        return 0;
    }

    @Override
    public int search(Integer[] a, Integer key) {
        if(a[0] > key){
            return -1;
        }
        if(a[a.length - 1] < key){
            return a.length;
        }

        int left = 0;
        int right = a.length;
        int compare;
        int mid = (left + right) / 2;
        while (left < right) {
            mid = (left + right) / 2;
            compare = a[mid].compareTo(key);
            switch (compare) {
                case 0:
                case -1:
                    right = mid;
                    break;
                case 1:
                    left = mid + 1;
                    break;
            }
        }

        if(a[left].compareTo(key) != 0){
            mid = left;
        }

        return mid;
    }
}
