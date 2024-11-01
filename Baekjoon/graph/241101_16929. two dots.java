package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static String ans = "No";
    static boolean[][] visited;

    static final int DIR_NUM = 4;
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (!visited[i][j]) {
                    // (-1, -1): 이전 노드가 없는 상태
                    checkCycle(i, j, -1, -1, map[i][j]);
                }
            }
        }

        System.out.println(ans);
    }

    // 현재와 이전 노드의 위치만 비교하면 된다
    static void checkCycle(int r, int c, int pr, int pc, char color) {
        visited[r][c] = true;

        for (int i = 0; i < DIR_NUM; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (canGo(nr, nc, color)) {
                if (!visited[nr][nc]) {
                    checkCycle(nr, nc, r, c, color);
                } else {
                    // 방문이 가능하지만, 이전 노드가 아닌 경우 정답을 체크한다
                    if (nr != pr || nc != pc) {
                        ans = "Yes";
                    }
                }
            }
        }
    }

    static boolean canGo(int r, int c, char color) {
        return inRange(r, c) && map[r][c] == color;
    }

    static boolean inRange(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }
}

