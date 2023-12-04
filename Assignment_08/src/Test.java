import java.util.ArrayList;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        ISet<String> s1 = new UnsortedSet<>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        // test 1
        boolean actual = s1.contains("A");
        showTestResults(actual, true, 1, s1, null, "add and contains methods UnsortedSet"
                + "/nset 1 contains A.");

        // test 2
        s1.remove("A");
        actual = s1.contains("A");
        showTestResults(actual, false, 2, s1, null, "remove and contains method UnsortedSet"
                + "/nset1 does not contain A.");

        // test 3
        actual = s1.size() == 2;
        showTestResults(actual, true, 3, s1, null, "size method UnsortedSet"
                + "/nsize of set 1 is 2.");

        ISet<String> s2 = new UnsortedSet<>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        // test 4
        actual = s2.containsAll(s1);
        showTestResults(actual, true, 4, s1, s2, "containsAll method UnsortedSet"
                + "/ns2 contains all of s1.");

        // test 5
        actual = s1.containsAll(s2);
        showTestResults(actual, false, 5, s1, s2, "containsAll method UnsortedSet"
                + "/ns1 contains all of s2.");

        // test 6
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<>();
        expected.add("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 6, s1, s2, "difference and equals methods UnsortedSet"
                + "/ns2.difference(s1). result = " + s3 + " expected result = " + expected);

        // test 7
        s3 = s2.union(s1);
        expected.add("B");
        expected.add("C");
        actual = s3.equals(expected);
        showTestResults(actual, true, 7, s1, s2, "union and equals methods UnsortedSet"
                + "/ns2.union(s1). actual result = " + s3
                + " expected result = " + expected);

        // test 8
        s3 = s2.intersection(s1);
        expected.remove("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 8, s1, s2, "intersection and equals methods UnsortedSet"
                + "/ns2.intersection(s1). actual result = " + s3
                + " expected result = " + expected);

        // sorted sets
        s1 = new SortedSet<>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        // test 9
        actual = s1.contains("A");
        showTestResults(actual, true, 9, s1, null, "add and contains methods SortedSet"
                + "/nset 1 contains A.");

        // test 10
        s1.remove("A");
        actual = s1.contains("A");
        showTestResults(actual, false, 10, s1, null, "remove and contains method SortedSet"
                + "/nset1 does not contain A.");


        // test 11
        actual = s1.size() == 2;
        showTestResults(actual, true, 11, s1, null, "size method SortedSet"
                + "/nsize of set 1 is 2.");

        s2 = new SortedSet<>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        // test 12
        actual = s2.containsAll(s1);
        showTestResults(actual, true, 12, s1, s2, "containsAll method SortedSet"
                + "/ns2 contains all of s1.");

        // test 13
        actual = s1.containsAll(s2);
        showTestResults(actual, false, 13, s1, s2, "containsAll method SortedSet"
                + "/ns1 contains all of s2.");

        // test 14
        s3 = s2.difference(s1);
        expected = new SortedSet<>();
        expected.add("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 14, s1, s2, "difference and equals methods SortedSet"
                + "/ns2.difference(s1). result = " + s3 + " expected result = " + expected);

        // test 15
        s3 = s1.difference(s2);
        expected = new SortedSet<>();
        actual = s3.equals(expected);
        showTestResults(actual, true, 14, s1, s2, "difference and equals methods SortedSet"
                + "/ns1.difference(s2). result = " + s3 + " expected result = " + expected);

        // test 16
        s3 = s1.union(s2);
        expected = new SortedSet<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        actual = s3.equals(expected);
        showTestResults(actual, true, 16, s1, s2, "union and equals methods SortedSet"
                + "/ns2.union(s1). actual result = " + s3
                + " expected result = " + expected);


        // test 17
        s3 = s1.intersection(s2);
        expected.remove("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 17, s1, s2, "intersection and equals methods SortedSet"
                + "/ns1.intersection(s2). actual result = " + s3
                + " expected result = " + expected);

        // test 18
        s1.add("A");
        Iterator<String> it1 = s1.iterator();
        Iterator<String> it2 = s2.iterator();
        boolean good = true;
        while (good && it1.hasNext()) {
            good = it1.next().equals(it2.next());
        }
        showTestResults(good, true, 18, s1, s2, "iterator and add methods SortedSet."
                + "\nChecked all elements equal via iterators.");

        // test 19
        s1 = new UnsortedSet<>();
        UnsortedSet<Integer> si1 = new UnsortedSet<>();
        actual = si1.equals(s1);
        showTestResults(actual, true, 19, s1, s2, "equals methods UnsortedSet"
                + "\ns2.equals(s1), both sets empty");

        // test 20
        s1.add("is");
        s1.add("a");
        si1.add(12);
        si1.add(13);
        si1.add(12);
        actual = si1.equals(s1);
        showTestResults(actual, false, 20, si1, null, "equals methods UnsortedSet"
                + "\ns2.equals(s1), different data types of elements");

        // test 21
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(12);
        ar.add(13);
        actual = false;
        showTestResults(actual, false, 20, si1, null, "equals methods UnsortedSet"
                + "\nsi1.equals(anArrayList), other Object is not a set");

        // test 22
        Object obj1 = s1;
        s2 = new UnsortedSet<>();
        s2.add("a");
        s2.add("is");
        Object obj2 = s2;
        actual = obj1.equals(obj2);
        showTestResults(actual, true, 22, s1, s2, "equals methods UnsortedSet"
                + "\nVerify equals overridden and not overloaded.");

        // test 23
        s1 = new SortedSet<>();
        s1.add("A");
        s1.add("A");
        s1.add("B");
        ISet<Integer> ss2 = new SortedSet<>();
        ss2.add(12);
        ss2.add(15);
        ss2.add(12);
        ss2.add(15);
        actual = s1.equals(ss2);
        showTestResults(actual, false, 23, s1, null, "equals methods SortedSet - different types"
                + "\nsecond set contains Integers: " + ss2);

        // test 24
        actual = s1.equals(null);
        showTestResults(actual, false, 24, s1, null, "equals methods SortedSet - other Object is null");

    }

    // print out results of test
    private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
            int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
        if (actualResult == expectedResult) {
            System.out.println("Passed test " + testNumber);
        } else {
            System.out.print("Failed test ");
            System.out.println(testNumber + ": " + testDescription);
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result  : " + actualResult);
            System.out.println("Set 1: " + set1);
            if (set2 != null) {
                System.out.println("Set 2: " + set2);
            }
        }

    }

   
}