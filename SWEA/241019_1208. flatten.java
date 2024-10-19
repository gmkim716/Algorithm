import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    private static int MAX_N = 100;
    private static int[] boxes = new int[MAX_N];
    private static int dumpCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        for (int t=0; t<T; t++) {
            dumpCnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<MAX_N; i++) {
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            while (dumpCnt > 0) {
                dump();
                dumpCnt --;
            }

            int maxNum = 0;
            int minNum = 101;
            for (int i=0; i<MAX_N; i++) {
                if (maxNum < boxes[i]) {
                    maxNum = boxes[i];
                }
                if (minNum > boxes[i]) {
                    minNum = boxes[i];
                }
            }

            int ans = maxNum - minNum;
            System.out.println("#" + (t+1) + " " + ans);

        }
        br.close();
    }

    public static void dump() {
        int max = 0;
        int maxIdx = 0;
        int min = 101;
        int minIdx = 101;
        for (int i=0; i<MAX_N; i++) {
            if (max < boxes[i]) {
                max = boxes[i];
                maxIdx = i;
            }

            if (min > boxes[i]) {
                min = boxes[i];
                minIdx = i;
            }
        }

        boxes[maxIdx] --;
        boxes[minIdx] ++;
    }
}
