import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            String[] input = br.readLine().split(" ");

            int max = 0;
            for (int i=0; i<10; i++) {
                int num = Integer.parseInt(input[i]);
                if (max < num) {
                    max = num;
                }
            }
            System.out.println("#" + (t+1) + " " + max);
        }

        br.close();
    }
}