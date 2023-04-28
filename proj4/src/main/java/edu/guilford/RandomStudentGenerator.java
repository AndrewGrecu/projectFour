package edu.guilford;

public class RandomStudentGenerator {
    public static Student[] generateRandomStudents(int numStudents) {
        Student[] students = new Student[numStudents];
        for (int i = 0; i < numStudents; i++) {
            students[i] = new Student();
        }
        return students;
    }
}



