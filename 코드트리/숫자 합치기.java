import java.util.Scanner;
import java.util.PriorityQueue;  // *PriorityQueue: 매번 제일 작은 수만 이용하기 때문

public class Main {
    public static final int MAX_N = 100000;
    
    public static int n, ans;
    public static int[] a = new int[MAX_N];
    public static PriorityQueue<Integer> pq =  new PriorityQueue<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        // 값 입력
        for (int i=0; i<n; i++){
            a[i] = sc.nextInt();  
        }

        // 우선순위 큐에 원소 넣기
        for (int i=0; i<n; i++) {
            pq.add(a[i]);
        }

        while (pq.size() >= 2) {  // *while 이용하면 편리함
            int x1 = pq.poll();
            int x2 = pq.poll();
            
            ans += (x1+x2);
            pq.add(x1+x2);
        }

        System.out.print(ans);
    }
}