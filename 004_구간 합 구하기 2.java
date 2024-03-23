import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));  // bf: 입력을 받기 위한 BufferedReader 객체

        StringTokenizer st = new StringTokenizer(bf.readLine());  // st: 입력을 받은 문자열을 공백을 기준으로 나누기 위한 StringTokenizer 객체
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] A = new int[N+1][N+1];
        int[][] S = new int[N+1][N+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(bf.readLine());  // 초기화 필요
            for (int j=1; j<=N; j++) {
                A[j][i] = Integer.parseInt(st.nextToken());
                S[j][i] = S[j-1][i] + S[j][i-1] - S[j-1][i-1] + A[j][i];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());  // 초기화 필요
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int ans = S[y2][x2] - S[y2][x1-1] - S[y1-1][x2] + S[y1-1][x1-1];
            System.out.println(ans);
        }
    }
}