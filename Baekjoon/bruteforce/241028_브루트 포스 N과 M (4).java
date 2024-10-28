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

        dfs(1, 0);

        System.out.println(sb.toString());

        br.close();
    }
ㅁ
    static void dfs(int start, int cnt) {
        if (cnt == M) {
            printArr();
            return;
        }

        for (int i = start; i <= N; i++) {
            arr.add(i);
            dfs(i, cnt + 1);  // i부터 시작하면 현재값을 포함해 더 큰 수가 입력된다
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
