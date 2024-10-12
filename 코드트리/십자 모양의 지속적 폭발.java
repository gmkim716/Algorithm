import java.util.Scanner; 

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 200; 
    public static final int MAX_M = 10;

    public static final int[] dr = new int[]{-1,0,1,0};
    public static final int[] dc = new int[]{0,1,0,-1};
    
    public static int n, m;

    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] temp = new int[MAX_N][MAX_N];

    public static void printGrid() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println(); 
        }
    }
    
    public static void printTemp() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(temp[i][j]+" ");
            }
            System.out.println(); 
        }
    }


    public static void initializeTemp() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                temp[i][j] = 0;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static void boom(int row, int col, int pow) {
        for (int p=0; p<pow; p++) {
            for (int d=0; d<DIR_NUM; d++) {
                int nr = row + dr[d]*p;
                int nc = col + dc[d]*p;
                
                if (inRange(nr, nc)) {
                    grid[nr][nc] = 0;
                }
            }
        }
    }

    public static void drop() {
        initializeTemp();

        for (int j=0; j<n; j++) {
            int currIdx = n-1; 

            for (int i=n; i>=0; i--) {
                if (grid[i][j] != 0) {
                    // System.out.println("currIdx: " + currIdx);
                    temp[currIdx--][j] = grid[i][j];
                }
            }
        }

        // temp값으로 grid를 업데이트
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = temp[i][j];
            }
        }
    }

    public static void explode(int c) {
        for (int i=0; i<n; i++) {
            // 폭탄이 존재하지 않을 때: pass
            if (grid[i][c] == 0) continue;
            
            // 폭탄이 존재할 때: boom
            if (grid[i][c] != 0) {
                boom(i, c, grid[i][c]);
                break;
            }
        }
        
        // 떨어뜨리기
        drop(); 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i=0; i<n; i++) {
            for (int j =0; j<n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        while (m-->0) {
            int c = sc.nextInt();  // 폭탄이 터지는 열(가장 위의 행)
            c--;
            explode(c);
        }

        printGrid(); 
    }
}