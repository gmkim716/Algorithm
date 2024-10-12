import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final Pair NOT_EXISTS = new Pair(-1, -1);  // **
    public static final int DIR_NUM = 4; 
    public static final int MAX_NUM = 100;

    public static final int[] dx = new int[]{-1,0,1,0};
    public static final int[] dy = new int[]{0,1,0,-1};

    // 현재 위치를 나타내는 좌표
    public static Pair currCell;

    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    public static boolean[][] visited = new boolean[MAX_NUM][MAX_NUM];
    public static Queue<Pair> q = new LinkedList<>();
    public static int n, k, r, c;

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static boolean canGo(int x, int y, int targetNum) {
        return inRange(x, y) && !visited[x][y] && grid[x][y] < targetNum;
    }

    // visited 초기화
    public static void initializeVisited() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                visited[i][j] = false; 
            }
        }
    }

    public static void bfs() {
        int cX = currCell.x;
        int cY = currCell.y;
        visited[cX][cY] = true;
        q.add(currCell);

        int targetNum = grid[cX][cY];

        while(!q.isEmpty()) {
            Pair currPos = q.poll();
            int currX = currPos.x;
            int currY = currPos.y;

            for (int i=0; i<DIR_NUM; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];
                
                if (canGo(nx, ny, targetNum)) {
                    q.add(new Pair(nx, ny));
                    visited[nx][ny] = true;  // *
                }
            }
        }
    }
    
    // best 위치를 새로운 위치로 바꿔야 하는지 판단
    public static boolean needUpdate(Pair bestPos, Pair newPos) {
        
        // best.x == -1, best.y == -1이면 true
        if (bestPos.x == NOT_EXISTS.x && bestPos.y == NOT_EXISTS.y){
            return true; 
        }

        int bx = bestPos.x;
        int by = bestPos.y;

        int nx = newPos.x;
        int ny = newPos.y;

        // **숫자, -행, -열 순으로 더 큰 곳이 선택되야함?? 
        if(grid[nx][ny] != grid[bx][by]) {
            return grid[nx][ny] > grid[bx][by];
        }
        if (-nx != -bx) {
            return -nx > bx;
        }
        return -ny > -by;
    }

    public static boolean move() {
        // 0. 초기화 작업
        initializeVisited();

        // 1. bfs, 진행 가능한 모든 위치 탐색
        bfs();

        // 2. 진행 가능한 위치 중, 가장 우선순위가 높은 위치 구하기 
        Pair bestPos = NOT_EXISTS;  // 초기값 설정
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // 현재 위치 or 도달 불가 → 패스
                if (!visited[i][j] || i == currCell.x && j == currCell.y) {
                    continue;
                }

                Pair newPos = new Pair(i, j);
                // 업데이트 필요시 업데이트
                if (needUpdate(bestPos, newPos)) {
                    bestPos = newPos;
                }
            }
        }

        //==3. 위치를 이동==//
        // 움직일 위치가 없을 때 종료
        if (bestPos.x == NOT_EXISTS.x && bestPos.y == NOT_EXISTS.y) {
            return false;
        } 
        // 움직일 위치가 있다면 이동 
        else {
            currCell = bestPos;
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        r = sc.nextInt();
        c = sc.nextInt();

        currCell = new Pair(r-1, c-1);  // (r, c)가 아니라 (r-1, c-1)이므로 주의

        while(k-- > 0) {
            boolean isMoved = move();

            if(!isMoved) {
                break;
            }
        }

        int finalX = currCell.x;
        int finalY = currCell.y;

        System.out.println((finalX+1)+" "+(finalY+1));
    }
}