import java.util.Scanner;

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_HEIGHT = 100;
    public static final int MAX_NUM = 50;
    
    public static final int[] dx = new int[]{-1,0,1,0};
    public static final int[] dy = new int[]{0,1,0,-1};

    public static int n, m;
    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    public static boolean[][] visited = new boolean[MAX_NUM][MAX_NUM];
    public static int zoneNum;
    
    // visited를 초기화 하기 위한 메서드: 매번 재생성하지 않는 방법
    public static void initialVisited() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<m ;j++) {
                visited[i][j] = false;
            }
        }
    }
    
    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<m;
    }

    public static boolean canGo(int x, int y, int k) {
        if (!inRange(x, y)) 
            return false;

        if (visited[x][y] || grid[x][y] <= k)   // *방문 기록이 있거나, k보다 낮을 때
            return false;

        return true;
    }
    
    public static void dfs(int x, int y, int k) {
        for (int i=0; i<DIR_NUM; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (canGo(nx, ny, k)) {
                visited[nx][ny] = true;
                dfs(nx, ny, k); 
            }
        }
    }
    
    public static void getZoneNum(int k) {
        
        // 새로운 탐색 시작 시, 초기화
        zoneNum = 0;
        initialVisited();

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (canGo(i, j, k)) {
                    visited[i][j] = true;
                    zoneNum ++;

                    dfs(i, j, k);
                }
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
        
        int ans = 0; 
        int maxZoneNum = -1;
        for (int k=1; k<=MAX_HEIGHT; k++) {
            getZoneNum(k);

            if (zoneNum > maxZoneNum) {
                maxZoneNum = zoneNum;
                ans = k; 
            }
        }

        System.out.println(ans+" "+maxZoneNum);
    }
}