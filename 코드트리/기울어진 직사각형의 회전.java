import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100;
    
    public static int n, r, c, m1, m2, m3, m4, dir;

    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] tempGrid = new int[MAX_N][MAX_N];

    public static void rotate(int x, int y, int k, int l, int moveDir) {
        
        // if문 밖에서 선언해줘야 오류가 발생하지 않음
        int[] dx;
        int[] dy;
        int[] moveNums;

        if (moveDir == 0) {  // moveDir = 0
            dx = new int[]{-1, -1, 1, 1};
            dy = new int[]{1, -1, -1, 1};
            moveNums = new int[]{k, l, k, l};

        } else {             // moveDir = 1
            dx = new int[]{-1, -1, 1, 1};
            dy = new int[]{-1, 1, 1, -1};
            moveNums = new int[]{l, k, l, k};
        }

        // 1. tempGrid에 grid 값을 복사
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++) {
                tempGrid[i][j] = grid[i][j];
            }
        }

        // 2. 직사각형 경계를 따라가며 숫자를 이동했을 때의 결과값을 tempGrid에 저장
        for (int d=0; d<DIR_NUM; d++) {
            for (int q=0; q<moveNums[d]; q++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                tempGrid[nx][ny] = grid[x][y];
                x = nx; y = ny;  // (x, y)값을 (nx, ny)로 갱신
            }
        }

        // 3. tempGrid값을 grid에 이동
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = tempGrid[i][j];
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

        // r, c열에서 시작, 1~4 순서대로 m1~m4만큼 이동, 회전:{dir:0 = 반시계방향, dir:1 = 시계방향}
        r = sc.nextInt(); 
        c = sc.nextInt(); 
        m1 = sc.nextInt(); 
        m2 = sc.nextInt(); 
        m3 = sc.nextInt(); 
        m4 = sc.nextInt(); 
        dir = sc.nextInt();
        r--; c--; 

        rotate(r, c, m1, m2, dir);  // *m3, m4값은 사실 꼭 필요하지 않음

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }

}