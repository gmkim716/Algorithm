import java.util.Scanner;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int MAX_N = 100;
    public static int n;
    public static Pair[] arr = new Pair[MAX_N];

    public static int area(Pair p1, Pair p2, Pair p3) {
        return Math.abs((p1.x*p2.y + p2.x*p3.y + p3.x*p1.y) - (p2.x*p1.y + p3.x*p2.y + p1.x*p3.y));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[i] = new Pair(x, y);        
        }

        int maxArea = 0;
        for (int i=0; i<n-2 ; i++) {
            for (int j=i+1; j<n-1; j++) {
                for (int k=j+1; k<n; k++) {
                    
                    // *x축, y축에 평행한 좌표 1개와 나머지 두개의 좌표를 찾기 
                    if ((arr[i].x == arr[j].x || arr[j].x == arr[k].x || arr[k].x == arr[i].x) &&
                        (arr[i].y == arr[j].y || arr[j].y == arr[k].y || arr[k].y == arr[i].y)) {
                            maxArea = Math.max(maxArea, area(arr[i], arr[j], arr[k]));
                    }
                }
            }
        }
        System.out.println(maxArea);
    }
}