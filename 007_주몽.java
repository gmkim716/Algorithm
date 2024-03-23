import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);  // N의 최대범위가 15,000이므로 O(nlogn) 가능 -> 정렬 사용

        int cnt = 0;
        int left = 0;  // 왼쪽 포인터 인덱스
        int right = N-1;  // 오른쪽 포인터 인덱스(우측 끝에서 시작)

        while (left < right) {
            if (A[left] + A[right] < M) {
                left ++;
            } else if (A[left] + A[right] > M) {
                right --;
            } else {
                cnt ++;
                left ++;
                right --;
            }
        }
        System.out.println(cnt);
        bf.close();  // 생략 가능
    }
}