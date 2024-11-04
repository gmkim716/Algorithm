package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ball {
    int r, c;
    public Ball(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class State {
    Ball red, blue;
    int moves;
    public State(Ball red, Ball blue, int moves) {
        this.red = red;
        this.blue = blue;
        this.moves = moves;
    }
}

// 구슬 탈출 2: 최단 경로를 구해야 하기 때문에 BFS를 사용한다
public class Main {

    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;

    // 동, 남, 서, 북
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};

    static Ball redBall, blueBall;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String inputStr = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = inputStr.charAt(j);

                // 구슬의 위치를 표시하고, 빈 칸으로 바꾼다
                if (map[i][j] == 'R') {
                    redBall = new Ball(i, j);
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    blueBall = new Ball(i, j);
                    map[i][j] = '.';
                }
            }
        }

        // BFS 탐색:
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(redBall, blueBall, 0));
        visited[redBall.r][redBall.c][blueBall.r][blueBall.c] = true;  // 동일한 상태가 되는 경우를 계산하지 않기 위해 방문 처리

        // 한 번의 사이클: n번째 기울이기를 적용했을 때 가능한 경우의 수
        while (!queue.isEmpty()) {
            State state = queue.poll();

            if (state.moves > 10) return -1;  // 10번 초과 시 실패
            for (int dir = 0; dir < 4; dir++) {
                Ball newRed = move(state.red, dir);
                Ball newBlue = move(state.blue, dir);

                if (map[newBlue.r][newBlue.c] == 'O') continue;  // 파란 구슬이 구멍에 빠지면 실패
                if (map[newRed.r][newRed.c] == 'O') return state.moves + 1;  // 빨간 구슬만 구멍에 빠지면 성공

                // 두 구슬이 같은 위치에 있을 때
                if (newRed.r == newBlue.r && newRed.c == newBlue.c) {
                    if (dist(state.red, newRed) > dist(state.blue, newBlue)) {
                        newRed.r -= dr[dir];
                        newRed.c -= dc[dir];
                    } else {
                        newBlue.r -= dr[dir];
                        newBlue.c -= dc[dir];
                    }
                }

                // 새로운 상태를 방문하지 않았으면 큐에 추가
                if (!visited[newRed.r][newRed.c][newBlue.r][newBlue.c]) {
                    visited[newRed.r][newRed.c][newBlue.r][newBlue.c] = true;
                    queue.add(new State(newRed, newBlue, state.moves + 1));
                }
            }
        }

        return -1;  // 성공할 수 없는 경우
    }

    static Ball move(Ball ball, int dir) {
        int r = ball.r;
        int c = ball.c;

        // 벽이 나오거나 구멍에 빠질 때까지 이동
        while (map[r + dr[dir]][c + dc[dir]] != '#' && map[r][c] != 'O') {
            r += dr[dir];
            c += dc[dir];
        }

        return new Ball(r, c);
    }

    static int dist(Ball start, Ball end) {
        return Math.abs(start.r - end.r) + Math.abs(start.c - end.c);
    }
}
