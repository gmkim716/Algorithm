import java.util.*;

public class Main {
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int MAX_N = 300;

    public static int n;
    public static int[][] grid = new int[MAX_N+1][MAX_N+1];
    public static int[][] prefixSum = new int[MAX_N+1][MAX_N+1];
    public static int[] dp = new int[MAX_N+1];

    // 시작점 (x1, y1), 끝점 (x2, y2)
    public static int getSum(int x1, int y1, int x2, int y2) {
        return prefixSum[x2][y2] - prefixSum[x1-1][y2] - prefixSum[x2][y1-1] + prefixSum[x1-1][y1-1];
    }

    // 시작행이 x1, 끝 행이 x2인 직사각형 중 가능한 최대 합 반환
    public static int getMaxArea(int x1, int x2) {
        
        // 시작행과 끝 행이 x1, x2로 정해지면, 각 열마다 x1부터 x2까지 행에 적혀있는 숫자들을 누적했을 때
        // 1차원에서 최대 연속 부분 수열의 합을 구하는 문제와 같아진다: 동적 계획법으로 간단히 해결 가능
        for (int y=1; y<=n; y++) {
            // y열에 있는 숫자들의 합을 구한다 
            int value = getSum(x1, y, x2, y);

            dp[y] = Math.max(value, dp[y-1]+value);
        }

        // dp 값 중 최댓값이 원하는 값이 된다
        int maxArea = INT_MIN;
        for (int y=1; y<=n; y++) {
            maxArea = Math.max(maxArea, dp[y]);
        }

        return maxArea;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // 누적합 배열 만들기
        prefixSum[0][0] = 0;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + grid[i][j]; 
            }
        }

        // 직사각형의 시작 행과 끝 행을 결정
        // 각 쌍에 대개 가능한 직사각형 중 최대 합을 계산해 답과 비교해서 최댓값을 갱신
        int ans = INT_MIN;
        for (int i=1; i<=n; i++) {
            for (int j=i; j<=n; j++) {  // j의 시작점은 i에서 부터*
                ans = Math.max(ans, getMaxArea(i, j));
            }
        }
        System.out.print(ans);
    }
}