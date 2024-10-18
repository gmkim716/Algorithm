import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            int num = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] scores = new int[101];
            int[] cnt = new int[101];
            for (int i=0; i<1000; i++) {
                scores[Integer.parseInt(st.nextToken())] ++;
            }

            int max = 0;
            int idx = 0;
            for (int i=0; i<100; i++) {
                if (max <= scores[i]) {
                    max = scores[i];
                    idx = i;
                }
            }

            System.out.println("#" + (t+1) + " " + idx);
        }
        br.close();
    }
}
