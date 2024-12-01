package org.example;

import java.io.*;
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

    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;

    static final int[] dr = {0, 0, 1, -1, 0};
    static final int[] dc = {1, -1, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K+1];  // 벽을 부순 횟수를 관리

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0));  // 시작점
        visited[0][0][0] = true;  // 벽을 부수지 않고 시작

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.r == N - 1 && current.c == M - 1) {
                return current.dist;  // 도착 지점 도달
            }

            for (int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];

                if (inRange(nr, nc)) {
                    // 벽이 아닌 경우
                    if (map[nr][nc] == 0 && !visited[nr][nc][current.wallBreak]) {
                        visited[nr][nc][current.wallBreak] = true;
                        queue.add(new Node(nr, nc, current.dist + 1, current.wallBreak));
                    }
                    // 벽이고, 부술 횟수가 남아있는 경우
                    else if (map[nr][nc] == 1 && current.wallBreak < K && !visited[nr][nc][current.wallBreak + 1]) {
                        visited[nr][nc][current.wallBreak + 1] = true;
                        queue.add(new Node(nr, nc, current.dist + 1, current.wallBreak + 1));
                    }
                }
            }
        }

        return -1;  // 도달 불가능
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
