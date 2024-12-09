import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] board;
    static int[] airCleaner; // 공기청정기 위치 (두 줄의 행 위치를 저장)
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        airCleaner = new int[2];
        int airCleanerIndex = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    airCleaner[airCleanerIndex++] = i; // 공기청정기 위치 저장
                }
            }
        }

        for (int t = 0; t < T; t++) {
            spreadDust(); // 1. 미세먼지 확산
            operateAirCleaner(); // 2. 공기청정기 작동
        }

        System.out.println(getTotalDustAmount());
    }

    // 1. 미세먼지 확산
    static void spreadDust() {
        int[][] temp = new int[R][C];

        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (board[x][y] > 0) {
                    int spreadAmount = board[x][y] / 5; // 확산되는 먼지의 양
                    int spreadCount = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && board[nx][ny] != -1) {
                            temp[nx][ny] += spreadAmount;
                            spreadCount++;
                        }
                    }

                    temp[x][y] += board[x][y] - (spreadAmount * spreadCount); // 남은 먼지
                }
            }
        }

        // 기존 보드에 새로운 먼지 상태 덮어쓰기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] = temp[i][j];
            }
        }

        // 공기청정기 위치 복구
        board[airCleaner[0]][0] = -1;
        board[airCleaner[1]][0] = -1;
    }

    // 2. 공기청정기 작동
    static void operateAirCleaner() {
        // 1. 위쪽 공기청정기 작동 (반시계 방향)
        int top = airCleaner[0];

        // 아래로 이동
        for (int i = top - 1; i > 0; i--) {
            board[i][0] = board[i - 1][0];
        }
        // 왼쪽으로 이동
        for (int i = 0; i < C - 1; i++) {
            board[0][i] = board[0][i + 1];
        }
        // 위로 이동
        for (int i = 0; i < top; i++) {
            board[i][C - 1] = board[i + 1][C - 1];
        }
        // 오른쪽으로 이동
        for (int i = C - 1; i > 1; i--) {
            board[top][i] = board[top][i - 1];
        }
        board[top][1] = 0; // 공기청정기 옆은 항상 0

        // 2. 아래쪽 공기청정기 작동 (시계 방향)
        int bottom = airCleaner[1];

        // 위로 이동
        for (int i = bottom + 1; i < R - 1; i++) {
            board[i][0] = board[i + 1][0];
        }
        // 왼쪽으로 이동
        for (int i = 0; i < C - 1; i++) {
            board[R - 1][i] = board[R - 1][i + 1];
        }
        // 아래로 이동
        for (int i = R - 1; i > bottom; i--) {
            board[i][C - 1] = board[i - 1][C - 1];
        }
        // 오른쪽으로 이동
        for (int i = C - 1; i > 1; i--) {
            board[bottom][i] = board[bottom][i - 1];
        }
        board[bottom][1] = 0; // 공기청정기 옆은 항상 0
    }

    // 남은 미세먼지의 양 계산
    static int getTotalDustAmount() {
        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    total += board[i][j];
                }
            }
        }
        return total;
    }
}
