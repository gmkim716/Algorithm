import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    static int N, total;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> lst = new ArrayList<>();

    static final int DIR_NUM = 4;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            String str = br.readLine();
            for (int j=0; j<N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    total++;
                    lst.add(cntBuilding(i, j));
                }
            }
        }

        System.out.println(lst.size());

        Collections.sort(lst);
        for (Integer num: lst) {
            System.out.println(num);
        }
    }

    static int cntBuilding(int r, int c) {
        int ret = 1;
        visited[r][c] = true;

        for (int i=0; i<DIR_NUM; i++) {
            int nr = r + dy[i];
            int nc = c + dx[i];

            if (canGo(nr, nc)) {
                ret += cntBuilding(nr, nc);
            }
        }
        return ret;
    }

    static boolean canGo(int r, int c) {
        return inRange(r, c) && map[r][c] == 1 && !visited[r][c];
    }

    static boolean inRange(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }
}
