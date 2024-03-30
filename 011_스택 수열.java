// 1874 스택 수열

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i=0; i<N; i++) {
            A[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();  // 스택 정의
        StringBuffer bf = new StringBuffer();  // 정답으로 출력할 수 있는 값 저장

        int num = 1;  // 오름차순 수
        boolean result = true;  // 불가능 여부 확인
        for (int i=0; i<A.length; i++) {
            if (A[i] >= num) {  // 값이 같아질 때까지 push
                while (A[i] >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else {
                int n = stack.pop();  // 스택의 마지막 값 뽑아서 확인
                if (n > A[i]) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
    if (result) {  // result가 NO가 아닌 경우 정답 출력
        System.out.println(bf.toString());  // bf로 저장된 값을 String으로 변환해 출력
    }
    }
}
