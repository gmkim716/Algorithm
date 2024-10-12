import java.util.Scanner;

public class Main {
    public static final int MAX_T = 1000000; 
    public static int n, m; 

    public static int[] locA = new int[MAX_T+1];  // *인덱스가 시간, 값이 위치를 나타내는 배열
    public static int[] locB = new int[MAX_T+1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        m = sc.nextInt();

        int timeA = 1;
        while (n-->0) {
            char d = sc.next().charAt(0);
            int t = sc.nextInt();

            while(t-- > 0) {
                if (d == 'R') {
                    locA[timeA] = locA[timeA-1] + 1;
                } else {
                    locA[timeA] = locA[timeA-1] - 1; 
                }
                timeA ++;
            }
        }

        int timeB = 1; 
        while (m-->0) {
            char d = sc.next().charAt(0);
            int t = sc.nextInt();

            while(t-- > 0) {
                if (d == 'R') {
                    locB[timeB] = locB[timeB-1] + 1;
                } else {
                    locB[timeB] = locB[timeB-1] - 1; 
                }
                timeB ++;
            }
        }

        int ans = -1;
        for (int i=1; i<timeA; i++) {  // *최종적으로 변화한 값 timeA 만큼의 범위 확인
            if (locA[i] == locB[i]) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}