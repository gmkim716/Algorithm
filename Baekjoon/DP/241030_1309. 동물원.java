package org.example;

import java.io.IOException;
import java.util.Scanner;

// dp를 통해 푼다: 특정 상태가 이전에 의존, 결과를 활용할 수 있기 때문
// dp[i][0]: i번째 줄에 사자를 배치하지 않음, dp[i][1]: i번째 줄에 사자를 왼쪽에 배치, dp[i][2]: i번째 줄에 사자를 오른쪽에 배치
// 2개, 3개 짜리 무언가가 나온다면 dp, 누적합, 백트래킹 등 의심해보기
// 더구나 이 문제는 9901로 나눈 나머지의 값을 요구하고 있다
public class Main {

    static int N, ans;
    static int[][] map;
    static int[][] dp;
    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][2];
        dp = new int[N+1][2+1];  // dp를 이용할 때는 0번째 맵의 크기에 여유를 둔다(0번째 인덱스 이용을 위함)
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;

        findDp();

        // N, N-1 중에서 헷갈리지 않도록 주의 (0번째 인덱스를 사용했기 때문에 N-1의 값을 사용해야 함)
        ans = (dp[N-1][0] + dp[N-1][1] + dp[N-1][2]) % MOD;
        System.out.println(ans);
    }

    static void findDp() {
        for (int i=1; i<=N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;
        }
    }
}
