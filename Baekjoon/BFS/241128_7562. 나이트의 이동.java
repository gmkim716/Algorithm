import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Loc {
    int r, c;

    public Loc(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int T, l;
    static int[][] map; // (0, 0) ~ (N-1, N-1)
    static boolean[][] visited;
    static Queue<Loc> queue;

    static final int DIR_NUM = 8;
    static final int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static final int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t=0; t<T; t++) {
            queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            map = new int[l][l];
            visited = new boolean[l][l];

            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int endR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken());

            int cntMinMove = bfs(startR, startC, endR, endC);

            System.out.println(cntMinMove);
        }
    }

    static int bfs(int cr, int cc, int tr, int tc) {
        queue.add(new Loc(cr, cc));
        visited[cr][cc] = true;

        while (!queue.isEmpty()) {
            Loc currLoc = queue.poll();
            int r = currLoc.r;
            int c = currLoc.c;

            if (r == tr && c == tc) {
                return map[r][c];
            }

            for (int i=0; i<DIR_NUM; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (inRange(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    map[nr][nc] = map[r][c] + 1;
                    queue.add(new Loc(nr, nc));
                }
            }
        }

        return 0;
    }

    static boolean inRange(int r, int c) {
        return 0<=r && r<l && 0<=c && c<l;
    }

}
