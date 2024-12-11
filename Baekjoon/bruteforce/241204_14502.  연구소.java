
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int r, c;
    Pair (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int N, M;

    static int[][] map;
    static boolean[][] visited;  // 벽을 세운 곳을 방문했는지 여부 체크
    static int safeArea;

    static Queue<Pair> virus = new LinkedList<>();
    static List<Pair> space = new LinkedList<>();
    static Queue<Pair> walls = new LinkedList<>();

    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    space.add(new Pair(i, j));
                }
                if (map[i][j] == 2) {
                    virus.add(new Pair(i, j));
                }
            }
        }

        choose3(0, 0);
        System.out.println(safeArea);
    }

    static void choose3(int idx, int cnt) {
        if (cnt == 3) {
            int cntSafeArea = findSafeArea(new ArrayList<>(walls));
            safeArea = Math.max(safeArea, cntSafeArea);
            return;
        }

        for (int i=idx; i<space.size(); i++) {
            Pair pair = space.get(i);

            if (visited[pair.r][pair.c]) continue;

            walls.add(pair);
            visited[pair.r][pair.c] = true;
            choose3(i,cnt+1);
            walls.remove(pair);
            visited[pair.r][pair.c] = false;
        }
    }

    static int findSafeArea(List<Pair> walls) {
        int[][] newMap = deepClone(map);  // 깊은 복사를 진행해야 함

        for (Pair p: walls) newMap[p.r][p.c] = 1;

        Queue<Pair> q = new LinkedList<>(virus);

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (canGo(nr, nc, newMap)) {
                    q.add(new Pair(nr, nc));
                    newMap[nr][nc] = 2;
                }
            }
        }
        int safeArea = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (newMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }


    static int[][] deepClone(int[][] map) {
        int[][] newMap = new int[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
    static boolean canGo(int r, int c, int[][] curMap) {
        return inRange(r, c) && curMap[r][c] == 0;
    }
    static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
