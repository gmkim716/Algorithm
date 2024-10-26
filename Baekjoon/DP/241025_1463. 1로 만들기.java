import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int ans;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        bfs(N);
        System.out.println(ans);

        br.close();
    }

    static void bfs(int n) {
        int cnt = 0;
        queue.add(n);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i=0; i<size; i++) {
                int x = queue.poll();

                // 종료 조건
                if (x == 1) {
                    ans = cnt;
                    return;
                }
                if (x % 3 == 0) {
                    queue.add(x / 3);
                }
                if (x % 2 == 0) {
                    queue.add(x / 2);
                }
                queue.add(x - 1);
            }

            cnt ++;
        }

    }
}
