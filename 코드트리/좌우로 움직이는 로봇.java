import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 1000000;
    public static int n, m;
    public static int[] posA = new int[MAX_NUM+1];  // index: time
    public static int[] posB = new int[MAX_NUM+1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
    
        int timeA = 1;
        for (int i=0; i<n; i++) {
            int t = sc.nextInt();
            char c = sc.next().charAt(0);

            while (t-->0) {
                if (c == 'R') {
                    posA[timeA] = posA[timeA-1] + 1;
                } else {
                    posA[timeA] = posA[timeA-1] - 1;
                }
                timeA ++;
            }
        }

        int timeB = 1;
        for (int i=0; i<m; i++) {
            int t = sc.nextInt();
            char c = sc.next().charAt(0);

            while (t-->0) {
                if (c == 'R') {
                    posB[timeB] = posB[timeB-1] + 1;
                } else {
                    posB[timeB] = posB[timeB-1] - 1;
                }
                timeB ++;
            }
        }

        if (timeA < timeB) {
            for (int i=timeA; i<timeB; i++) {
                posA[i] = posA[i-1];
            }
        }
        else if (timeA > timeB) {
            for (int i=timeB; i<timeA; i++) {
                posB[i] = posB[i-1];
            }
        }

        int ans = 0;
        int timeMax = 0;
        if (timeA < timeB) {
            timeMax = timeB;
        } else {
            timeMax = timeA;
        }

        for (int i=1; i<timeMax; i++) {
            if (posA[i-1] != posB[i-1] && posA[i] == posB[i]) {
                ans ++;
            }
        }
        System.out.println(ans);
    }
}