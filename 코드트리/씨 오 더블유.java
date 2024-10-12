import java.util.*;

public class Main {
    public static int MAX_N = 100000;

    public static int n;
    public static long ans;

    public static int[] L = new int[MAX_N];
    public static int[] R = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        String str = sc.next();
        
        int c = 0, w = 0;

        for (int i=0; i<n; i++) {
            if (str.charAt(i) == 'C') {
                c ++;
            }
            L[i] = c;
        }

        for (int i=n-1; i>=0; i--) {
            if (str.charAt(i) == 'W') {
                w ++;
            }
            R[i] = w;
        }


        for (int i=1; i<n-1; i++) {
            if (str.charAt(i) == 'O') {
                ans += L[i-1] * R[i+1];
            }
        }

        System.out.println(ans);
    }
}