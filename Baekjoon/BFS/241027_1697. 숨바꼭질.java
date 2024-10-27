import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, ans;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K);

        System.out.println(ans);
        br.close();
    }

    static void bfs(int N, int K) {
        int cnt = 0;
        q.add(N);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i=0; i<size; i++) {
                int x = q.poll();
                visited[x] = true;

                if (x == K) {
                    ans = cnt;
                    return;
                }

                if (canGo(x*2)) {
                    q.add(x*2);
                }

                if (canGo(x+1)) {
                    q.add(x+1);
                }

                if (canGo(x-1)) {
                    q.add(x-1);
                }
            }
            cnt ++;
        }

    }

    static boolean canGo(int n) {
        return inRange(n) && !visited[n];
    }

    static boolean inRange(int n) {
        return 0<=n && n<=100000;
    }
}
