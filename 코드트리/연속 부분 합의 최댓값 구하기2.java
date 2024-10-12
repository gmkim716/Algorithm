import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100000; 
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static int n, ans;
    public static int[] arr = new int[MAX_N];
    public static int[] dp = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); 
        
        for (int i=0;i<n; i++) {
            arr[i] = sc.nextInt();
        }

        ans = INT_MIN;
        for (int i=0; i<n; i++) {
            dp[i] = INT_MIN;
        }

        dp[0] = arr[0];
        for (int i=1; i<n; i++) {
            int preCal = dp[i-1] + arr[i];
            if (preCal > 0) {
                dp[i] = preCal;
            } else {
                dp[i] = 0;
            }
        }

        for (int i=0; i<n; i++) {
            ans = Math.max(ans, dp[i]); 
        }

        if (ans == 0) {
            System.out.println(arr[n-1]);  
        } else {
            System.out.println(ans);
        }
    }
}