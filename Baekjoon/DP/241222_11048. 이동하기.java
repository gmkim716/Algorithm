package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int N, M;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];

        for (int j=1; j<M; j++) {
            dp[0][j] = dp[0][j-1] + map[0][j];
        }

        for (int i=1; i<N; i++) {
            dp[i][0] = dp[i-1][0] + map[i][0];
        }

        for (int i=1; i<N; i++) {
            for (int j=1; j<M; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1])) + map[i][j];
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
