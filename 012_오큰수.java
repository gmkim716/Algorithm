import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int[] A = new int[N];
        int[] ans = new int[N];
        String[] str = bf.readLine().split(" ");
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> myStack = new Stack<>();
        myStack.push(0);  // 처음에는 항상 스택이 비어 있으므로 최초 값을 push해 초기화
        for (int i=0; i<N; i++) {
            // 스택이 비어있지 않고, 현재 수열이 스택 top 인덱스가 가리키는 수열보다 큰 경우
            while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
                ans[myStack.pop()] = A[i];  // 정답 배열에 오큰수를 현재 수열로 저장
            }
            myStack.push(i);  // 신규 데이터 push
        }

        // 반복문을 다 돌고 나왔는데 스택이 비어있지 않다면, 빌 때까지 진행
        while (!myStack.empty()) {
            ans[myStack.pop()] = -1;  // 스택에 쌓인 index에 -1을 넣기
        }

        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i=0; i<N; i ++) {
            bw.write(ans[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
