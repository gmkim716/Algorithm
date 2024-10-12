import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class People {  // Comparable을 implement 하지 않음
    String name; 
    int height;
    double weight;

    public People (String name, int height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
}
public class Main {
    public static People[] people = new People[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int i=0; i<5; i++) {
            String name = sc.next();
            int height = sc.nextInt();
            double weight = sc.nextDouble();  // sc.nextDouble(): double 타입 받기

            people[i] = new People(name, height, weight);
        }

        //== **custom comparator 활용해서 정렬 ==//
        Arrays.sort(people, new Comparator<People>() {  // Comparator 객체 생성 (*객체타입)
            
            @Override  // compare 메소드 재정의
            public int compare(People a, People b) {
                return a.name.compareTo(b.name);  // a객체와 b객체의 name 비교;
            }
        });

        System.out.println("name");
        for (int i=0; i<5; i++) {
            System.out.println(people[i].name+" "+people[i].height+" "+people[i].weight);
        }
        System.out.println(); 

        Arrays.sort(people, new Comparator<People>() {
            @Override
            public int compare(People a, People b) {
                return b.height - a.height;
            }
        });

        System.out.println("height");
        for (int i=0; i<5; i++) {
            System.out.println(people[i].name+" "+people[i].height+" "+people[i].weight);
        }
    }
}