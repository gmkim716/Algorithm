import java.util.Scanner; 

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int DIR_NUM = 4; 
    public static final int MAX_N = 20;

    // U, D, L, R: 방향은 문제를 보고 정해..: 시간 오래 잡아먹혔어
    public static final int[] dx = new int[]{-1,1,0,0};
    public static final int[] dy = new int[]{0,0,-1,1};

    public static int n, m, t;

    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] pos = new int[MAX_N][MAX_N];
    public static int[][] temp = new int[MAX_N][MAX_N];

    public static void printPos() {
        for (int i=0; i<n ;i++) {
            for (int j=0; j<n ;j++) {
                System.out.print(pos[i][j]+" ");
            }
            System.out.println(); 
        }
    }


    public static void initializeTemp() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                temp[i][j] = 0;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    // 기록하고 지웠다를 반복하는 방법보다 아래와 같이 *객체를 반환하는 방식이 깔끔
    public static Pair getMaxPos(int x, int y) {
        int maxVal = -1;
        Pair maxPos = new Pair(-1, -1);

        for (int i=0; i<DIR_NUM; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx, ny) && (maxVal < grid[nx][ny])) {
                maxVal = grid[nx][ny];
                maxPos = new Pair(nx, ny);
            }
        }
        return maxPos;
    }

    public static void updatePos() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n ;j++) {
                pos[i][j] = temp[i][j];
            }
        }
    }

    public static void move(int x, int y) {
        Pair maxPos = getMaxPos(x, y);
        int nx = maxPos.x, ny = maxPos.y;
        temp[nx][ny] ++; 
    }

    public static void moveAll() {
        // 1. temp 초기화
        initializeTemp();  
        
        // 2. 이동
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++) {
                if (pos[i][j] == 1) {
                    move(i, j);
                }
            }
        }

        // 3. temp 반영
        updatePos();  
    }

    public static void removeDuplicated() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (pos[i][j] >= 2) {
                    pos[i][j] = 0;
                }
            }
        }
    }

    public static void simulate() {
        moveAll();
        removeDuplicated(); 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); 
        m = sc.nextInt();  // 구슬의 개수
        t = sc.nextInt();  // 시간

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt(); 
            }
        }

        for (int i=0; i<m; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            r--; c--;
            pos[r][c] = 1; 
        }

        while (t-->0) {
            simulate();
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (pos[i][j] > 0) {
                    ans ++;
                }
            }
        }
        System.out.println(ans); 
    }
}