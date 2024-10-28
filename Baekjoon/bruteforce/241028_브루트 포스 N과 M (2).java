import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Integer> arr = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];  // 동일한 값이 여러번 포함되지 않도록 함

        dfs(0);

        br.close();
    }

    static void dfs(int cnt) {
        // 종료조건
        if (cnt == M) {
            printArr();
            return;
        }

        // 백트래킹
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                arr.add(i);  // 현재 숫자를 추가
                visited[i] = true;

                dfs(cnt + 1);

                arr.remove(arr.size() - 1);
                visited[i] = false;
            }

        }
    }

    static void printArr() {
        for (Integer integer : arr) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
}
