package edu.guilford;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Student {
    // attributes for a student
    private String name;
    private int age;
    private String major;
    private String minor;
    private double GPA;
    private String year;
    private double credits;

    // constructor
    public Student() {
        Random rand = new Random();

        // Generate random values for each attribute
        this.name = generateRandomName();
        this.age = rand.nextInt(100) + 1; // generates a random number between 1 and 100
        this.major = generateRandomMajor();
        this.minor = generateRandomMinor();
        this.GPA = rand.nextDouble() * 4.0; // generates a random floating-point number between 0.0 and 4.0
        this.year = generateRandomYear();
        this.credits = rand.nextInt(121); // generates a random integer between 0 and 120
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    // toString method
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", minor='" + minor + '\'' +
                ", GPA=" + GPA +
                ", year='" + year + '\'' +
                ", credits=" + credits +
                '}';
    }

    private String generateRandomName() {
        String[] firstNames = { "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy" };
        String[] lastNames = { "Smith", "Johnson", "Brown", "Garcia", "Davis", "Wilson", "Miller", "Jones", "Taylor",
                "Clark" };
        Random rand = new Random();
        String firstName = firstNames[rand.nextInt(firstNames.length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    private String generateRandomMajor() {
        String[] majors = { "Computer Science", "Mathematics", "Engineering", "Biology", "Psychology", "English",
                "History", "Business", "Art", "Music" };
        Random rand = new Random();
        return majors[rand.nextInt(majors.length)];
    }

    private String generateRandomMinor() {
        String[] minors = { "Computer Science", "Mathematics", "Engineering", "Biology", "Psychology", "English",
                "History", "Business", "Art", "Music" };
        Random rand = new Random();
        return minors[rand.nextInt(minors.length)];
    }

    private String generateRandomYear() {
        String[] years = { "Freshman", "Sophomore", "Junior", "Senior" };
        Random rand = new Random();
        return years[rand.nextInt(years.length)];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of random students to generate: ");
        int numStudents = scanner.nextInt();

        // Generate an array of random Student objects
        Student[] students = RandomStudentGenerator.generateRandomStudents(numStudents);

        // Display the unsorted array of Student objects
        System.out.println("Unsorted students:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Sort the array of Student objects by GPA using selection sort
        selectionSortByGPA(students);

        // Display the sorted array of Student objects
        System.out.println("\nSorted students by GPA (selection sort):");
        for (Student student : students) {
            System.out.println(student);
        }

        // Shuffle the array of Student objects
        shuffleArray(students);

        // Sort the array of Student objects by GPA using merge sort
        mergeSortByGPA(students);

        // Display the sorted array of Student objects
        System.out.println("\nSorted students by GPA (merge sort):");
        for (Student student : students) {
            System.out.println(student);
        }

        // Search for a Student by name using sequential search
        System.out.print("Enter the name of the Student to search for: ");
        scanner.nextLine(); // consume newline character
        String name = scanner.nextLine();
        int index = sequentialSearchByName(students, name);
        if (index != -1) {
            System.out.println("Found " + students[index] + " at index " + index + " using sequential search.");
        } else {
            System.out.println("Unable to find " + name);
        }
        System.out.print("Enter the age of the Student to search for: ");
        int age = scanner.nextInt();
        index = binarySearchByAge(students, age);
        if (index != -1) {
            System.out.println("Found " + students[index] + " at index " + index + " using binary search.");
        } else {
            System.out.println("Unable to find a Student with age " + age);
        }
    }

    // Sorting algorithms
    public static void selectionSortByGPA(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < students.length; j++) {
                if (students[j].getGPA() < students[minIndex].getGPA()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Student temp = students[minIndex];
                students[minIndex] = students[i];
                students[i] = temp;
            }
        }
    }

    public static void mergeSortByGPA(Student[] students) {
        if (students.length > 1) {
            int mid = students.length / 2;
            Student[] left = Arrays.copyOfRange(students, 0, mid);
            Student[] right = Arrays.copyOfRange(students, mid, students.length);
            mergeSortByGPA(left);
            mergeSortByGPA(right);
            mergeByGPA(students, left, right);
        }
    }

    private static void mergeByGPA(Student[] students, Student[] left, Student[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].getGPA() <= right[j].getGPA()) {
                students[k] = left[i];
                i++;
            } else {
                students[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            students[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            students[k] = right[j];
            j++;
            k++;
        }
    }

    public static int sequentialSearchByName(Student[] students, String name) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearchByAge(Student[] students, int age) {
        int left = 0;
        int right = students.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (students[mid].getAge() == age) {
                return mid;
            } else if (students[mid].getAge() < age) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Other helper methods
    public static void shuffleArray(Student[] students) {
        Random rand = new Random();
        for (int i = students.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Student temp = students[i];
            students[i] = students[j];
            students[j] = temp;
        }
    }
}