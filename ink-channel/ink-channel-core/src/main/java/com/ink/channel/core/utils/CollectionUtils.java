package com.ink.channel.core.utils;



import java.util.*;

/**
 * Static helper class for use dealing with Collections.
 * 
 * @author Jerry
 * 
 * @version 1.0
 * 
 *   TIME 2015/8/24
 * 
 */
public class CollectionUtils {

 

    public static <E> Set<E> asSet(@SuppressWarnings("unchecked") E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptySet();
        }
        LinkedHashSet<E> set = new LinkedHashSet<E>(elements.length * 4 / 3 + 1);
        Collections.addAll(set, elements);
        return set;
    }


    public static boolean isEmpty(Collection<? extends Object> c) {
        return c == null || c.isEmpty();
    }


    public static boolean isEmpty(Map<String,Object> m) {
        return m == null || m.isEmpty();
    }

    public static int size(Collection<? extends Object> c) {
        return c != null ? c.size() : 0;
    }


    public static int size(Map<String,Object> m) {
        return m != null ? m.size() : 0;
    }

    public static <E> List<E> asList(@SuppressWarnings("unchecked") E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptyList();
        }
        // Avoid integer overflow when a large array is passed in
        int capacity = computeListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }


    static int computeListCapacity(int arraySize) {
        return (int) Math.min(5L + arraySize + (arraySize / 10), Integer.MAX_VALUE);
    }
}
