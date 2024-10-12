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
    public static Pair[] arr = new Pair[MAX_N];

    public static int dist(int i, int j) {
        return (arr[i].x-arr[j].x)*(arr[i].x-arr[j].x) + (arr[i].y-arr[j].y)*(arr[i].y-arr[j].y);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[i] = new Pair(x, y);
        }

        int minDist = INT_MAX; 

        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                minDist = Math.min(minDist, dist(i, j));  // 처음부터 메소드 작성했다면 수월했을 것 
            }
        }
        System.out.println(minDist);
    }
}