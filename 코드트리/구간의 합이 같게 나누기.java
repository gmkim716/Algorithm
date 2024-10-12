import java.util.*;

public class Main {
    public static final int MAX_N = 100000;

    public static int n; 
    
    public static int[] arr = new int[MAX_N];
    public static int[] L = new int[MAX_N];
    public static int[] R = new int[MAX_N];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        // 총 합을 계산
        int totalSum = 0;
        for (int i=0; i<n; i++) {
            totalSum += arr[i];
        }

        // 총 합이 4로 나누어 떨어지지 않으면 답이 0
        if (totalSum%4 != 0) {
            System.out.println(0);
            System.exit(0);  // 그대로 프로그램 종료
        }
        
        // 각 구간 내 합이 되어야 하는 targetSum은 totalSum을 4로 나눈 값
        int targetSum = totalSum/4;

        // L 배열 채우기 진행
        L[0] = 0;
        int sum = arr[0];  // 현재까지의 합
        int cnt = (sum==targetSum) ? 1 : 0;  // 합의 값이 targetSum이 되는 상태를 표시
        
        for (int i=1; i<n; i++) {
            sum += arr[i];

            // 합이 2*targetSum이 되면 2개의 구간을 만들 수 있으므로, 해당 cnt를 기록
            if (sum == 2*targetSum) {
                L[i] = cnt;
            }

            // 합이 targetSum인 경우 cnt 값을 갱신
            if (sum == targetSum) {
                cnt ++;
            }
        }

        // R 배열 채우기 진행
        L[n-1] = 0;
        sum = arr[n-1];
        cnt = (sum==targetSum) ? 1 : 0;
        for (int i=n-2; i>=0; i--) {
            sum += arr[i];

            // 합이 2*targetSum이 되면 2개의 구간을 만들 수 있으므로, 해당 cnt를 기록
            if (sum == 2*targetSum) {
                R[i] = cnt;
            }

            // 합이 targetSum인 경우 cnt 값을 갱신
            if (sum == targetSum) {
                cnt ++; 
            }
        }

        // 각 위치 i에 대해 
        // [0, i] 까지 targetSum인 구간을 2개 생성,
        // [i+1, n] 까지 targetSum인 구간을 2개 생성
        // 이때의 가능한 가지수를 세어준다
        long ans = 0;
        for (int i=1; i<n-1; i++) {
            ans += L[i] * R[i+1];
        }
        System.out.println(ans); 
    }
} {
  
}
