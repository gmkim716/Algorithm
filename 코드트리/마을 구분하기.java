import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static final int MAX_N = 25;
    public static final int DIR_NUM = 4;
    
    public static final int[] dx = new int[]{-1,0,1,0};
    public static final int[] dy = new int[]{0,1,0,-1};

    public static int n;
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    public static ArrayList<Integer> peopleNums = new ArrayList<>();  // *
    public static int peopleNum;

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }
    
    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) {  // 범위 밖에 위치
            return false;
        }

        if (visited[x][y] || grid[x][y] == 0) {  // 방문 기록이 있거나, 벽인 경우
            return false;
        }

        return true;
    }

    
    public static void dfs(int x, int y) {
        for (int dir=0; dir<DIR_NUM; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (canGo(nx, ny)) {
                visited[nx][ny] = true;
                peopleNum ++;
                dfs(nx, ny);
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
                if (visited[i][j] == false && grid[i][j] == 1) {
                    visited[i][j] = true;
                    peopleNum = 1;
                    
                    dfs(i, j);

                    // 각 마을마다 사람 수를 저장
                    peopleNums.add(peopleNum); 
                }
            }
        }

        Collections.sort(peopleNums);  // Collections를 이용해 정렬 

        // 정답 입력
        System.out.println(peopleNums.size()); 
        for (int i=0; i<peopleNums.size(); i++) {
            System.out.println(peopleNums.get(i)); 
        }
    }
}