import java.util.Scanner; 
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x; 
        this.y = y;
    }
}

public class Main {
    public static final int DIR_NUM = 4; 
    public static final int MAX_N = 100; 

    public static final int[] dx = new int[]{-1,1,0,0};
    public static final int[] dy = new int[]{0,0,-1,1};

    public static int n, m, h; 

    public static int[][] grid = new int[MAX_N][MAX_N];  // 입력값 기록

    public static ArrayList<Pair> sPos = new ArrayList<>();  // 비를 피하는 장소 위치 

    // bfs에 필요한 변수들
    public static Queue<Pair> q = new LinkedList<>(); 
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    public static int[][] step = new int[MAX_N][MAX_N];

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    // 진행 여부 판단: 범위 내, 벽이 아닌 곳, 방문 기록 없음
    public static boolean canGo(int x, int y) {
        return inRange(x, y) && grid[x][y] != 1 && !visited[x][y];
    }

    public static void push(int nx, int ny, int newStep) {
        q.add(new Pair(nx, ny));  // q에 추가 
        visited[nx][ny] = true;  // 방문 표시
        step[nx][ny] = newStep;  // 최단 거리의 값 입력 
    }

    public static void BFS() {
        
        while (!q.isEmpty()){
            Pair curr = q.poll();
            int x = curr.x;
            int y = curr.y;

            for (int dir=0; dir<DIR_NUM; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (canGo(nx, ny)) {
                    push(nx, ny, step[x][y]+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();  // 격자의 크기
        m = sc.nextInt();  // 비를 피할 수 있는 공간
        h = sc.nextInt();  // 사람의 수 
    
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt();

                // 비를 피할 수 있는 장소를 sPos에 저장
                if (grid[i][j] == 3) {
                    sPos.add(new Pair(i, j));
                }
            }
        }

        // 은신처의 위치 표시: visited 표시, step = 0
        for (int i=0; i<sPos.size(); i++) {
            push(sPos.get(i).x, sPos.get(i).y, 0); 
        }

        // 은신처로부터 시작점까지의 거리 표시 진행
        BFS(); 

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // 사람이 있던 칸이 아닐 때 0 출력
                if (grid[i][j] != 2) {
                    System.out.print(0+" ");
                } 
                // 사람이 있던 칸일 경우
                else {
                    // 도작지점에서 출발했을 때 도착 기록이 없다면 -1 출력
                    if (!visited[i][j]) {
                        System.out.print(-1+" ");
                    } 
                    // 도착지점에서 출발해서 도착 기록이 있다면 최소시간 출력
                    else {
                        System.out.print(step[i][j]+" ");
                    }
                }
            }
            System.out.println(); 
        }
    }
}