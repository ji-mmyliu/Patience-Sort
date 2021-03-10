import java.util.ArrayList;
import java.util.Arrays;

public class SortingDriver {
    public static void main(String[] args) throws Exception {
        Integer array[] = { 2, 10, 3, 1, 2, 5, 7, 4, 8, 8, 0 };
        PatienceSort<Integer> p = new PatienceSort<Integer>();

        p.patienceSort(array);

        System.out.println(Arrays.toString(array));
    }
}
