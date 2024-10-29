import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

// 직사각형 범위 내에 벽이 있는 지 검사하는 과정으로 인해 시간 초과 발생
// 누적합 배열을 통해 벽 포함 여부를 확인하면 효율적으로 검사할 수 있다
public class Main {

    static int N, M, H, W, Sr, Sc, Fr, Fc;
    static int[][] map;
    static int[][] dist;
    static int[][] wallSum; // 누적합 배열

    static final int DIR_NUM = 4;
    static final int[] dr = {0, 1, 0, -1}; // 동남서북
    static final int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        wallSum = new int[N + 1][M + 1];  // 누적합 배열을 만들 때는 1칸의 여유 공간을 둔다

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1; // 방문하지 않은 상태를 -1로 설정
            }
        }

        // 누적합 배열 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                wallSum[i][j] = map[i-1][j-1] + wallSum[i-1][j] + wallSum[i][j-1] - wallSum[i-1][j-1];
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sr = Integer.parseInt(st.nextToken()) - 1;
        Sc = Integer.parseInt(st.nextToken()) - 1;
        Fr = Integer.parseInt(st.nextToken()) - 1;
        Fc = Integer.parseInt(st.nextToken()) - 1;

        int result = findMinDist(Sr, Sc);
        System.out.println(result);
        br.close();
    }

    static int findMinDist(int Sr, int Sc) {
        Deque<Pair> deque = new LinkedList<>();
        deque.addFirst(new Pair(Sr, Sc));
        dist[Sr][Sc] = 0;

        while (!deque.isEmpty()) {
            Pair pair = deque.pollFirst();
            int r = pair.r;
            int c = pair.c;

            if (r == Fr && c == Fc) {
                return dist[r][c];
            }

            for (int i = 0; i < DIR_NUM; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (canGo(nr, nc)) {
                    dist[nr][nc] = dist[r][c] + 1;
                    deque.addLast(new Pair(nr, nc));
                }
            }
        }

        return -1; // 목표 지점에 도달하지 못한 경우
    }

    static boolean canGo(int r, int c) {
        return inRange(r, c) && notContainsWall(r, c) && dist[r][c] == -1;
    }

    // 누적합 배열을 통해 벽 포함 여부를 확인하다
    static boolean notContainsWall(int r, int c) {
        int r2 = r + H - 1;
        int c2 = c + W - 1;

        int wallCount = wallSum[r2 + 1][c2 + 1] - wallSum[r][c2 + 1] - wallSum[r2 + 1][c] + wallSum[r][c];
        return wallCount == 0;
    }

    // N, M이 포함되어야 한다?: 직사각형의 넓이를 고려해야 하기 때문
    static boolean inRange(int r, int c) {
        return 0 <= r && r + H <= N && 0 <= c && c + W <= M;
    }
}
