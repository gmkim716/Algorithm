import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();
        int N = sc.nextInt();

        for (int i=1; i<=N; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            queue.poll();  // poll: 큐 맨 앞에 있는 값 반환 후 삭제
            queue.add(queue.poll());
        }
        System.out.println(queue.poll());
    }
}

