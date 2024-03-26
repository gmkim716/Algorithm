import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] A = new int[N];

        st = new StringTokenizer(bf.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);  // 정렬 O(nlogn)

        int ans = 0;
        for (int i=0; i<N; i++) {
            int target = A[i];  // 찾으려는 수
            int left = 0;  // 왼쪽 포인터
            int right = N-1;  // 오른쪽 포인터

            while (left < right) {
                if (A[left] + A[right] == target) {  // target 값을 찾은 경우
                    if (left != i && right != i) {  // left & right가 i이면 두 값의 합이 아니므로 제외
                        ans ++;
                        break;
                    } else if (left == i) {
                        left ++;
                    } else if (right == i) {
                        right --;
                    }
                } else if (A[left] + A[right] < target) {  // target 값보다 작은 경우: 왼쪽 포인터 이동
                    left ++;
                } else {  // target 값보다 큰 경우: 우측 포인터 이동
                    right --;
                }
            }
        }
        System.out.println(ans);
    }
}