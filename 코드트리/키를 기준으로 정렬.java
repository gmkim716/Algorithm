import java.util.Scanner;
import java.util.Arrays;

class People implements Comparable<People> {
    String name;
    int height, weight;

    public People(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(People people) {
        return this.height - people.height;
    }
}

public class Main {
    public static int MAX_N = 10;
    public static int n; 
    public static People[] people = new People[MAX_N];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            String name = sc.next();
            int height = sc.nextInt();
            int weight = sc.nextInt();

            people[i] = new People(name, height, weight);
        }

        Arrays.sort(people, 0, n);

        for (int i=0; i<n; i++) {
            System.out.println(people[i].name+" "+people[i].height+" "+people[i].weight);
        }
    }
}