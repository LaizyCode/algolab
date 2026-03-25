package de.hska.iwi.ads.solution.search;

import de.hska.iwi.ads.search.Search;

public class BinarySearch<E extends Comparable<E>> implements Search<E> {
    @Override
    public int search(E[] a, E key, int left, int right) {
        // Check ob der Value out of Bounds ist
        if(key.compareTo(a[left]) < 0) {
            return left - 1;
        }

        if(key.compareTo(a[right]) > 0) {
            return right + 1;
        }

        int index = -1;

        // Verschiebe links und rechts so lange, bis right < links gilt
        while (left <= right){
            int middle = (left + right) / 2;
            int compare = key.compareTo(a[middle]);
            if (compare > 0) {
                left = middle + 1;
            } else if (compare == 0) {
                // Wenn Value gefunden wird iteriere nach links weiter,
                // um den kleinstmöglichen Index zu finden
                index = middle;
                right = middle - 1;
            } else {
                right = middle - 1;
            }
        }

        return (index != -1) ? index : left;
    }

    @Override
    public int search(E[] a, E key) {
        return search(a, key, 0, a.length - 1);
    }
}
