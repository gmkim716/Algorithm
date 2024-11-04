import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, ans = -1;
    static int[] arr;
    static int[] output;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        output = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(ans);
    }

    // 조합 만들기: depth, visited 사용
    static void dfs(int depth) {
        if (depth == N) {
            findMax(output);
            return;
        }

        for (int i=0; i<N; i++) {
            if (!visited[i]) {
                visited[i] = true;  // visited 체크 후 배열에 저장
                output[depth] = arr[i];

                dfs(depth+1);  // 다음 깊이 탐색

                visited[i] = false;  // visited 체크 해제
            }
        }
    }

    static void findMax(int[] arr) {
        int sum = 0;
        for (int i=1; i<N; i++) {
            sum += Math.abs(arr[i-1] - arr[i]);
        }
        ans = Math.max(ans, sum);
    }
}

