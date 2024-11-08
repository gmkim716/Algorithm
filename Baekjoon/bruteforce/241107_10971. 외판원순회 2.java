apackage org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 순화하면 되기 때문에 출발 지점을 0으로 고정하는 것이 가능하다
// 출발지점을 고정하면 처리해야 할 경우의 수가 줄어 계산이 간단해진다
public class Main {

    static int N, ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] visited;
    static List<Integer> lst = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        lst.add(0);
        visited[0] = true;
        combination(1);

        System.out.println(ans);
    }

    static void combination(int depth) {
        if (depth == N) {
            findMinCost();
            return;
        }

        for (int i=1; i<N; i++) {
            if (!visited[i] && map[lst.get(lst.size()-1)][i] != 0) {
                lst.add(i);
                visited[i] = true;

                combination(depth + 1);

                lst.remove(lst.size() - 1);
                visited[i] = false;
            }
        }
    }

    static void findMinCost() {
        int sum = 0;

        // 경로의 비용 계산
        for (int i = 0; i < N - 1; i++) {
            sum += map[lst.get(i)][lst.get(i + 1)];
        }

        // 돌아올 수 있는 경우에만 계산
        if (map[lst.get(N - 1)][lst.get(0)] != 0) {
            sum += map[lst.get(N - 1)][lst.get(0)];
            ans = Math.min(ans, sum);
        }
    }
}
