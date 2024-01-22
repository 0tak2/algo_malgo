package kr.pe.otag2.study.icote.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class StudentSort_6_3 {
    class Student implements Comparable {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Object o) {
            return ((Student)o).getScore() - this.score;
        }
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());

        Student[] students = new Student[total];

        for (int i=0; i<total; i++) {
            String[] inputs = br.readLine().split(" ");
            students[i] = new Student(inputs[0], Integer.parseInt(inputs[1]));
        }

        Arrays.sort(students, Collections.reverseOrder());

        for (int i=0; i<total; i++) {
            System.out.print(students[i].getName() + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        StudentSort_6_3 solution = new StudentSort_6_3();
        solution.solution();
    }
}
