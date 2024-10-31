import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// n의 범위가 백만 이하이므로 O(n)으로 풀 수 있다. 완전 탐색 문제
public class Main {

    static int N, M, cnt;
    static boolean[] wrongBtn = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<M; i++) {
                int num = Integer.parseInt(st.nextToken());
                wrongBtn[num] = true;
            }
        }

        int result = Math.abs(N - 100);  // +/-만 사용해서 이동하는 경우

        // 완전 탐색이 가능하므로 백만을 최대 범위로 잡는다
        // 실제로 85만까지를 범위로 잡아도 정답을 통과하는 것을 확인, 그러나 안전한 풀이를 위해 백만을 잡아야 한다
        for (int i=0; i<=1000000; i++) {
            int len = checkValid(i);
            if (len > 0) {
                int cnt = Math.abs(N-i) + len;
                result = Math.min(result, cnt);
            }
        }

        System.out.println(result);
        br.close();
    }

    // 고장난 숫자를 제외한 번호로 조합이 가능한지 확인한다
    static int checkValid(int num) {
        // 반환 타입이 int이므로 true/false 대신 1/0을 사용하는 방법을 고려해 볼 것
        // 0을 누른 경우에 대해 특별한 처리 필요
        if (num == 0) {
            // 0이 고장난 버튼이라면 0 반환: 유효하지 않음
            // 0이 고장나지 않은 버튼이라면 1 반환: 유효함 표시
            return wrongBtn[0] ? 0 : 1;
        }

        int len = 0;
        while (num > 0) {
            if (wrongBtn[num % 10]) {
                return 0;
            }
            len ++;
            num /= 10;
        }
        return len;
    }
}

