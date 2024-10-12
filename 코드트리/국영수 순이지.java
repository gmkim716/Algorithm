import java.util.Scanner; 
import java.util.Arrays; 

class Student implements Comparable<Student> {
    String name;
    int korean, english, math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    @Override
    public int compareTo(Student student) {
        if (this.korean == student.korean) {
            if (this.english == student.english) {
                return student.math - this.math;
            }
            return student.english - this.english;
        }
        return student.korean - this.korean;
    }
}

public class Main {
    public static int MAX_N = 10;
    public static int n;
    public static Student[] students = new Student[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            String name = sc.next();
            int korean = sc.nextInt();
            int english = sc.nextInt();
            int math = sc.nextInt();

            students[i] = new Student(name, korean, english, math);
        }

        Arrays.sort(students, 0, n);

        for (int i=0; i<n; i++) {
            System.out.println(students[i].name + " " + students[i].korean + " " + students[i].english + " " + students[i].math);
        }
    }
}