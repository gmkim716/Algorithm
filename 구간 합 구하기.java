// 11659

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N+1];
        int[] S = new int[N+1];  // 구간 합을 저장하는 배열

        for (int i=1; i<=N; i++) {
            A[i] = sc.nextInt();
            if (i == 0) {
                S[i] = A[i];
            } else {
                S[i] = S[i-1]+A[i];
            }
        }

        for (int i=1; i<=M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            System.out.println(S[b] - S[a-1]);
        }
    }
}