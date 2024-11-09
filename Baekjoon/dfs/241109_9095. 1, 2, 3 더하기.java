import java.util.Scanner;

public class Main {

    static int T, cnt;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int t=0; t<T; t++) {
            cnt = 0;
            dfs(sc.nextInt(), 0);
            System.out.println(cnt);
        }
    }

    static void dfs(int target, int sum) {
        if (sum == target) {
            cnt++;
            return;
        }

        for (int i=1; i<=3; i++) {
            if (sum+i <= 11) {
                dfs(target, sum+i);
            }
        }
    }
}