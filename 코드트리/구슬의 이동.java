import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

// *구슬의 우선순위: 속도, 번호 순 → {x, y, z} = {속도, 번호, 방향} 순으로 관리하기 위해 정렬 사용
class Pair implements Comparable<Pair>{
    int x, y, z;
    public Pair(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public int compareTo(Pair b) {
        if (x != b.x) return x - b.x;
        if (y != b.y) return y - b.y;
        return z-b.z;
    }
}

public class Main {
    public static final int MAX_N = 50;
    public static final int ASCII_NUM = 128;

    public static final int[] dx = new int[]{-1,0,1,0};
    public static final int[] dy = new int[]{0,1,0,-1};

    public static int n, m, t, k; 

    // *2차원 배열 + ArrayList
    public static ArrayList<Pair>[][] grid = new ArrayList[MAX_N][MAX_N];
    public static ArrayList<Pair>[][] nextGrid = new ArrayList[MAX_N][MAX_N];

    public static int[] mapper = new int[ASCII_NUM];

    public static void initializeGrid() {
        for (int i=0; i<n; i++) 
            for (int j=0; j<n; j++) 
                grid[i][j] = new ArrayList<>();
    }

    public static void initializeNextGrid() {
        for (int i=0; i<n; i++) 
            for (int j=0; j<n; j++) 
                nextGrid[i][j] = new ArrayList<>();
    }

    public static boolean inRange(int x, int y) {
        return 0<=x && x <n && 0<=y && y<n;
    }

    public static Pair nextPos(int x, int y, int v, int d) {
        
        for (int i=0; i<v; i++){ 
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 밖으로 나갔을 때 
            if (!inRange(nx, ny)) {
                d = (d+2)%4;  // 반대 방향으로 전환
                nx = x + dx[d]; ny = y + dy[d];  // 이전 위치로 되돌림
            } 
            x = nx; y = ny;
        }
        
        return new Pair(x, y, d);
    }

    public static void moveAll() {
        for (int i=0; i<n; i++) 
            for (int j=0; j<n; j++) 
                // *비어있는 경우 size() = 0 : boolean 타입으로 확인하지 않고 size()로 확인
                for (int k=0; k<grid[i][j].size(); k++) {
                    int vel = grid[i][j].get(k).x;  // 속도 
                    int num = grid[i][j].get(k).y;  // 번호
                    int dir = grid[i][j].get(k).z;  // 방향
                    
                    int nx = nextPos(i, j, -vel, dir).x;
                    int ny = nextPos(i, j, -vel, dir).y;
                    int nd = nextPos(i, j, -vel, dir).z;
                    
                    nextGrid[nx][ny].add(new Pair(vel, num, nd));
                }
    }

    public static void modifyDuplicated() {
        for (int i=0; i<n; i++) 
            for (int j=0; j<n; j++) 
                // k개 이상일 경우
                if (nextGrid[i][j].size() >= k) {  

                    // 우선순위가 높은 k개만 남겨두기
                    Collections.sort(nextGrid[i][j]);  // 정렬

                    // k개가 남을 때까지 제거
                    while (nextGrid[i][j].size() > k) {
                        nextGrid[i][j].remove(nextGrid[i][j].size()-1);
                    }
                }
    }

    public static void updateGrid() {
        for (int i=0; i<n; i++) 
            for (int j=0; j<n; j++) 
                grid[i][j] = (ArrayList<Pair>) nextGrid[i][j].clone();  // *clone(): 복사, *캐스트 형변환 필요 
    }   

    public static void simulate() {
        initializeNextGrid(); 

        moveAll();
        modifyDuplicated(); 

        updateGrid();
    }

    public static void main(String[] args) {
        mapper['U'] = 0;
        mapper['R'] = 1;
        mapper['D'] = 2;
        mapper['L'] = 3;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();  // 격자의 크기 
        m = sc.nextInt();  // 구슬의 개수
        t = sc.nextInt();  // 시간
        k = sc.nextInt();  // k개 이상의 구슬이 같은 칸에 위치하면 우선순위가 높은 구슬 k개만 살아남고 나머지는 사라짐

        initializeGrid();
        initializeNextGrid(); 

        for (int i=0; i<m; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            char d = sc.next().charAt(0);
            int v = sc.nextInt();  // 속도
            r--; c--;

            // 속도, 번호: 내림차순으로 관리하기 위해 음수로 처리
            grid[r][c].add(new Pair(-v, -(i+1), mapper[d]));
        }

        while (t-->0) 
            simulate();

        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                ans += grid[i][j].size();
            }
        }
        System.out.println(ans); 
    }
}