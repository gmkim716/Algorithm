import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class CoinState {
    int r1, c1, r2, c2, moves;

    public CoinState(int r1, int c1, int r2, int c2, int moves) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
        this.moves = moves;
    }
}

public class Main {

    static int N, M;
    static char[][] board;
    static List<int[]> coins = new ArrayList<>();
    static Queue<CoinState> queue = new LinkedList<>();
    static boolean[][][][] visited;

    static final int DIR_NUM = 4;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'o') {
                    coins.add(new int[]{i, j});
                }
            }
        }

        System.out.println(bfs(coins.get(0)[0], coins.get(0)[1], coins.get(1)[0], coins.get(1)[1]));
    }

    static boolean inRange(int x, int y) {
        return 0<= x && x < N && 0<=y && y < M;
    }

    static int bfs(int x1, int y1, int x2, int y2) {

        queue.add(new CoinState(x1, y1, x2, y2, 0));
        visited[x1][y1][x2][y2] = true;

        while (!queue.isEmpty()) {
            CoinState current = queue.poll();
            int cx1 = current.r1, cy1 = current.c1;
            int cx2 = current.r2, cy2 = current.c2;
            int count = current.moves;

            // 10번 이상 움직인 경우, -1을 반환한다
            if (count >= 10) return -1;

            for (int i=0; i<DIR_NUM; i++) {
                int nx1 = cx1 + dx[i], ny1 = cy1 + dy[i];
                int nx2 = cx2 + dx[i], ny2 = cy2 + dy[i];

                // 두 동전이 모두 보드에서 벗어난 경우
                int outCount = 0;
                if (!inRange(nx1, ny1)) outCount++;
                if (!inRange(nx2, ny2)) outCount++;

                // 하나의 동전이 보드에서 벗어난 경우
                if (outCount == 1) return count + 1;

                // 두 동전이 모두 보드에서 벗어난 경우
                if (outCount == 2) continue;

                // 벽이 있는 경우 이동시키지 않는다
                if (board[nx1][ny1] == '#') {
                    nx1 = cx1;
                    ny1 = cy1;
                }
                if (board[nx2][ny2] == '#') {
                    nx2 = cx2;
                    ny2 = cy2;
                }

                if (!visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    queue.offer(new CoinState(nx1, ny1, nx2, ny2, count + 1));
                }
            }
        }

        return -1;
    }
}
