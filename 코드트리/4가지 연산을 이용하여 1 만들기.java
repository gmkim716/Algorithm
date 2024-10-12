import java.util.Scanner; 
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_N = 1000000;
    public static final int OPERATOR_NUM = 4; 

    public static final int SUBTRACT = 0;
    public static final int ADD = 1;
    public static final int DIV2 = 2;
    public static final int DIV3 = 3;

    public static int n;

    public static Queue<Integer> q = new LinkedList<>();
    public static boolean[] visited = new boolean[MAX_N*2];
    
    // idx에 도달하기 위한 최단 거리 기록
    public static int[] step = new int[MAX_N*2];

    public static int ans; 

    public static boolean possible(int num, int op) {
        if (op == SUBTRACT || op == ADD) {
            return true;
        } else if (op == DIV2) {
            return num%2 == 0;
        } else {
            return num%3 == 0;
        }
    }

    public static int calculate(int num, int op) {
        if (op == SUBTRACT) {
            return num-1;
        } else if(op == ADD) {
            return num+1;
        } else if(op == DIV2) {
            return num/2;
        } else {
            return num/3;
        }
    }
    /*
        이 문제를 BFS로 풀때 주의해야 할 점은 정점을 1에서 2n-1번까지 사용하여 최단거리를 구해야 한다는 것입니다. 
        그 이유는 n 보다 큰 값을 만든 다음 2나 3으로 숫자를 나눠 1을 더 빨리 만들 수도 있기 때문입니다. 
        2n-1까지만 정점을 고려하면 되는 이유는 입력으로 주어진 숫자 n에 1을 빼주는 연산을 n-1번 반복하면 항상 1이기 되기 때문에 답은 최대 n-1이 될 수 있으므로 
        숫자 n에 n-1번 연산을 했을 때 만들 수 있는 가장 큰 숫자인 2n-1까지만 정점을 만들어 최단거리를 구하면 
        올바른 답을 구할 수 있다고 보장할 수 있기 때문입니다.
    */
    public static boolean inRange(int num) {
        return 1<=num && num<=2*n-1;
    }

    public static boolean canGo(int num) {
        return inRange(num) && !visited[num];
    }

    public static void push(int num, int newStep) {
        q.add(num);
        visited[num] = true;  // 계산했음을 표시
        step[num] = newStep;
    }

    public static void findMin() {
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int i=0; i<OPERATOR_NUM; i++) {
                // 계산을 진행해도 되는지 확인
                if (!possible(curr, i)) {
                    continue; 
                }
                // 계산한 값 확인
                int newNum = calculate(curr, i);
                if (canGo(newNum)) {
                    push(newNum, step[curr]+1); 
                }
            }
        }
        ans = step[1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        // BFS를 통해 최소 연산 횟수 구하기 
        push(n, 0);  // 처음 숫자 n을 q에 입력
        findMin(); 

        System.out.println(ans);
    }
}