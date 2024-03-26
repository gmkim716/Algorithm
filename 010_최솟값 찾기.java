import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));  // 출력을 버퍼에 넣고 한 번에 출력하기 위해 BufferedWriter를 사용한다
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> mydeque = new LinkedList<>();  // 덱 구조 사용: 슬라이딩 윈도우를 이용해, 오름차순 정렬을 적용 가능 (문제 핵심!)
        for (int i=0; i<N; i++) {
            int now = Integer.parseInt(st.nextToken());

            while (!mydeque.isEmpty() && mydeque.getLast().value > now) {
                mydeque.removeLast();  // 덱 안에 있는 숫자가 now에서 확인하는 수보다 클 경우 삭제 진행
            }
            mydeque.addLast(new Node(now, i));

            // 범위에서 벗어난 값을 덱에서 제거
            if (mydeque.getFirst().index <= i - L) {
                mydeque.removeFirst();
            }
            bw.write(mydeque.getFirst().value + " ");  // bw를 이용한 출력
        }
        bw.flush();  // BuffredWriter의 버퍼에 저장된 모든 출력 데이터를 스트림으로 전송하고, 버퍼를 비우는 역할
        bw.close();  // 종료
    }

    static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}

