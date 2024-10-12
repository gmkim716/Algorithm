import java.util.Scanner;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 100;
    public static int n;
    public static Pair[] pairs = new Pair[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            pairs[i] = new Pair(x, y);
        }

        int ans = INT_MAX;
        for (int i=0; i<n; i++) {

            int minX = INT_MAX, minY = INT_MAX;
            int maxX = 0, maxY = 0;

            int square = 0;
            for (int j=0; j<n; j++) {
                if (i!=j) {
                    if (maxX < pairs[j].x) {
                        maxX = pairs[j].x;
                    }
                    if (maxY < pairs[j].y) {
                        maxY = pairs[j].y;
                    }
                    if (minX > pairs[j].x) {
                        minX = pairs[j].x;
                    }
                    if (minY > pairs[j].y) {
                        minY = pairs[j].y;
                    }
                }   
                
            }
            for (int r=minX; r<maxX; r++) {
                for (int c=minY; c<maxY; c++) {
                    square++;
                }
            }
            
            if (ans > square) {
                ans = square;
            }
        }
        System.out.println(ans);
    }
}