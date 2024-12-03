package org.example;

import java.util.*;

class Pair {
    int r, c;
    Pair (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int N, L, R;
    static int[][] map, group;

    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int moveCnt = 0;

        while (true) {
            // 이동 탐색이 반복될 때마다 group을 새로 만든다
            group = new int[N][N];
            int groupNum = 0;
            boolean hasMoved = false;

            for (int r=0; r<N; r++) {
                for (int c=0; c<N; c++) {
                    if (group[r][c] == 0 && bfs(r, c, ++groupNum)) {
                        hasMoved = true;
                    }
                }
            }

            if (!hasMoved) break;  // 인구의 이동이 일어나지 않을 경우 반복문을 종료한다

            moveCnt++;
        }

        System.out.println(moveCnt);
    }

    static boolean bfs(int r, int c, int groupNum) {
        Queue<Pair> queue = new LinkedList<>();
        List<Pair> union = new ArrayList<>();

        queue.add(new Pair(r, c));
        union.add(new Pair(r, c));
        group[r][c] = groupNum;

        int totalPopulation = map[r][c];
        int countries = 1;  // 0부터 시작하면 연합 번호와 탐색하지 않은 수를 확인하기 어려움

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int d=0; d<4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (inRange(nr, nc) && group[nr][nc] == 0 && canOpen(p.r, p.c, nr, nc)) {
                    queue.add(new Pair(nr, nc));
                    union.add(new Pair(nr, nc));
                    group[nr][nc] = groupNum;
                    totalPopulation += map[nr][nc];
                    countries++;
                }
            }
        }

        if (countries > 1) {  // 인구 이동이 가능한 경우
            int avgPopulation = totalPopulation / countries;
            for (Pair p : union) {
                map[p.r][p.c] = avgPopulation;
            }
            return true;
        }
        return false;
    }

    static boolean canOpen(int r1, int c1, int r2, int c2) {
        int diff = Math.abs(map[r1][c1]-map[r2][c2]);
        return L <= diff && diff <= R;
    }

    static boolean inRange(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    static void printGroup() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(group[i][j] + " ");
            }
            System.out.println();
        }
    }
}
