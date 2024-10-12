import java.util.Scanner;

public class Main {
    public static final int OFFSET = 100;
    public static final int MAX = 200;
    public static int n;
    public static int[][] graph = new int[MAX+1][MAX+1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();    
        for (int k=1; k<=n; k++) {
            int x1 = sc.nextInt()+OFFSET;
            int y1 = sc.nextInt()+OFFSET;
            int x2 = sc.nextInt()+OFFSET;
            int y2 = sc.nextInt()+OFFSET;

            for (int i=x1; i<x2; i++) {
                for (int j=y1; j<y2; j++) {
                    graph[i][j] = k;
                }
            }
        }

        int ans = 0;
        for (int i=0; i<=MAX; i++) {
            for (int j=0; j<=MAX; j++) {
                if (graph[i][j]>0 && graph[i][j]%2 == 0) {
                    ans ++;
                }
            }
        }

        System.out.println(ans);
    }

}