import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][M]; // 0-based index로 수정

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int layers = Math.min(N, M) / 2; // 테두리의 개수

        for (int layer = 0; layer < layers; layer++) {
            // 1. 테두리 추출
            List<Integer> edge = extractLayer(layer);

            // 2. R % 테두리 길이 만큼 회전
            int edgeLength = edge.size();
            int rotationCount = R % edgeLength;

            if (rotationCount > 0) {
                // 3. 회전 (리스트 슬라이싱 방식)
                List<Integer> rotated = rotateList(edge, rotationCount);

                // 4. 배열에 다시 반영
                fillLayer(layer, rotated);
            }
        }

        printA();
    }

    /**
     * 1. 테두리 추출 메서드
     * @param layer 현재 회전할 테두리의 레이어 인덱스 (0부터 시작)
     * @return 테두리 요소 리스트 (1차원으로 펼친 값)
     */
    static List<Integer> extractLayer(int layer) {
        List<Integer> edge = new ArrayList<>();

        // 위쪽 (왼쪽 → 오른쪽)
        for (int i = layer; i < M - layer; i++) {
            edge.add(A[layer][i]);
        }

        // 오른쪽 (위쪽 → 아래쪽)
        for (int i = layer + 1; i < N - layer; i++) {
            edge.add(A[i][M - layer - 1]);
        }

        // 아래쪽 (오른쪽 → 왼쪽)
        for (int i = M - layer - 2; i >= layer; i--) {
            edge.add(A[N - layer - 1][i]);
        }

        // 왼쪽 (아래쪽 → 위쪽)
        for (int i = N - layer - 2; i > layer; i--) {
            edge.add(A[i][layer]);
        }

        return edge;
    }

    /**
     * 2. 테두리를 R번 회전시키는 메서드 (리스트 슬라이싱 방식)
     * @param edge 테두리의 1차원 리스트
     * @param rotationCount 회전 횟수 (R % 테두리 길이)
     * @return 회전된 1차원 리스트
     */
    static List<Integer> rotateList(List<Integer> edge, int rotationCount) {
        int n = edge.size();
        List<Integer> rotated = new ArrayList<>(edge.size());

        for (int i = 0; i < n; i++) {
            rotated.add(edge.get((i + rotationCount) % n));
        }

        return rotated;
    }

    /**
     * 3. 테두리를 배열 A에 다시 채워넣는 메서드
     * @param layer 현재 회전할 테두리의 레이어 인덱스 (0부터 시작)
     * @param edge 1차원으로 정렬된 테두리 값
     */
    static void fillLayer(int layer, List<Integer> edge) {
        int index = 0;

        // 위쪽 (왼쪽 → 오른쪽)
        for (int i = layer; i < M - layer; i++) {
            A[layer][i] = edge.get(index++);
        }

        // 오른쪽 (위쪽 → 아래쪽)
        for (int i = layer + 1; i < N - layer; i++) {
            A[i][M - layer - 1] = edge.get(index++);
        }

        // 아래쪽 (오른쪽 → 왼쪽)
        for (int i = M - layer - 2; i >= layer; i--) {
            A[N - layer - 1][i] = edge.get(index++);
        }

        // 왼쪽 (아래쪽 → 위쪽)
        for (int i = N - layer - 2; i > layer; i--) {
            A[i][layer] = edge.get(index++);
        }
    }

    /**
     * 4. 배열 A 출력 메서드
     */
    static void printA() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
