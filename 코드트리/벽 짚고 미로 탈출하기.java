import java.util.Scanner; 

public class Main {
    public static final int DIR_NUM = 4; 
    public static final int MAX_N = 100;

    // 시계 방향: 3/6/9/12
    public static int[] dx = new int[]{0,1,0,-1};
    public static int[] dy = new int[]{1,0,-1,0};
    
    public static int n, currX, currY, currDir, time;  // *전역변수 표기(curr)
    public static char[][] grid = new char[MAX_N][MAX_N];

    // 동일한 위치에서 동일한 방향으로 진행했던지 표시
    public static boolean[][][] visited = new boolean[MAX_N][MAX_N][DIR_NUM];

    public static void printVisited() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    // 진행 방향에 벽이 있는지 확인
    public static boolean isWall(int x, int y) {
        return inRange(x, y) && grid[x][y] == '#';
    }

    public static void simulate() {
        // 종료 조건 확인: 현재 위치에서 같은 방향으로 진행한 적이 있는 지 확인
        if (visited[currX][currY][currDir]) {
            System.out.println(-1);
            System.exit(0);  // System.exit(x): 프로그램 전체 종료({0:정상 종료, 1:오류 종료})
        }   

        // 현재 위치 표시
        visited[currX][currY][currDir] = true;

        //==바라보고 있는 방향으로 이동이 가능한지 확인==//
        int nx = currX+dx[currDir];
        int ny = currY+dy[currDir];

        // 1. 막혀있을 때
        if (isWall(nx, ny)) {
            // 시계 반대 방향 전환
            currDir = (currDir+DIR_NUM-1)%DIR_NUM;
        } 

        // 2. 막히지 않았을 때, 다음 위치가 격자 외부
        else if (!inRange(nx, ny)) {
            currX = nx; currY = ny;
            time ++;
        }
        
        // 3. 막히지 않았을 때, 다음 위치가 격자 내부
        else {
            // 3-1. 이동했을 때, 오른쪽에 벽이 있다면: 이동
            int rx = nx+dx[(currDir+1)%4];
            int ry = ny+dy[(currDir+1)%4];

            if (isWall(rx, ry)) {
                currX = nx; currY = ny;
                time ++;
            }

            // 3-2. 이동했을 때, 오른쪽에 벽이 없다면: 2칸 이동 & 방향 전환
            else {
                // System.out.println("전진/오른쪽에 벽이 없음/코너 돌기");
                currX = rx; currY = ry;
                currDir = (currDir+1)%4;                
                time += 2;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        currX = sc.nextInt();
        currY = sc.nextInt();
        currX--; currY--;
        currDir = 0; time = 0;

        for (int i=0; i<n; i++) {
            String str = sc.next();
            for (int j=0; j<n; j++) {
                grid[i][j] = str.charAt(j);
            }
        }

        // 격자를 빠져나올 때까지 진행
        do {
            simulate();
        } while (inRange(currX, currY));  

        System.out.println(time);
    }
}