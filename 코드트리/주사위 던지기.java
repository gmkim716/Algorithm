import java.util.Scanner;  

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final Pair OUT_OF_GRID = new Pair(-1, -1);  // *findNextPos가 오류일 때 반환받을 값
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100; 
    public static final int ASCII_NUM = 128;

    // R, L, U, D
    public static final int[] dx = new int[]{0,0,-1,1};
    public static final int[] dy = new int[]{1,-1,0,0};

    public static int n, m, x, y;

    // u, f, r: 주사위가 놓여있는 상태, 3개만 정의해도 다른 값을 알 수 있다 
    public static int u=1, f=2, r=3;  // *동일 타입에 대해 쉼표(,)로 이어서 변수 정의 가능
    
    public static int[][] grid = new int[MAX_N][MAX_N];

    public static boolean inRange(int x, int y) {
        return 0<=x&& x<n && 0<=y && y<n;
    }

    public static Pair findNextPos(int x, int y, int moveDir) {
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};
        int nx = x + dx[moveDir], ny = y + dy[moveDir];
        if(inRange(nx, ny))
            return new Pair(nx, ny);
        else
            return OUT_OF_GRID;
    }

    public static void simulate(int moveDir) {
        // moveDir 방향으로 굴렸을 때의 위치
        Pair nextPos = findNextPos(x, y, moveDir); 

        // 굴리는게 불가능한 경우, 패스
        if (nextPos == OUT_OF_GRID) {
            return;
        }

        // 위치 이동
        x = nextPos.x;
        y = nextPos.y;

        // **주사위가 움직이는 방향에 따라 3개 면의 상태를 조정
        if (moveDir == 0) {  // R
            int nu = 7-r;
            int nf = f;
            int nr = u;
            u = nu; f = nf; r = nr;
        }
        else if (moveDir == 1) {  // L
            int nu = r;
            int nf = f;
            int nr = 7-u;
            u = nu; f = nf; r = nr;
        }
        else if (moveDir == 2) {  // U
            int nu = f;
            int nf = 7-u;
            int nr = r;
            u = nu; f = nf; r = nr;
        }
        else {  // D
            int nu = 7-f;
            int nf = u;
            int nr = r;
            u = nu; f = nf; r = nr;
        }

        // 바닥에 적혀있는 숫자를 변경
        int down = 7-u;
        grid[x][y] = down;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();  // 주사위 굴릴 횟수
        x = sc.nextInt();  
        y = sc.nextInt(); 
        x--; y--;

        // *if 조건문 대신 mapper가 더 편함
        int[] dirMapper = new int[ASCII_NUM];  // ASCII_NUM 만큼 공간 생성
        dirMapper['R'] = 0;
        dirMapper['L'] = 1;
        dirMapper['U'] = 2;
        dirMapper['D'] = 3;

        // 시뮬레이션 진행
        grid[x][y] = 6;
        for (int i=0; i<m; i++) {
            char c = sc.next().charAt(0);
            simulate(dirMapper[c]);
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                ans += grid[i][j];
            }
        }
        System.out.println(ans); 
    }
}