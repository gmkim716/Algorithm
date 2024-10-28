import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Integer> lst = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(1, 0);

        br.close();
    }

    static void dfs(int start, int cnt) {
        if (cnt == M) {
            printArr();
            return;
        }

        for (int i = start; i <= N; i++) {
            lst.add(i);
            dfs(i+1, cnt + 1);  // start+1: 더 큰 수만을 찾기 때문에 visited 체크가 필요하지 않다
            lst.remove(lst.size() - 1);
        }
    }

    static void printArr() {
        for (Integer num : lst) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
