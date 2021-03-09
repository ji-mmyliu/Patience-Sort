import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        PatienceSort<Person> p = new PatienceSort<Person>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Person a[] = new Person[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Person(s.next(), s.nextInt());
        }

        p.patienceSort(a);
        System.out.println(Arrays.toString(a));
    }

    static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String n, int a) {
            name = n;
            age = a;
        }

        @Override
        public int compareTo(Person p) {
            return age - p.age;
        }

        public String toString() {
            return name + " " + age;
        }
    }
}
