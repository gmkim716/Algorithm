import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Node {
    int index, dist;

    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
}

class Element implements Comparable<Element> {
    int index, dist;

    public Element(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }

    @Override
    public int compareTo(Element e) {
        return this.dist - e.dist;  // dist 기준 오름차순 정렬
    }
}

public class Main {
    public static final int MAX_NUM = 100000;

    public static int n, m;
    public static ArrayList<Node>[] graph = new ArrayList[MAX_NUM+1];
    public static PriorityQueue<Element> pq = new PriorityQueue<>();

    public static int[] dist = new int[MAX_NUM+1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 그래프를 인접리스트로 표현
        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<Node>(); 
        }

        // 주어지는 값 입력: *학교를 출발점으로, 집을 도착점으로 설정하기 위해 s, e방향을 바꾸어서 입력
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[e].add(new Node(s, d));  // 도착점과 연결된 시작점을 나타내기
        }

        // dist에 매우 높은 값 입력
        for (int i=1; i<=n; i++) {
            dist[i] = (int)1e9;
        }

        // 다익스트라 알고리즘 구현
        // 시작점: 학교의 위치 N
        dist[n] = 0;  // 시작점의 거리값 0으로 갱신
        pq.add(new Element(n, 0));  // 시작점에 대한 우선순위 큐 입력, 거리: 0

        // pq에 남은 요소가 없을 때까지 진행
        while (!pq.isEmpty()) {
            int minIndex = pq.peek().index;  // 다음 목적지
            int minDist = pq.peek().dist;  // 시작점으로부터의 최소 거리
            pq.poll();  // 요소 제거

            // continue 조건 
            if (minDist != dist[minIndex]) {
                continue;
            }

            for (int j=0; j<graph[minIndex].size(); j++) {
                int targetIndex = graph[minIndex].get(j).index;
                int targetDist = graph[minIndex].get(j).dist;

                int newDist = dist[minIndex] + targetDist;
                if (dist[targetIndex] > newDist) {
                    dist[targetIndex] = newDist;
                    pq.add(new Element(targetIndex, newDist));
                }
            }
        }

        int ans = 0;
        for (int i=1; i<=n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        System.out.println(ans); 
    }
}