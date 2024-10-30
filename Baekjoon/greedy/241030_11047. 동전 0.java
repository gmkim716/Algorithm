import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static List<Integer> arr = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.reverse(arr);

        int coinCnt = 0;
        for (int coin: arr) {
            if (K/coin >0) {
                coinCnt += K/coin;
                K %= coin;
            }
        }

        System.out.println(coinCnt);

        br.close();
    }
}

