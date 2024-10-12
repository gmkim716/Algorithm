import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100; 
    public static final int DIR_NUM = 4; 
    public static int n, m;

    // 상, 하, 좌, 우
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    public static boolean[][] visited = new boolean [MAX_NUM][MAX_NUM];

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<= y && y<m;
    }

    public static void go(int x, int y) {
        visited[x][y] = true;
        
        for (int i=0; i<DIR_NUM; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            
            if (inRange(nx, ny) && grid[nx][ny] == 1 && visited[nx][ny] == false) {
                go(nx, ny);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt(); 

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        go(0, 0); 
        
        if (visited[n-1][m-1] == true) {
            System.out.println(1);
        } else {
            System.out.println(0); 
        }
    }
}