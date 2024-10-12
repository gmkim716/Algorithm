import java.util.Scanner;

class Pair{
    int s, e;

    public Pair(int s, int e) {
        this.s = s;
        this.e = e;
    }
}

public class Main {
    public static final int MAX_N = 100;
    public static final int OFFSET = 1000000;
    public static Pair[] arr = new Pair[MAX_N+1];
    public static boolean[] crossedArr = new boolean[MAX_N+1];
    public static int n;

    public static boolean isCrossed(Pair p1, Pair p2) {
        return p1.s <= p2.s && p2.e <= p1.e;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int x1 = sc.nextInt()+OFFSET;
            int x2 = sc.nextInt()+OFFSET;
            arr[i] = new Pair(x1, x2);
        }

        int ans = n;
        for (int i=0; i<n; i++) {
            boolean crossed = false;
            for (int j=0; j<n; j++) {
                if (i == j) continue;
                if (isCrossed(arr[i], arr[j])) {
                    crossedArr[i] = true;
                    crossedArr[j] = true;
                }
            }
        }
        for (int i=0; i<MAX_N; i++) {
            if (crossedArr[i]) {
                ans --;
            }
        }
        System.out.println(ans);
    }
}