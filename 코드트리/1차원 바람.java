import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100;
    public static final int SHIFT_LEFT = 1;
    public static final int SHIFT_RIGHT = 0;

    public static int n, m, q;
    
    public static int[][] grid = new int[MAX_NUM+1][MAX_NUM+1];

    public static boolean hasSameNumber(int idxRow, int compareRow) {
        for (int i=1; i<=m; i++){
            if (grid[idxRow][i] == grid[compareRow][i]) {
                return true;
            }
        }
        return false;
    }

    public static int flip(int d) {
        return (d == SHIFT_LEFT) ? SHIFT_RIGHT : SHIFT_LEFT;
    }

    public static void simulate(int r, int d) {
        //== 1. 바람이 불어온 행에 대한 이동 ==//
        shift(r, d);

        // 바람의 방향 반전: 반대 방향으로 다른 행들을 처리하기 위함
        d = flip(d); 

        //== 전파 진행 ==//
        // 1) 같은 값을 가진 열이 있는지 확인
        // 2) 전파 진행
        // 3) 전파 이후 방향을 전환

        // 2. 위측 행으로 전파
        for (int i=r, dir=d; i>=2; i--) {  // for문에서 여러 개의 변수를 선언 가능, 여러 타입의 변수는?
            if (hasSameNumber(i, i-1)) {
                shift(i-1, dir); 
                dir = flip(dir);  
            } 

            // 같은 숫자가 없다면 전파를 중지
            else {
                break;
            }
        }

        // 3. 아래측 행으로 전파
        for (int i=r, dir=d; i<=n-1; i++) {
            if (hasSameNumber(i, i+1)) {
                shift(i+1, dir);
                dir = flip(dir);
            }

            // 같은 숫자가 없다면 전파를 중지
            else {
                break;
            }
        }
    }

    // *shift 방식 꼼꼼하게 익히기, m-1: 인덱스 주의
    // temp: 배열로 저장하는 것보다 하나의 값으로 저장하는 것이 메모리 절약
    public static void shift(int r, int d) {

        if (d == SHIFT_RIGHT) {
            int temp = grid[r][m];
            for (int i=m; i>=2; i--) {  
                grid[r][i] = grid[r][i-1];
            }
            grid[r][1] = temp;
        }

        else {
            int temp = grid[r][1];
            for (int i=1; i<=m-1; i++){ 
                grid[r][i] = grid[r][i+1];
            }
            grid[r][m] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt(); 

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        while (q-->0) {
            int r = sc.nextInt();  // 바람에 영향을 받는 행 번호 
            char d = sc.next().charAt(0);  // 바람의 방향 
            simulate(r, (d=='L') ? SHIFT_RIGHT : SHIFT_LEFT);
        }

        // 정답 출력
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++){ 
                System.out.print(grid[i][j]+" ");
            }
            System.out.println(); 
        }
    }
}