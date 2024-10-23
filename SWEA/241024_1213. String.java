import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t=0; t<10; t++) {
            int T = Integer.parseInt(br.readLine());
            String str = br.readLine();
            String sentence = br.readLine();
            char[] arr = sentence.toCharArray();

            int ans = 0;
            int len = str.length();
            for (int i=0; i<arr.length-len+1; i++) {
              if (sentence.substring(i, i+len).equals(str)) {
                ans++;  
              }
            }
            System.out.println("#" + T + " " + ans);
        }
    }

}
