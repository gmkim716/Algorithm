package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1][3];  // 3중 배열을 만들어 사용한다

        // 입력받아 지도 초기화
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 상태 설정: (1, 2)에 가로 방향 파이프
        dp[1][2][0] = 1;

        // DP 계산
        for (int r=1; r<=N; r++) {
            for (int c=1; c<=N; c++) {
                if (map[r][c] == 1) continue; // 벽이면 스킵

                // 가로 방향: 이전 열의 가로/대각선 방향 경우의 수 합산
                if (c > 1) dp[r][c][0] += dp[r][c-1][0] + dp[r][c-1][1];

                // 세로 방향: 이전 행의 세로/대각선 방향 경우의 수 합산
                if (r > 1) dp[r][c][2] += dp[r-1][c][2] + dp[r-1][c][1];

                // 대각선 방향: 이전 열의 가로/세로/대각선 방향 경우의 수 합산
                if (r > 1 && c > 1 && map[r-1][c] == 0 && map[r][c-1] == 0) {
                    dp[r][c][1] += dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2];
                }
            }
        }

        // (N, N)에 도달하는 모든 방향의 경우의 수 합산
        int result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(result);
    }
}
