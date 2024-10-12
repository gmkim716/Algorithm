import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    public static final int DIR_NUM = 4;
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 100;

    public static int n, k;
    public static int r1, c1, r2, c2;
    public static int ans = INT_MAX;

    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] step = new int[MAX_N][MAX_N];
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];

    public static Queue<Pair> q = new LinkedList<>();  // queue
    public static ArrayList<Pair> stonePos = new ArrayList<>();  // 벽의 위치
    
    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }
    
    public static boolean canGo(int x, int y) {
        return inRange(x, y) && !visited[x][y] && grid[x][y] == 0;
    }
    
    public static void push(int nx, int ny, int nextStep) {
        visited[nx][ny] = true;
        q.add(new Pair(nx, ny));
        step[nx][ny] = nextStep;
    }

    public static int BFS() {
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

        // 도착지의 최소 거리
        if (visited[r2][c2]) {
            return step[r2][c2];
        } else {
            return INT_MAX;
        }
    }

    // idx: 현재 확인 중인 벽의 위치, cnt: 확인한 벽의 개수 
    public static void findMin(int idx, int cnt) {
        // 현재 탐색중인 돌의 idx가 마지막 idx일 때 종료
        if (idx == stonePos.size()) {  
        
            // 선택한 돌의 개수가 k일 때: BFS 탐색 진행
            if (cnt == k) {  
                // visited, step 초기화
                for (int i=0; i<n; i++) {
                    for (int j=0; j<n; j++) {
                        visited[i][j] = false;
                        step[i][j] = 0;
                    }
                }
        
                // (r1, c1) 지점에서 BFS 탐색
                push(r1, c1, 0);  // 시작점의 위치 입력
                int minDist = BFS();  // 최소거리 구하기 
                ans = Math.min(ans, minDist);
            }

            return;
        }

        // idx번째에 위치한 벽이 존재할 때와 아닌 경우에 대해 탐색
        grid[stonePos.get(idx).x][stonePos.get(idx).y] = 0;  // 벽 제거 
        findMin(idx+1, cnt+1);  // 확인 idx+1, 확인 개수+1
        grid[stonePos.get(idx).x][stonePos.get(idx).y] = 1;  // 벽 세우기

        findMin(idx+1, cnt);  // 확인 idx+1, 확인 개수+0
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();  
        k = sc.nextInt();

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt();
                if (grid[i][j] == 1) {
                    stonePos.add(new Pair(i, j));
                }
            }
        }

        r1 = sc.nextInt();
        c1 = sc.nextInt();
        r2 = sc.nextInt();
        c2 = sc.nextInt();
        r1--; c1--; r2--; c2--;

        findMin(0, 0);

        if(ans == INT_MAX) {
            ans = -1;
        }

        System.out.println(ans);
    }
}