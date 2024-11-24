import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, ans = Integer.MIN_VALUE;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][];
        dp = new int[N][];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = new int[i + 1];
            dp[i] = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N == 1일 경우 바로 결과 출력
        if (N == 1) {
            System.out.println(map[0][0]);
            return;
        }

        // DP 초기값 설정
        dp[0][0] = map[0][0];
        dp[1][0] = dp[0][0] + map[1][0];
        dp[1][1] = dp[0][0] + map[1][1];

        // DP 테이블 채우기
        for (int i = 2; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + map[i][0];
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + map[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + map[i][j];
                }
            }
        }

        // 결과 계산
        for (int num : dp[N - 1]) {
            ans = Math.max(ans, num);
        }
        System.out.println(ans);
    }
}
