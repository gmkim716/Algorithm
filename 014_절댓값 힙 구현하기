import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 우선순위 큐의 정렬 기준을 직접 정의: 절댓값 기준 설정
        PriorityQueue<Integer> myQueue = new PriorityQueue<>((o1, o2) -> {
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);

            // 절댓값이 같으면 음수를 우선으로 정렬하기
            if (first_abs == second_abs) {
                return o1 > o2 ? 1 : -1;
            } else {
                return first_abs - second_abs;
            }
        });

        for (int i=0; i<N; i++) {
            int request = Integer.parseInt(br.readLine());
            if (request == 0) {
                if (myQueue.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(myQueue.poll());
                }
            } else {
                myQueue.add(request);
            }
        }
    }
}

