import java.util.Scanner;

class Work {
    int s, e;

    public Work(int s, int e) {
        this.s = s;
        this.e = e;
    }
}

public class Main {
    public static final int MAX_N = 100;
    public static final int MAX_T = 1000;
    public static int n;
    public static Work[] works = new Work[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            works[i] = new Work(a, b); 
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            boolean[] time = new boolean[MAX_T+1];
            for (int j=0; j<n; j++) {
                if (i == j) continue;
    
                int s = works[j].s;
                int e = works[j].e;
                for (int k=s; k<e; k++) {
                    time[k] = true;
                }
            }

            int cnt = 0;
            for (int k=0; k<MAX_T; k++) {
                if (time[k]) {
                    cnt ++;
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }
}