import java.util.Scanner;

public class Main {

    static int N, K, L, ans;
    static int[][] map;
    static char[] turnLst = new char[10001];

    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N+1][N+1];

        K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            int appleR = sc.nextInt();
            int appleC = sc.nextInt();
            map[appleR][appleC] = 7;  // 사과 위치 표시
        }

        L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            turnLst[x] = c;
        }

        snakeGo();
        System.out.println(ans);
    }

    static void snakeGo() {
        int hr = 1, hc = 1, tr = 1, tc = 1, dir = 0, time = 0;
        map[hr][hc] = dir + 1;  // 뱀 머리 방향 설정 (1~4로 표시)

        while (true) {
            time++;
            hr += dr[dir];
            hc += dc[dir];

            // 벽에 부딪히거나 자기 몸에 부딪히면 종료
            if (!inRange(hr, hc) || eatBody(hr, hc)) {
                ans = time;
                break;
            }

            // 방향 전환 체크
            if (isTurn(time)) {
                if (turnLst[time] == 'L') {
                    dir = (dir + 3) % 4;  // 왼쪽 회전
                } else if (turnLst[time] == 'D') {
                    dir = (dir + 1) % 4;  // 오른쪽 회전
                }
            }

            // 사과를 먹었을 때
            if (eatApple(hr, hc)) {
                map[hr][hc] = dir + 1;  // 방향 저장 (1~4로 표시)
            }
            // 일반적인 이동
            else {
                map[hr][hc] = dir + 1;  // 방향 저장 (1~4로 표시)
                int tailDir = map[tr][tc] - 1;  // 꼬리 방향 계산 (1~4 -> 0~3으로 조정)
                map[tr][tc] = 0;  // 꼬리 위치 초기화

                tr += dr[tailDir];
                tc += dc[tailDir];
            }

//            printMap();  // 디버깅
//            System.out.println();
        }
    }

    static boolean isTurn(int time) {
        return turnLst[time] != '\0';
    }

    static boolean eatApple(int r, int c) {
        return map[r][c] == 7;
    }

    static boolean eatBody(int r, int c) {
        return map[r][c] >= 1 && map[r][c] <= 4;  // 방향 값이 설정된 위치는 몸통으로 간주
    }

    static boolean inRange(int r, int c) {
        return 1 <= r && r <= N && 1 <= c && c <= N;
    }

    static void printMap() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
