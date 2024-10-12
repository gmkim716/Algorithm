import java.util.Scanner;

public class Main {
    public static final int DIR_NUM = 5;
    public static final int MAX_NUM = 100;

    // *(0, 0): 자기자신을 포함
    public static final int[] dx = new int[]{0,-1,0,1,0};
    public static final int[] dy = new int[]{0,0,1,0,-1};

    public static int n, m, q;

    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    public static int[][] tempGrid = new int[MAX_NUM][MAX_NUM];

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<m; 
    }

    // 가장 자리의 값 회전 
    public static void rotate(int x1, int y1, int x2, int y2) {
        // 0. 가장 왼쪽 위 모서리 값을 temp에 저장
        int temp = grid[x1][y1];

        // 1. 가장 왼쪽 열을 위로 한 칸씩 이동
        for (int i=x1; i<x2; i++) {
            grid[i][y1] = grid[i+1][y1];
        }

        // 2. 가장 아래쪽 행을 왼쪽으로 한 칸씩 이동
        for (int i=y1; i<y2; i++) {
            grid[x2][i] = grid[x2][i+1];
        }

        // 3. 가장 오른쪽 열을 아래로 한 칸씩 이동
        for (int i=x2; i>x1; i--) {
            grid[i][y2] = grid[i-1][y2];
        }

        // 4. 가장 위쪽 행을 오른쪽으로 한 칸씩 이동
        for (int i=y2; i>y1; i--) {
            grid[x1][i] = grid[x1][i-1];
        }

        // 5. temp값 입력
        grid[x1][y1+1] = temp;
    }

    public static int average(int x, int y) {
        int sum = 0;
        int cnt = 0;

        for (int i=0; i<DIR_NUM; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inRange(nx, ny)) {
                sum += grid[nx][ny];
                cnt ++; 
            }
        }
        return sum/cnt;
    }

    // 동시에 일어나야 하므로 임시 그리드에 저장
    public static void setAverage(int x1, int y1, int x2, int y2) {
        for (int i=x1; i<=x2; i++) {   
            for (int j=y1; j<=y2; j++) {
                tempGrid[i][j] = average(i, j);
            }
        }

        for (int i=x1; i<=x2; i++) {
            for (int j=y1; j<=y2; j++) {
                grid[i][j] = tempGrid[i][j];
            }
        }
    }

    public static void wind(int x1, int y1, int x2, int y2) {
        // 1. 경계에 있는 숫자들이 시계방향으로 회전
        rotate(x1, y1, x2, y2); 

        // 2. 동시에(순차적x) 사각형 내부의 값 전환: (현재 칸+인접칸)의 평균
        setAverage(x1, y1, x2, y2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt(); 

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        while (q-->0) {
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            r1--; c1--; r2--; c2--;

            wind(r1, c1, r2, c2);
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
}