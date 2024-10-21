import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private static int[][] arr = new int[100][100];

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < 100 && y >= 0 && y < 100;
    }

    public static int findStartIdx(int x, int y) {
        while (y > 0) {

            // 좌우로 이동 가능한 경우, 이동
            if (inRange(x-1, y) && arr[y][x-1] == 1) {
                while (inRange(x-1, y) && arr[y][x-1] == 1) {
                    x--;
                }
            }
            else if (inRange(x+1, y) && arr[y][x+1] == 1) {
                while (inRange(x+1, y) && arr[y][x+1] == 1) {
                    x++;
                }
            }

            // 이동이 불가능한 경우 위로 이동
            y --;
        }
        return x;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t=0; t<10; t++) {
            int T = Integer.parseInt(br.readLine());

            for (int i=0; i<100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 도착 지점 확인
            int endIdx = -1;
            for (int i=0; i<100; i++) {
                if (arr[99][i] == 2) {
                    endIdx = i;
                    break;
                }
            }

            // 도착지점으로 부터 출발점을 찾아 거슬러 올라감
            int startIdx = findStartIdx(endIdx, 99);

            System.out.println("#" + T + " " + startIdx);
        }

        br.close();
    }


}
