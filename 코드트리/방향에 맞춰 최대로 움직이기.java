import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final int MAX_INT = Integer.MAX_VALUE;
    public static final int MAX_N = 4;

    // 1번 부터 8번 까지
    public static final int[] dx = new int[]{-1,-1,0,1,1,1,0,-1};
    public static final int[] dy = new int[]{0,1,1,1,0,-1,-1,-1};

    public static int n, ans;
    
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] direction = new int[MAX_N][MAX_N];

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static boolean canGo(int x, int y, int prevNum) {
        return inRange(x, y) && (prevNum < grid[x][y]);
    }

    // 재귀 반복 진행
    public static void findMax(int x, int y, int cnt) {

        // 정답 갱신
        ans = Math.max(ans, cnt);
        
        // (x, y)에서 가리키고 있는 방향 확인
        int currDir = direction[x][y]-1; 

        //== ** ==// 
        for (int i=0; i<n; i++) {  // (nx, ny): (x, y)에서 뻗을 수 있는 최대 범위
            int nx = x + dx[currDir]*i;
            int ny = y + dy[currDir]*i;

            // 진행이 가능할 때 cnt+1 하면서 findMax 탐색 진행
            if (canGo(nx, ny, grid[x][y])) {
                findMax(nx, ny, cnt+1);
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

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                direction[i][j] = sc.nextInt();
            }
        }

        // 시작 위치
        int r = sc.nextInt();
        int c = sc.nextInt();
        r--; c--;

        // 탐색 시작
        findMax(r, c, 0); 

        System.out.println(ans); 
    }
}