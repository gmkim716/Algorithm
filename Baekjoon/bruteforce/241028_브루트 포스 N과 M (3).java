import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Integer> arr = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(0);  // 모든 요소들을 탐색해야 하기 때문에 start가 필요하지 않다

        System.out.println(sb.toString());

        br.close();
    }

    static void dfs(int cnt) {
        if (cnt == M) {
            printArr();
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr.add(i);
            dfs(cnt + 1);
            arr.remove(arr.size() - 1);
        }
    }

    static void printArr() {
        // 문자얼을 생성하는 방법으로 시간초과 문제를 해결할 수 있다
        for (int num: arr) {
            sb.append(num).append(" ");
        }
        sb.append("\n");

    }
}
