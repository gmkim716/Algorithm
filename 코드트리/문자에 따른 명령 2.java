import java.util.Scanner;

public class Main {
    // 북동남서: *회전을 위해 시계 방향 순서대로 정의
    public static final int[] dx = new int[]{0, 1, 0, -1};
    public static final int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        
        int x = 0, y = 0, dir=0;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'L') {
                dir = (dir - 1+ 4)%4;  // 왼쪽 회전: *(dir-1+4)%4               
            } else if (c == 'R') {
                dir = (dir+1)%4;  // 오른쪽 회전: (dir+1)%4
            } else {
                x += dx[dir];
                y += dy[dir];
            }
        }
        System.out.println(x+" "+y);
    }
}