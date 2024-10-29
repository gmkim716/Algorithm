import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N, S;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];

        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeSubset(0, 0);

        // S=0인 경우, 공집합이 포함되어 계산되어 있으므로 이를 제거한다
        if (S == 0) cnt --;

        System.out.println(cnt);
        br.close();
    }

    static void makeSubset(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                cnt ++;
            }
            return;
        }

        // 현재 인덱스를 포함하지 않는 경우: 다음 인덱스 이동, sum 유지
        makeSubset(idx+1, sum);

        // 현재 인덱스를 포함하는 경우, idx를 이동: 다음 인덱스 이동, sum+현재 인덱스 값
        makeSubset(idx+1, sum+arr[idx]);
    }
}
