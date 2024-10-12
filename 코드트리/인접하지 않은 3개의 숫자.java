import java.util.*;

// LR Technique 사용: i번째의 최대합: 왼쪽에서 얻을 수 있는 최댓값 + i의 값 + 오른쪽에서 얻을 수 있는 최댓값
// 인접하지 않아야 하므로 왼쪽의 i-2, 오른쪽 배열의 i+2를 가지고 값 생성
public class Main {

    public static final int MAX_N = 100000;
    public static int n, ans;

    public static int[] A = new int[MAX_N];
    public static int[] L = new int[MAX_N];
    public static int[] R = new int[MAX_N];
    public static int[] S = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            A[i] = sc.nextInt();
        }

        // L, R 배열의 기본 값 입력
        L[0] = A[0];
        L[1] = A[1];
        R[n-1] = A[n-1];
        R[n-2] = A[n-2];

        // L배열 생성
        int leftMax = Math.max(L[0], L[1]);
        for (int i=2; i<n; i++) {
            leftMax = Math.max(leftMax, A[i]);
            L[i] = leftMax;
        }

        // R배열 생성
        int rightMax = Math.max(R[n-1], R[n-2]);
        for (int i=n-2; i>=0; i--) {
            rightMax = Math.max(rightMax, A[i]);
            R[i] = rightMax;
        }

        // 최댓값 확인 & 정답 갱신
        for (int i=2; i<n-2; i++) {
            S[i] = L[i-2] + A[i] + R[i+2];
            ans = Math.max(ans, S[i]);
        }
        
        System.out.println(ans); 
    }
}