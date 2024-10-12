import java.util.Scanner; 
import java.util.ArrayList;
import java.util.PriorityQueue;


class Node {
    int index, dist;

    public Node (int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
}

class Element implements Comparable<Element> {
    int dist, index;

    public Element(int dist, int index) {
        this.dist = dist;
        this.index = index;
    }

    @Override 
    public int compareTo(Element e) {
        return this.dist - e.dist;  // 거리 기준 오름차순
    }
}

public class Main {
    public static final int MAX_N = 20000;

    public static int n, m, k; 
    public static ArrayList<Node>[] graph = new ArrayList[MAX_N+1];  // *연결리스트 표현: ArrayList를 각 요소로 가지고 있는 배열 
    public static PriorityQueue<Element> pq = new PriorityQueue<>(); 

    public static int[] dist = new int[MAX_N+1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();  // 각각의 graph[i] 요소들은 ArrayList 구조를 가짐
        }

        while (m-->0) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();

            // 연결리스트에 인접 노드 입력
            // 양방향 그래프이기 때문에 x→y, y→x 지정
            graph[s].add(new Node(e, v));
            graph[e].add(new Node(s, v)); 
        }

        // 초기값을 매우 큰 값을 설정
        for (int i=1; i<=n; i++) {
            dist[i] = (int)1e9;  
        }

        // 시작 위치의 dist는 0으로 설정
        dist[k] = 0;

        // 우선순위 큐에 시작점을 넣어준다
        // 거리가 가까운 순으로 나와야 하며, (거리, 정점 번호) 형태로 넣어야 함: 해당 지점에 대한 정보 필요
        pq.add(new Element(0, k));  // 시작점과 시작점의 거리 = 0

        //==O(|E|log|V|) 다익스트라 코드==//
        // 우선순위 큐에 원소가 남아있는 동안 진행
        while (!pq.isEmpty()) {
            int minDist = pq.peek().dist;  // *peek(): 우선순위 큐에서 가장 우선순위가 높은 요소, cf. poll()과 달리 제거하지 않음 
            int minIndex = pq.peek().index;
            pq.poll();  // dist, index를 뽑아낸 요소 제거

            // 우선순위 큐를 이용하면 같은 정점의 원소가 여러번 들어가는 문제가 발생할 수 있다
            // minDist가 최신 dist[minIndex]값과 다를 때: continue 해준다 
            if (minDist != dist[minIndex]) {  // 값이 다를 경우 대기열에 있는 정점의 거리가 최신상태가 아님
                continue;
            }

            // minIndex에 해당하는 정점과 연결된 간선들을 확인, 시작점으로부터 최단거리 값 갱신
            for (int j=0; j<graph[minIndex].size(); j++) {
                int targetIndex = graph[minIndex].get(j).index;
                int targetDist = graph[minIndex].get(j).dist;

                // 현재 위치에서 연결된 간선으로 가는 것이 더 작을 때
                int newDist = dist[minIndex] + targetDist;
                if (dist[targetIndex] > newDist) {
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex)); 
                }
            }
        }

        // 시작점(k번)으로부터 각 지점까지의 최단거리 값 출력
        for (int i=1; i<=n; i++) {
            if (dist[i] == (int)1e9) {
                System.out.println(-1);
            } else {
                System.out.println(dist[i]);
            }
        }

        
    }
}