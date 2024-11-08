import java.util.*;

// 0-1 BFS 문제: 순간이동 비용이 0이므로 deque의 앞에서 추가하고, 한칸 이동은 비용이 1이므로 deque의 뒤에서 추가한다
public class Main {

    static int N, K;
    static int[] minTimeLst = new int[100001];
    static boolean[] visited = new boolean[100001];
    static Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        Arrays.fill(minTimeLst, Integer.MAX_VALUE);  // 비용의 최소값을 찾는 것이 목적이기 때문에 최댓값으로 초기화 진행

        findMinTime();
        System.out.println(minTimeLst[K]);
    }

    static void findMinTime() {
        minTimeLst[N] = 0;
        deque.addFirst(N);

        while (!deque.isEmpty()) {
            int currLoc = deque.poll();

            if (visited[currLoc]) continue;  // 이미 방문한 적이 있다면 통과

            // 방문한 적이 없을 때 진행
            visited[currLoc] = true;

            // 순간이동은 비용이 0이므로 앞에서 추가
            canGo(currLoc * 2, minTimeLst[currLoc], false);

            // 한칸 이동은 비용이 1이므로 뒤에서 추가
            canGo(currLoc + 1, minTimeLst[currLoc] + 1, true);
            canGo(currLoc - 1, minTimeLst[currLoc] + 1, true);
        }
    }

    static void canGo(int nextLoc, int newTime, boolean needCost) {
        // 다음 위치의 조건: 범위 내에 있다 / 방문한 적이 없다 / 최소 비용을 찾았다
        if (inRange(nextLoc) && !visited[nextLoc] && minTimeLst[nextLoc] >= newTime) {
            minTimeLst[nextLoc] = newTime;  // 최소 비용으로 갱신한다

            if (!needCost) {
                deque.addFirst(nextLoc);
            } else {
                deque.addLast(nextLoc);
            }
        }
    }

    static boolean inRange(int x) {
        return 0<=x && x<=100000;  // 시작 위치가 0일 수 있다 (문제에서 주어지는 조건)
    }
}