import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int MAX_N = 20;
    public static final int DIR_NUM = 8;
    public static final Pair OUT_OF_GRID = new Pair(-1, -1); 

    // 12시부터 시계방향
    public static final int[] dx = new int[]{-1,-1,0,1,1,1,0,-1};
    public static final int[] dy = new int[]{0,1,1,1,0,-1,-1,-1};

    public static int n, m;

    public static ArrayList<Integer>[][] grid = new ArrayList[MAX_N][MAX_N];  // **2차원배열에 ArrayList 추가 

    public static Pair getPos(int num) {
        // num이 있는 위치를 찾을 수 있는 경우
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++) 
                for (int k=0; k<grid[i][j].size(); k++) {
                    if (grid[i][j].get(k) == num)
                        return new Pair(i, j); 
                }

        // num이 있는 위치를 찾지 못한 경우
        return new Pair(-1, -1);  // 해설과 다르게 설정 주의
    }

    public static boolean inRange(int x, int y) {
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static Pair getNextPos(Pair pos) {
        int x = pos.x, y = pos.y;

        // 인접한 8개 칸 중 가장 값이 큰 위치 반환
        int maxVal = -1;
        Pair maxPos = OUT_OF_GRID;  // *상태를 정의하는 변수를 사용하면 효과적이다
        
        for (int i=0; i<DIR_NUM; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            
            if (inRange(nx, ny)) {
                for (int j=0; j<grid[nx][ny].size(); j++) {  // ArrayList
                    if(maxVal < grid[nx][ny].get(j)) {
                        maxVal = grid[nx][ny].get(j);
                        maxPos = new Pair(nx, ny); 
                    }
                }
            }
        }

        return maxPos; 
    }

    public static void move(Pair pos, Pair nextPos, int num) {
        int x = pos.x, y = pos.y;
        int nx = nextPos.x, ny = nextPos.y;

        // 1. (x, y) 위치에 있던 숫자들 중 num위에 있는 숫자들을 옆 위치로 옮겨준다
        boolean toMove = false;
        for (int i=0; i<grid[x][y].size(); i++) {
            if (grid[x][y].get(i) == num) {  // num이 위치한 순서(i) 찾기
                toMove = true; 
            }

            // **num의 위치 + 이후의 위치에 있는 값 이동 
            if (toMove) {
                grid[nx][ny].add(grid[x][y].get(i));  // grid[x][y].get(i)에 있던 값을 grid[nx][ny]에 추가
            }
        }

        // 2. (x, y)에 있던 숫자들 중에서 움직인 숫자 비우기
        while (grid[x][y].get(grid[x][y].size()-1) != num) { 
            grid[x][y].remove(grid[x][y].size()-1);  // num을 발견하기 전까지 제거
        }
        grid[x][y].remove(grid[x][y].size()-1);  // num 까지 제거 
    }

    public static void simulate(int num) {
        // 1. num이 속한 위치
        Pair pos = getPos(num);

        // 2. num이 이동할 다음 칸 탐색
        Pair nextPos = getNextPos(pos);

        // 3. 이동이 가능하다면, 이동
        if (nextPos != OUT_OF_GRID) {
            move(pos, nextPos, num);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();    
        m = sc.nextInt();  // 움직임의 횟수

        //== grid 입력 방법 ==//
        // 1. *grid[i][j]에 ArrayList 생성
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = new ArrayList<>();  
            }
        }
        
        // 2. ArrayList에 add로 값 추가
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int num = sc.nextInt();
                grid[i][j].add(num); 
            }
        }

        while (m-->0) {
            int num = sc.nextInt();
            simulate(num); 
        }
        
        // 정답 출력
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j].size() == 0) {
                    System.out.print("None");
                } 
                else {
                    // 가장 위에 적혀있는 숫자부터 거꾸로 출력 
                    for (int k=grid[i][j].size()-1; k>=0; k--) {
                        System.out.print(grid[i][j].get(k)+" ");
                    }
                }
                System.out.println();
            }
        }
    }
}