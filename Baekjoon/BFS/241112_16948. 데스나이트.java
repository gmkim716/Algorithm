package org.example;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Pair {
    int r, c;

    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int N, r1, c1, r2, c2;
    static int[][] dp;
    static Deque<Pair> deque = new LinkedList<>();

    static final int DIR_NUM = 6;
    static final int[] dr = {-2, -2, 0, 0, 2, 2};
    static final int[] dc = {-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        r1 = sc.nextInt();
        c1 = sc.nextInt();
        r2 = sc.nextInt();
        c2 = sc.nextInt();

        dp[r1][c1] = 0;
        deque.add(new Pair(r1, c1));
        bfs(0);


        System.out.println(dp[r2][c2] == Integer.MAX_VALUE ? -1 : dp[r2][c2]);
    }

    static void bfs(int cnt) {
        while (!deque.isEmpty()) {
            Pair p = deque.poll();
            int r = p.r;
            int c = p.c;
            cnt = dp[r][c];

            for (int d=0; d<DIR_NUM; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (inRange(nr, nc) && dp[nr][nc] > cnt+1) {
                    dp[nr][nc] = cnt+1;
                    deque.add(new Pair(nr, nc));
                }
            }
        }
    }

    static boolean inRange(int r, int c) {
        return 0<= r && r<N && 0<=c && c<N;
    }

}
