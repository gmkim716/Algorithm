package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, ans = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비트마스크를 활용해 모든 조합 탐색
        for (int i = 1; i < (1 << N) - 1; i++) {
            ans = Math.min(ans, calculateDifference(i));
        }

        System.out.println(ans);
    }

    // 특정 비트마스크로 나눠진 팀의 차이 계산
    static int calculateDifference(int bitmask) {
        int teamStartSum = 0, teamLinkSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 팀 시작 (start) 멤버인지 확인
                if (((bitmask & (1 << i)) != 0) && ((bitmask & (1 << j)) != 0)) {
                    teamStartSum += map[i][j] + map[j][i];
                }
                // 팀 링크 (link) 멤버인지 확인
                else if (((bitmask & (1 << i)) == 0) && ((bitmask & (1 << j)) == 0)) {
                    teamLinkSum += map[i][j] + map[j][i];
                }
            }
        }

        return Math.abs(teamStartSum - teamLinkSum);
    }
}
