import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static int N, ans;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i=0; i<N; i++) {
            String inputStr = br.readLine();  // 띄어쓰기로 구분된 것이 아니라면 String 타입으로 입력을 받아 chatAt()으로 접근한다
            for (int j=0; j<N; j++) {
                map[i][j] = inputStr.charAt(j);
            }
        }

        findAns();

        System.out.println(ans);
    }

    // 그리디 사용: 바꾸었을 때, 바꾸지 않았을 때의 결과 비교
    static void findAns() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (j+1 < N) {
                    swap(i, j, i, j+1);
                    checkMaxLength();
                    swap(i, j, i, j+1);  // 복구한다
                }

                if (i+1 < N) {
                    swap(i, j, i+1, j);
                    checkMaxLength();
                    swap(i, j, i+1, j);  // 복구한다
                }
            }
        }
    }

    static void swap(int x1, int y1, int x2, int y2) {
        char temp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = temp;
    }

    static void checkMaxLength() {
        // 가로 방향에서 찾기
        for (int i=0; i<N; i++) {
            int rowMax = 1;
            for (int j=0; j<N-1; j++) {
                if (map[i][j] == map[i][j+1]) {
                    rowMax++;
                } else {
                    ans = Math.max(ans, rowMax);
                    rowMax = 1;
                }
            }
            ans = Math.max(ans, rowMax);
        }

        // 세로 방향에서 찾기
        for (int i=0; i<N; i++) {
            int colMax = 1;
            for (int j=0; j<N-1; j++) {
                if (map[j][i] == map[j+1][i]) {
                    colMax++;
                } else {
                    ans = Math.max(ans, colMax);
                    colMax = 1;
                }
            }
            ans = Math.max(ans, colMax);
        }
    }
}
