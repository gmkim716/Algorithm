import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 200;

    public static int n, m;
    public static int[][] grid = new int[MAX_NUM+1][MAX_NUM+1];

    // *가능한 모든 모양 틀을 만들어 진행하기
    public static int[][][] shapes = new int[][][]{  // *3중 배열 진행
        {{1, 1, 0},
         {1, 0, 0},
         {0, 0, 0}},

        {{1, 1, 0},
         {0, 1, 0},
         {0, 0, 0}},
        
        {{1, 0, 0},
         {1, 1, 0},
         {0, 0, 0}},
        
        {{0, 1, 0},
         {1, 1, 0},
         {0, 0, 0}},
        
        {{1, 1, 1},
         {0, 0, 0},
         {0, 0, 0}},
        
        {{1, 0, 0},
         {1, 0, 0},
         {1, 0, 0}}
    };

    public static int getMaxSum(int x, int y) {
        int maxSum = 0;  // 합계를 저장할 변수

        for (int i=0; i<6; i++) {  // 6가지 경우의 수: 가능한 블럭의 모양
            boolean isPossible = true;  //
            int sum = 0;
            
            for (int dx=0; dx<3; dx++) {
                for (int dy=0; dy<3; dy++) {

                    // 블럭에 포함되지 않는 경우
                    if (shapes[i][dx][dy] == 0) {  // 6가지 경우의 수의 모든 점을 탐색, 1이 아닐경우 패스 
                        continue;
                    }

                    // 블럭에 포함되는 경우
                    if (x+dx >= n || y+dy >= m) {
                        isPossible = false;
                    } else {
                        sum += grid[x+dx][y+dy];
                    }
                }
            }
            if(isPossible) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();  
    
        // 주어지는 정보 입력
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int ans = 0;

        // 모든 위치에 대해 GetMaxSum 매소드 실행
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                ans = Math.max(ans, getMaxSum(i, j));
            }
        }
        System.out.println(ans);
    }
}