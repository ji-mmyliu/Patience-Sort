
/**
 * Names: Jimmy Liu, Josh Liu, Di Hu
 * Date: March 4, 2021
 * Teacher: Ms. Krasteva
 * Description: This class contains the methods require to sort an integer array using the Patience Sort algorithm
 */

import java.util.ArrayList;

/**
 * This class is used to sort an array using the Patience Sort algorithm
 * 
 * @param E The Object type that the array to sort is going to be in
 * @author Jimmy Liu
 */
public class PatienceSort<E> {
    /**
     * This 2D arraylist contains arraylists of arraylists which store the patience
     * sort buckets
     */
    private ArrayList<ArrayList<E>> buckets;

    /** This 2D arraylist contains a merged arraylist */
    private ArrayList<ArrayList<E>> buckets1;

    /** This arraylist contains the top element of each bucket */
    private ArrayList<E> top;

    /**
     * This method gets the current merged arraylist
     * 
     * @param status The merging state of the program
     * @return The current merged arraylist
     */
    private ArrayList<ArrayList<E>> getCurrentList(boolean status) {
        if (!status)
            return buckets;
        else
            return buckets1;
    }

    /**
     * This method is used to compare two parametized objects a and b
     * 
     * @param a One item to compare
     * @param b Another item to compare
     * @return True if item a is "less than" item b according to the implemeted
     *         Comparable<E> interface
     * @throws ClassCastException If the object type E does not implement the
     *                            Comparable<E> interface
     */
    private boolean lessThan(E a, E b) throws ClassCastException {
        if (!(a instanceof Comparable<?>)) {
            throw new ClassCastException(
                    "Type specified does not implement the Comparable<" + a.getClass().getName() + "> interface");
        }
        Comparable<E> c = (Comparable<E>) a;
        return c.compareTo(b) < 0;
    }

    /**
     * This method reverses an arraylist and returns the result
     * 
     * @param prev The original arraylist
     * @return The arraylist after it has been reversed
     */
    private ArrayList<E> reverse(ArrayList<E> prev) {
        ArrayList<E> toReturn = new ArrayList<E>();
        for (int i = prev.size() - 1; i >= 0; i--) {
            toReturn.add(prev.get(i));
        }
        return toReturn;
    }

    /**
     * This method returns a lower-bound binary search position. It gives the
     * leftmost position that the key is eligible to fit into
     * 
     * @param key The number to be placed
     * @return The leftmost eligible position for the key specified
     */
    private int binarySearch(E key) throws Exception {
        int lft = 0, rit = top.size() + 1;
        int ans = 0;
        while (lft < rit) {
            int mid = (lft + rit) / 2;
            if (mid < top.size() && lessThan(top.get(mid), key)) {
                lft = mid + 1;
            } else {
                rit = mid;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * This method merges every two buckets into a bucket in the merged pile
     * 
     * @param initial The initial bucket (bucket to merge)
     * @param merged  The output bucket (bucket storing the result of the merge)
     */
    private void mergeLists(ArrayList<ArrayList<E>> initial, ArrayList<ArrayList<E>> merged) throws Exception {
        merged.clear();
        for (int i = 0; i < initial.size() - 1; i += 2) {
            // Merge (i) and (i + 1)
            merged.add(new ArrayList<E>());
            int p = initial.get(i).size() - 1, q = initial.get(i + 1).size() - 1;
            while (p >= 0 || q >= 0) {
                E lesser;
                if ((p >= 0) && (q < 0 || lessThan(initial.get(i).get(p), initial.get(i + 1).get(q)))) {
                    lesser = initial.get(i).get(p);
                    p--;
                } else {
                    lesser = initial.get(i + 1).get(q);
                    q--;
                }
                merged.get(merged.size() - 1).add(lesser);
            }
        }
        if (initial.size() % 2 != 0) {
            merged.add(reverse((initial.get(initial.size() - 1))));
        }
    }

    /**
     * This method merges every two buckets into a bucket in the merged pile but
     * assumes the elements are sorted in reverse order
     * 
     * @param initial The initial buckets (buckets to merge)
     * @param merged  The output buckets (buckets storing the result of the merge)
     */
    private void mergeListsInReverse(ArrayList<ArrayList<E>> initial, ArrayList<ArrayList<E>> merged) throws Exception {
        merged.clear();
        for (int i = 0; i < initial.size() - 1; i += 2) {
            // Merge (i) and (i + 1)
            merged.add(new ArrayList<E>());
            int size1 = initial.get(i).size(), size2 = initial.get(i + 1).size();
            int p = 0, q = 0;
            while (p < size1 || q < size2) {
                E lesser;
                if ((p < size1) && (q >= size2 || lessThan(initial.get(i).get(p), initial.get(i + 1).get(q)))) {
                    lesser = initial.get(i).get(p);
                    p++;
                } else {
                    lesser = initial.get(i + 1).get(q);
                    q++;
                }
                merged.get(merged.size() - 1).add(lesser);
            }
        }
        if (initial.size() % 2 != 0) {
            merged.add(initial.get(initial.size() - 1));
        }
    }

    /**
     * This method sorts an Integer array using the Patience Sort algorithm
     * 
     * @param array The array to be sorted
     */
    public void patienceSort(E array[]) throws Exception {
        top = new ArrayList<E>();
        buckets = new ArrayList<ArrayList<E>>();
        buckets1 = new ArrayList<ArrayList<E>>();

        for (int i = 0; i < array.length; i++) {
            if (top.isEmpty()) {
                top.add(array[i]);
                buckets.add(new ArrayList<E>());
                buckets.get(0).add(array[i]);
            } else {
                int pos = binarySearch(array[i]);
                if (pos >= top.size()) {
                    top.add(array[i]);
                    buckets.add(new ArrayList<E>());
                    buckets.get(buckets.size() - 1).add(array[i]);
                } else {
                    top.set(pos, array[i]);
                    buckets.get(pos).add(array[i]);
                }
            }
        }

        boolean toUse = false; // false - from bucket to bucket1; true - from bucket1 to bucket
        boolean firstMerge = true;

        if (getCurrentList(toUse).size() == 1) {
            for (int i = 0; i < array.length; i++) {
                array[i] = getCurrentList(toUse).get(0).get(array.length - i - 1);
            }
            return;
        }

        while (getCurrentList(toUse).size() > 1) {
            if (firstMerge) {
                mergeLists(buckets, buckets1);
                firstMerge = false;
            } else if (!toUse) {
                mergeListsInReverse(buckets, buckets1);
            } else {
                mergeListsInReverse(buckets1, buckets);
            }
            toUse = !toUse;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = getCurrentList(toUse).get(0).get(i);
        }
    }
}