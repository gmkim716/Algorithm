import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private static int[][] arr = new int[100][100];

    public static boolean inRange(int x, int y) {
        return 0<=x && x<100 && 0<=y && y<100;
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && arr[y][x] == 1;
    }

    public static int cntRouteLen(int x, int y) {
        int cnt = 0;

        while (y < 99) {
            if (canGo(x-1, y)) {
                while (canGo(x-1, y)) {
                    x -= 1;
                    cnt += 1;
                }
            }
            else if (canGo(x+1, y)) {
                while (canGo(x+1, y)) {
                    x += 1;
                    cnt += 1;
                }
            }

            y += 1;
            cnt += 1;
        }

        return cnt;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t=0; t<10; t++) {
            int T = Integer.parseInt(br.readLine());

            arr = new int[100][100];  // 케이스 t마다 초기화 진행
            for (int i=0; i<100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int minLen = Integer.MAX_VALUE;
            int minIdx = -1;

            for (int i=0; i<100; i++) {
                if (arr[0][i] == 1) {
                    int cnt = cntRouteLen(i, 0);
                    if (minLen > cnt) {
                        minLen = cnt;
                        minIdx = i;
                    }
                }
            }
            System.out.println("#" + T + " " + minIdx);
        }
        br.close();
    }


}
