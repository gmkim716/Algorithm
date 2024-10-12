import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;  // *Comparator: new!

// Comparator: 객체의 비교를 위해서 사용, 기존의 방식과는 다른 방식으로 정의해서 정렬 가능 


public class Main {
    public static final int MAX_N = 50000;
    
    public static int n;
    public static Integer[] arr = new Integer[MAX_N];  // *Integer: int가 아닌 Integer를 사용, Comparator에 사용하기 위함 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); 

        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        // arr: 정렬할 배열, 0: 시작 인덱스, n: 배열의 길이(= n-1: 마지막 인덱스), new~: 익명 내부 클래스의 인스턴스
        Arrays.sort(arr, 0, n, new Comparator<Integer>() {  
            @Override
            public int compare(Integer a, Integer b) {
                String s1 = Integer.toString(a) + Integer.toString(b);  // 'ab' 형태
                String s2 = Integer.toString(b) + Integer.toString(a);  // 'ba' 형태
                return s2.compareTo(s1);   // *compareTo: String 클래스의 비교, 두개의 문자를 사전식으로 비교
            }
        });

        for (int i=0; i<n; i++) {
            System.out.print(arr[i]);
        }
        
    }
}