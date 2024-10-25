import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N, M, day;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Pair> queue = new LinkedList<>();

    static final int DIR_NUM = 4;
    static final int[] dx = {0,1,0,-1};
    static final int[] dy = {-1,0,1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  // 가로 칸 수
        N = Integer.parseInt(st.nextToken());  // 세로 칸 수

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.add(new Pair(j, i));
                    visited[i][j] = true;
                }
            }
        }

        bfs();

        if (checkBox()) {
            System.out.println(day);
        } else {
            System.out.println(-1);
        }
        br.close();
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                Pair pair = queue.poll();
                int x = pair.x;
                int y = pair.y;

                for (int d = 0; d < DIR_NUM; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (canGo(nx, ny)) {
                        visited[ny][nx] = true;
                        map[ny][nx] = 1;
                        queue.add(new Pair(nx, ny));
                    }
                }
            }
            if (!queue.isEmpty()) {
                day++;
            }
        }
    }

    static boolean checkBox() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean canGo(int x, int y) {
        return inRange(x, y) && map[y][x] == 0 && !visited[y][x];
    }

    static boolean inRange(int x, int y) {
        return 0<=x && x<M && 0<=y && y<N;
    }
}
s