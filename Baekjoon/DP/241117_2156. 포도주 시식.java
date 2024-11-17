package org.example;

import java.util.Scanner;

// dfs로 풀 경우 비효율적이다. dp로 풀어야 한다
// 선택할 수 있는 경우의 수가 3가지이다
// 1. i번째 잔을 마시지 않음: dp[i] = dp[i-1]
// 2. i-1번 째 잔을 마시지 않고, i번 째 잔을 마신다: dp[i] = dp[i-2] + arr[i]
// 3. i-1번 째 잔을 마시고, i번 째 잔을 마신다: dp[i] = dp[i-3] + arr[i-1] + arr[i]
// 따라서 점화식을 만들 수 있다: dp[i] = max(dp[i-1], dp[i-2] + arr[i], ap[i-3] + arr[i-1] + arr[i])
public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];

        for (int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
        }

        if (N == 1) {
            System.out.println(arr[0]);
            return;
        } else if (N == 2) {
            System.out.println(arr[0] + arr[1]);
            return;
        }

        int[] dp = new int[N];
        dp[0] = arr[0];
        dp[1] = arr[0] + arr[1];
        dp[2] = Math.max(Math.max(dp[0]+arr[1], dp[0]+arr[2]), arr[1] + arr[2]);
        for (int i=3; i<N; i++) {
            dp[i] = Math.max(
                dp[i-1],  // i번 째 잔을 마시지 않음
                Math.max(
                    dp[i-2] + arr[i],  // i-1번 째 잔을 마시지 않고 i번 째 잔을 마신다
                    dp[i-3] + arr[i-1] + arr[i]  // i-1번 째 잔, i번 째 잔을 마시고 i-2번째 잔을 마시지 않는다
                )
            );
        }
        System.out.println(dp[N-1]);
    }
}