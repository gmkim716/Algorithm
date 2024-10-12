import java.util.Scanner;

public class Main {
    public static int n;
    // 동서남북
    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int x = 0, y = 0;
        while (n-->0) {
            char c = sc.next().charAt(0);
            int n = sc.nextInt();

            int dir = 0;
            if (c=='E') {
                dir = 0;
            } else if (c=='W') {
                dir = 1;
            } else if (c=='S') {
                dir = 2;
            } else if (c=='N') {
                dir = 3;
            }
            x += dx[dir] * n;
            y += dy[dir] * n;
        }
        System.out.println(x+" "+y);
    }
}