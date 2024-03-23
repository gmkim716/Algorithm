import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] S = new long[N];
        long[] C = new long[M];  // M으로 나누었을 때의 나머지 리스트

        long ans = 0;

        st = new StringTokenizer(bf.readLine());
        S[0] = Long.parseLong(st.nextToken());
        for (int i=1; i<N; i++) {
            S[i] = S[i-1] + Long.parseLong(st.nextToken());
        }

        for (int i=0; i<N; i++) {
            int remainder = (int)(S[i] % M);
            if (remainder == 0) ans ++;
            C[remainder] ++;
        }

        for (int i=0; i<M; i++) {
            if (C[i] > 1) {
                ans = ans + (C[i]*(C[i]-1))/2;  // C[i]*(C[i]-1))/2 <- nC2: n개의 선택지 중에서 2개를 선택
            }
        }
        System.out.println(ans);
    }
}