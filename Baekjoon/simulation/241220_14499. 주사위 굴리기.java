import java.util.Scanner;

public class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[6]; // 주사위 각 면: [위, 아래, 동, 서, 북, 남]
    static int[] dx = {0, 0, -1, 1}; // 동, 서, 북, 남
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 지도 세로 크기
        M = sc.nextInt(); // 지도 가로 크기
        x = sc.nextInt(); // 주사위 초기 x 좌표
        y = sc.nextInt(); // 주사위 초기 y 좌표
        K = sc.nextInt(); // 명령 개수

        map = new int[N][M];

        // 지도 값 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 명령 처리
        for (int i = 0; i < K; i++) {
            int command = sc.nextInt();
            move(command - 1); // 명령은 1부터 시작하므로 -1
        }
    }

    static void move(int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 경계 검사
        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return;

        // 주사위 위치 업데이트
        x = nx;
        y = ny;

        // 주사위 굴리기
        rollDice(dir);

        // 지도와 주사위 값 동기화
        if (map[x][y] == 0) {
            map[x][y] = dice[1]; // 지도에 주사위 바닥면 값 복사
        } else {
            dice[1] = map[x][y]; // 주사위 바닥면에 지도 값 복사
            map[x][y] = 0;
        }

        // 주사위 윗면 출력
        System.out.println(dice[0]);
    }

    static void rollDice(int dir) {
        int[] temp = dice.clone();  // clone: 주사위 값 깊은 복사
        switch (dir) {
            case 0: // 동쪽:
                dice[0] = temp[3];
                dice[1] = temp[2];
                dice[2] = temp[0];
                dice[3] = temp[1];
                break;
            case 1: // 서쪽
                dice[0] = temp[2];
                dice[1] = temp[3];
                dice[2] = temp[1];
                dice[3] = temp[0];
                break;
            case 2: // 북쪽
                dice[0] = temp[5];
                dice[1] = temp[4];
                dice[4] = temp[0];
                dice[5] = temp[1];
                break;
            case 3: // 남쪽
                dice[0] = temp[4];
                dice[1] = temp[5];
                dice[4] = temp[1];
                dice[5] = temp[0];
                break;
        }
    }
}
