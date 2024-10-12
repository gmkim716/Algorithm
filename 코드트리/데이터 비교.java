import java.util.Scanner;
import java.util.HashSet;

public class Main {
    public final static int MAX_NUM = 100000;

    public static int n, m;
    public static int[] arr1 = new int[MAX_NUM];
    public static int[] arr2 = new int[MAX_NUM];

    public static HashSet<Integer> s = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 값 입력
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            arr1[i] = sc.nextInt();
        }    

        m = sc.nextInt();
        for (int i=0; i<m; i++) {
            arr2[i] = sc.nextInt();
        }    

        // 첫 번째 수열 원소 전부를 HashSet에 넣어준다
        for (int i=0; i<n; i++) {
            s.add(arr1[i]);
        }

        // 두 번째 수열의 각 원소가 첫 번째 수열에 들어있는지 확인
        for (int i=0; i<m; i++) {
            if (!s.contains(arr2[i])) {
                System.out.print(0+" ");
            } else {
                System.out.print(1+" ");
            }
        }
    }
} {
  
}
