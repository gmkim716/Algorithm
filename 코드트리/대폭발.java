import java.util.Scanner;
import java.util.ArrayList;

class Pair{
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int DIR_NUM = 4; 
    public static final int MAX_N = 100;
    public static final int MAX_M = 8;

    public static final int[] dx = new int[]{-1,0,1,0};
    public static final int[] dy = new int[]{0,1,0,-1};

    public static int[][] grid = new int[MAX_N+1][MAX_N+1];
    public static boolean[][] isBoom = new boolean[MAX_N+1][MAX_N+1];

    public static int n, m, r, c;

    public static void printBoomPos() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(isBoom[i][j]+" ");
            }
            System.out.println(); 
        }
    }

    public static void initailizeIsBoom() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                isBoom[i][j] = false;
            }
        }
    }
    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static void expand(int x, int y, int dist) {
        for (int i=0; i<DIR_NUM; i++) {
            int nx = x + dx[i]*dist;
            int ny = y + dy[i]*dist;

            if (inRange(nx, ny)) {
                isBoom[nx][ny] = true; 
            }
        }
    }

    public static void simulate(int dist) {
        // isBoom 값을 초기화
        initailizeIsBoom(); 

        // 폭탄 입력
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] > 0) {
                    expand(i, j, dist);
                }
            }
        }

        // grid를 isBoom으로 업데이트 
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (isBoom[i][j]) {
                    grid[i][j] = 1; 
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();  // 시간
        r = sc.nextInt(); 
        c = sc.nextInt();

        grid[r-1][c-1] = 1;

        int dist = 1; 
        while (m-->0) {
            simulate(dist);
            dist *= 2;  // *dist 값 관리
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                ans += grid[i][j];
            }
        }

        System.out.println(ans); 
    }
}