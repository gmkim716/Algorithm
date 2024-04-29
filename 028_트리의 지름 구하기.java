import java.util.*;

public class Main {
    static boolean visited[];
    static int[] distance;
    static ArrayList<Edge>[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        A = new ArrayList[N+1];  // A의 크기 정의
        for (int i=1; i<=N; i++) {
            A[i] = new ArrayList<>();  // A의 각 요소에 ArrayList 추가
        }

        // input 값 입력
        for (int i=0; i<N; i++) {
            int S = sc.nextInt();  // 입력 쌍의 개수
            while (true) {
                // 값이 -1인지 여부 확인
                int E = sc.nextInt();
                if (E == -1) {
                    break;
                }
                // 정상적인 값이 추가되었을 때 A배열 S인덱스에 추가
                int V = sc.nextInt();
                A[S].add(new Edge(E, V));
            }
        }

        distance = new int[N+1];  // 거리 값 배열
        visited = new boolean[N+1];  // 방문 여부 배열
        BFS(1);  // 임의의 지점(1)에서 가장 먼 거리를 찾는다

        // 입력된 거리 값을 통해 Max를 찾는다
        int Max = 1;
        for (int i=2; i<=N; i++) {
            if (distance[Max] < distance[i]) {
                Max = i;
            }
        }

        // Max 지점으로부터 다시 한번 가장 먼 거리를 찾는다
        distance = new int[N+1];
        visited = new boolean[N+1];
        BFS(Max);

        Arrays.sort(distance);  // 가장 큰 값을 찾기 위해 정렬
        System.out.println(distance[N]);
    }

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;
        while(!queue.isEmpty()) {
            int now_node = queue.poll();
            for (Edge i : A[now_node]) {
                int e = i.e;
                int v = i.value;
                if (!visited[e]) {
                    visited[e] = true;
                    queue.add(e);
                    distance[e] = distance[now_node] + v;
                }
            }
        }
    }
}

// Edge 클래스 정의
class Edge {
    int e;
    int value;

    public Edge(int e, int value) {
        this.e  = e;
        this.value = value;
    }
}