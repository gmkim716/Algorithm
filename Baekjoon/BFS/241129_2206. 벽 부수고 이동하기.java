package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int r, c, dist, wallBreak;

    public Node(int r, int c, int dist, int wallBreak) {
        this.r = r;
        this.c = c;
        this.dist = dist;
        this.wallBreak = wallBreak;
    }
}

public class Main {

    static int N, M, ans;
    static int[][] map;
    static boolean[][][] visited;

    static final int DIR_NUM = 4;
    static final int[] dr = {0, 0, 1, -1};
    static final int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];  // 벽을 부쉈는지 여부를 체크한다

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int cr = current.r;
            int cc = current.c;

            if (cr == N-1 && cc == M-1) {
                return current.dist;
            }

            for (int i=0; i<DIR_NUM; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (inRange(nr, nc)) {
                    if (map[nr][nc] == 0 && !visited[nr][nc][current.wallBreak]) {
                        visited[nr][nc][current.wallBreak] = true;
                        queue.add(new Node(nr, nc, current.dist+1, current.wallBreak));
                    } else if (map[nr][nc] == 1 && current.wallBreak == 0) {
                        visited[nr][nc][1] = true;
                        queue.add(new Node(nr, nc, current.dist+1, 1));
                    }
                }
            }
        }

        return -1;
    }

    static boolean inRange(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }
}