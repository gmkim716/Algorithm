import java.util.*;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static final int MAX_N = 100;
    public static final int DIR_NUM = 4;

    public static int n, k;

    public static int[][] a = new int[MAX_N][MAX_N];
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    public static int[][] step = new int[MAX_N][MAX_N];
    public static Queue<Pair> q = new LinkedList<>(); 

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && !visited[x][y] && a[x][y] == 1;
    }

    public static void push(int nx, int ny, int nextStep) {
        visited[nx][ny] = true;
        q.add(new Pair(nx, ny));
        step[nx][ny] = nextStep;
    }

    public static void BFS() {
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int x = curr.x;
            int y = curr.y;

            int[] dx = new int[]{-1,1,0,0};
            int[] dy = new int[]{0,0,-1,1};

            for (int dir=0; dir<DIR_NUM; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (canGo(nx, ny)) {
                    push(nx, ny, step[x][y]+1); 
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt(); 

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                a[i][j] = sc.nextInt();
                // 상한 귤의 위치 담기
                if (a[i][j] == 0) {
                    step[i][j] = -1;
                } else if (a[i][j] == 2) {
                    q.add(new Pair(i, j));
                    visited[i][j] = true; 
                }
            }
        }

        BFS(); 
        
        // 정답 출력
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (a[i][j] == 1 && step[i][j] == 0) {
                    System.out.print(-2 + " ");
                } else {
                    System.out.print(step[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}