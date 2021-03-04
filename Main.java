import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        PatienceSort<Person> p = new PatienceSort();
        Person a[] = { new Person("Abbey", 3), new Person("Bobby", 5), new Person("Dewey", 10),
                new Person("Cathy", 6) };

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

        public int compareTo(Person p) {
            return age - p.age;
        }

        public String toString() {
            return name + " " + age;
        }
    }
}
