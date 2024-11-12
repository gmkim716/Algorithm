import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] isStartTeam;  // 팀 구분을 위한 boolean 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        isStartTeam = new boolean[N];  // 초기화

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int idx, int count) {
        if (count == N / 2) {
            // 두 팀의 차이를 계산
            calculateTeamDifference();
            return;
        }

        // 백트래킹
        for (int i = idx; i < N; i++) {
            isStartTeam[i] = true;
            dfs(i + 1, count + 1);
            isStartTeam[i] = false;
        }
    }

    static void calculateTeamDifference() {
        int teamStartSum = 0, teamLinkSum = 0;

        // 각 팀의 능력치 합계 계산
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isStartTeam[i] && isStartTeam[j]) {
                    teamStartSum += map[i][j] + map[j][i];
                } else if (!isStartTeam[i] && !isStartTeam[j]) {
                    teamLinkSum += map[i][j] + map[j][i];
                }
            }
        }

        // 최소 차이 갱신
        ans = Math.min(ans, Math.abs(teamStartSum - teamLinkSum));
    }
}
