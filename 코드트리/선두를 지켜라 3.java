import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 1000000;
    
    public static int[] posA = new int[MAX_NUM+1];
    public static int[] posB = new int[MAX_NUM+1];
    
    public static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        int timeA = 1;
        while (n-->0) {
            int v = sc.nextInt();
            int t = sc.nextInt();

            for (int i=0; i<t; i++) {
                posA[timeA] = posA[timeA-1] + v;
                timeA++;
            }
        }

        int timeB = 1;
        while (m-->0) {
            int v = sc.nextInt();
            int t = sc.nextInt();

            for (int i=0; i<t; i++) {
                posB[timeB] = posB[timeB-1] + v;
                timeB ++;
            }
        }

        int leader = 0, ans = 0;
        for (int i=1; i<=timeA; i++) {
            if (posA[i] < posB[i]) {
                if (leader == 1 || leader == 3) {
                    ans++;
                }
                leader = 2;
            }
            else if (posA[i] > posB[i]) {
                if (leader == 2 || leader == 3) {
                    ans++;
                }
                leader = 1;
            }            
            else {
                if (leader == 1 || leader == 2) {
                    ans ++;
                }
                leader = 3; 
            }
        }
        System.out.println(ans); 
    }
}