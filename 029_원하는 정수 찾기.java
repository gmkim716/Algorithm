import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i=0; i<N; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);  // 이진 탐색을 위한 정렬

        int M = sc.nextInt();
        for (int i=0; i<M; i++) {
            boolean find = false;
            int target = sc.nextInt();

            int start = 0;
            int end = A.length - 1;

            while (start <= end) {
                int midIdx = (start + end) / 2;
                int midValue = A[midIdx];

                if (midValue > target) {
                    end = midIdx - 1;
                } else if (midValue < target) {
                    start = midIdx + 1;
                } else {  // midvalue == target
                    find = true;
                    break;
                }
            }
            if (find) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}