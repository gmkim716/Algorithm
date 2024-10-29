import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static int N, ans;
    static int[] t_lst;
    static int[] p_lst;
    static int[] ans_lst;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t_lst = new int[N+1];
        p_lst = new int[N+1];
        ans_lst = new int[N+2];  // N+1까지 계산할 수 있도록 배열 크기를 증가시킨다

        for (int i=1; i<=N; i++) {
            String[] str = br.readLine().split(" ");
            int T = Integer.parseInt(str[0]);
            int P = Integer.parseInt(str[1]);

            t_lst[i] = T;
            p_lst[i] = P;
        }

        for (int i=N; i>=1; i--) {
            if (canDo(i)) {
                ans_lst[i] = Math.max(
                    // 현재 시점의 상담을 진행하면 얻을 수 있는 이익 + 오늘의 상담이 끝난 다음부터 얻을 수 있는 최대 이익
                    p_lst[i] + ans_lst[i+t_lst[i]],
                    ans_lst[i + 1]  // 다음 시점에 얻을 수 있는 최대 이익
                );
            } else {
                ans_lst[i] = ans_lst[i + 1];
            }
        }

        System.out.println(ans_lst[1]);
    }

    static boolean canDo(int idx) {
        return idx + t_lst[idx] <= N+1;

    }
}
