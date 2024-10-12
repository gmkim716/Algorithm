import java.util.Scanner;

public class Main {
    public static final int DIR_NUM = 4; 
    public static final int MAX_N = 100;
    public static final int[] dx = new int[]{-1,0,1,0};    
    public static final int[] dy = new int[]{0,1,0,-1};    

    public static int n;
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    public static int cntSize, cntMaxSize;

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y))
            return false;
    
        if (visited[x][y] == true) 
            return false;

        return true; 
    }

    public static void dfs(int x, int y, int v) {
        visited[x][y] = true;
        cntSize ++; 
        cntMaxSize ++; 

        for (int i=0; i<DIR_NUM; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (canGo(nx, ny) && grid[nx][ny] == v) {
                dfs(nx, ny, v);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int cntBoomedBlock = 0;
        int cntMaxSizeBlock = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;

                    cntSize = 0;
                    cntMaxSize = 0;

                    dfs(i, j, grid[i][j]); 
                    
                    if (4 <= cntSize) {
                        cntBoomedBlock ++;
                    }
                    cntMaxSizeBlock = Math.max(cntMaxSizeBlock, cntMaxSize);
                }
            }
        }
        System.out.println(cntBoomedBlock+" "+cntMaxSizeBlock);
    }
}