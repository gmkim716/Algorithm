import java.util.Scanner; 

public class Main {
    public static final int MAX_SIZE = 4; 
    public static final int ASCII_NUM = 128;
    public static final int n = 4;
    public static final int NONE = -1; 

    // L, R, U, D
    public static final int[] dx = new int[]{0,0,-1,1};
    public static final int[] dy = new int[]{-1,1,0,0};

    public static int[][] grid = new int[MAX_SIZE][MAX_SIZE];
    public static int[][] temp = new int[MAX_SIZE][MAX_SIZE];

    public static void initializeTemp() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                temp[i][j] = 0;
            }
        }
    }

    public static void rotate() {
        // 1. temp 초기화
        initializeTemp();

        // 2. 회전한 grid 값을 temp에 저장
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                temp[i][j] = grid[n-j-1][i];  // **90도 회전
            }
        }

        // temp 값으로 grid
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = temp[i][j];
            }
        }
    }

    public static void drop() {
        // temp 초기화
        initializeTemp();

        for (int j=0; j<n; j++) {
            int idxVal = NONE;
            int nextIdx = n-1;

            for (int i=n-1; i>=0; i--) {  // *grid 아래에서 위로 확인
                // 값이 0일 때: pass
                if (grid[i][j] == 0) continue;
                
                // 값이 있을 때, 기준값이 NONE: 기준값을 업데이트
                if (idxVal == NONE) {
                    idxVal = grid[i][j];
                } 

                // 기준값과 관찰된 값이 같을 때: 아래의 값 2배, (관찰된 값 0) 
                else if (idxVal == grid[i][j]) {
                    temp[nextIdx--][j] = idxVal*2;  // 값 저장
                    idxVal = NONE;  // 기준값 초기화
                }

                // 기준값과 관찰값이 같지 않을 때: 관찰값을 저장, 기준값 변경
                else {
                    temp[nextIdx--][j] = idxVal;
                    idxVal = grid[i][j];
                }
            }

            // idxVal이 남아있다면 떨어뜨리기
            if (idxVal != NONE) {
                temp[nextIdx--][j] = idxVal;
            }
        }

        // grid 업데이트
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = temp[i][j];
            }
        }
    }

    public static void tilt(int dir) {
        // 1. dir만큼 시계방향으로 90' 회전 → 항상 아래로만 숫자를 떨어뜨리도록 유도
        for (int i=0; i<dir; i++) {
            rotate();
        }

        // 2. 아래 방향으로 떨어뜨리기
        drop(); 

        // 3. 처음 상태로 회전
        for (int i=0; i<4-dir; i++) {  // *4-dir
            rotate(); 
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        char dir = sc.next().charAt(0);
        
        // *아스키 코드를 이용해 mapper를 설정하면 편리함
        int[] dirMapper = new int[ASCII_NUM];
        dirMapper['D'] = 0;
        dirMapper['R'] = 1;
        dirMapper['U'] = 2;
        dirMapper['L'] = 3;

        // 기울이기 진행
        tilt(dirMapper[dir]);

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println(); 
        }
    }
}