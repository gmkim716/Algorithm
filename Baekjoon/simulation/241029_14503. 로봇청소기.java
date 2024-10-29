import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int N, M, r, c, d;
    static int ans = 0;
    static int[][] map;
    static final int DIR_NUM = 4;
    // 북동남서
    static final int[] dc = {0, 1, 0, -1};
    static final int[] dr= {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        robotOn(r, c);

        System.out.println(ans);
        br.close();
    }

    static void robotOn(int r, int c) {
        // 현재 칸이 청소되지 않은 경우 현재 칸을 청소한다
        if (map[r][c] == 0) {
            map[r][c] = 2;  // 2: 로봇에 의해 청소된 위치
            ans++;
        }

        // 청소할 곳을 찾아 이동
        for (int i=0; i<DIR_NUM; i++) {
            d = (d+3) % 4;  // 왼쪽으로 회전
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (canGo(nr, nc)) {
                robotOn(nr, nc);
                return;  // 청소할 경우 아래 코드를 실행하지 않음
            }
        }

        // 청소할 곳을 찾지 못한 경우
        int backDir = (d + 2) % 4;  // 현재 방향을 유지한 채로 후진하기 위해 d가 아닌 backDir을 사용한다
        int nr = r + dr[backDir];
        int nc = c + dc[backDir];

        if (inRange(nr, nc) && map[nr][nc] != 1) {
            robotOn(nr, nc);
        }
    }

    static boolean canGo(int r, int c) {
        return inRange(r, c) && map[r][c] == 0;
    }

    static boolean inRange(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }

    static void printMap() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}
