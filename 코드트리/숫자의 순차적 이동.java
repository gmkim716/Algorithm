import java.util.Scanner; 

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int DIR_NUM = 8;
    public static final int MAX_N = 20;

    // 12시부터 시계방향
    public static final int[] dx = new int[]{-1,-1, 0, 1, 1, 1, 0, -1};
    public static final int[] dy = new int[]{0 , 1, 1, 1, 0,-1,-1,-1};

    public static int n, m;

    public static Pair[] numPos = new Pair[MAX_N*MAX_N+1];
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

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n; 
    }

    public static void updateGrid() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (temp[i][j] > 0) {
                    grid[i][j] = temp[i][j];
                }
            }
        }
    }

    public static void changeMaxVal(int x, int y) {
        // 1. 가장 큰 값을 가진 위치를 찾기
        int maxVal = 0;
        int tx = -1, ty = -1;

        for (int i=0; i<DIR_NUM; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (inRange(nx, ny) && maxVal<grid[nx][ny]) {
                maxVal = grid[nx][ny];
                tx = nx; ty = ny;
            }
        }

        Pair tempPair = numPos[grid[x][y]];
        numPos[grid[x][y]] = numPos[grid[tx][ty]];
        numPos[grid[tx][ty]] = tempPair;

        // 2. 현재 값과 target 값의 위치를 변경
        // System.out.println(tx+" "+ty);
        temp[x][y] = grid[tx][ty];
        temp[tx][ty] = grid[x][y];
        
        updateGrid(); 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int num = sc.nextInt();
                grid[i][j] = num;
                numPos[num] = new Pair(i, j);
            }
        }

        while(m-->0) {
            // 범위 1~n^2
            for (int i=1; i<=n*n; i++) {
                changeMaxVal(numPos[i].x, numPos[i].y); 
                // printGrid();
                // System.out.println(); 
            }
        }
        printGrid();
    }
}