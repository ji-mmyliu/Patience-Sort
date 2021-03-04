
/**
 * Names: Jimmy Liu, Josh Liu, Di Hu
 * Date: March 4, 2021
 * Teacher: Ms. Krasteva
 * Description: This class contains the methods require to sort an integer array using the Patience Sort algorithm
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /**
     * This 2D arraylist contains arraylists of arraylists which store the patience
     * sort buckets
     */
    private static ArrayList<ArrayList<Integer>> buckets;

    /** This 2D arraylist contains a merged arraylist */
    private static ArrayList<ArrayList<Integer>> buckets1;

    /** This arraylist contains the top element of each bucket */
    private static ArrayList<Integer> top;

    /**
     * This method gets the current merged arraylist
     * 
     * @param status The merging state of the program
     * @return The current merged arraylist
     */
    private static ArrayList<ArrayList<Integer>> getCurrentList(boolean status) {
        if (!status)
            return buckets;
        else
            return buckets1;
    }

    /**
     * This method returns a lower-bound binary search position. It gives the
     * leftmost position that the key is eligible to fit into
     * 
     * @param key The number to be placed
     * @return The leftmost eligible position for the key specified
     */
    private static int binarySearch(int key) {
        int lft = 0, rit = top.size() + 1;
        int ans = 0;
        while (lft <= rit) {
            int mid = (lft + rit) / 2;
            if (mid < top.size() && top.get(mid) < key) {
                lft = mid + 1;
            } else {
                rit = mid - 1;
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
    private static void mergeLists(ArrayList<ArrayList<Integer>> initial, ArrayList<ArrayList<Integer>> merged) {
        merged.clear();
        for (int i = 0; i < initial.size() - 1; i += 2) {
            // Merge (i) and (i + 1)
            merged.add(new ArrayList<Integer>());
            int p = initial.get(i).size() - 1, q = initial.get(i + 1).size() - 1;
            while (p >= 0 || q >= 0) {
                int lesser = -1;
                if ((p >= 0) && (q < 0 || initial.get(i).get(p) < initial.get(i + 1).get(q))) {
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
            merged.add(initial.get(initial.size() - 1));
        }
    }

    /**
     * This method merges every two buckets into a bucket in the merged pile but
     * assumes the elements are sorted in reverse order
     * 
     * @param initial The initial buckets (buckets to merge)
     * @param merged  The output buckets (buckets storing the result of the merge)
     */
    private static void mergeListsInReverse(ArrayList<ArrayList<Integer>> initial,
            ArrayList<ArrayList<Integer>> merged) {
        merged.clear();
        for (int i = 0; i < initial.size() - 1; i += 2) {
            // Merge (i) and (i + 1)
            merged.add(new ArrayList<Integer>());
            int size1 = initial.get(i).size(), size2 = initial.get(i + 1).size();
            int p = 0, q = 0;
            while (p < size1 || q < size2) {
                int lesser = -1;
                if ((p < size1) && (q >= size2 || initial.get(i).get(p) < initial.get(i + 1).get(q))) {
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
     * @param array The int array to be sorted
     */
    public static void patienceSort(int array[]) {
        top = new ArrayList<Integer>();
        buckets = new ArrayList<ArrayList<Integer>>();
        buckets1 = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < array.length; i++) {
            if (top.isEmpty()) {
                top.add(array[i]);
                buckets.add(new ArrayList<Integer>());
                buckets.get(0).add(array[i]);
            } else {
                int pos = binarySearch(array[i]);
                if (pos >= top.size()) {
                    top.add(array[i]);
                    buckets.add(new ArrayList<Integer>());
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

    /**
     * This main method is a driver method that tests the patience sort algorithm
     */
    public static void main(String[] args) {
        int a[] = { 10, 5, 8, 3, 9, 4, 12, 11, 3, -10, 20, 4, 7, 23, 0, -2, 4, 55, 1000, 730, 19, 18, 19, 29, 10 };
        patienceSort(a);
        System.out.println(Arrays.toString(a));
    }
}