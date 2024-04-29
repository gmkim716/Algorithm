import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // dx, dy 정의
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0}; 
    static boolean[][] visited;  // visited
    static int[][] A;  // 배열
    static int N, M;  // 변수
        
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M]; 
        visited = new boolean[N][M];

        // N x M, A배열 입력
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();

            for (int j=0; j<M; j++) {
                A[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        BFS(0, 0);
        System.out.println(A[N-1][M-1]);  // A배열의 도착 지점의 값 출력
    }
    
    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();  // 큐 정의
        queue.offer(new int[] { i, j });  // 길이가 2인 정수 배열 생성, {i, j} 삽입  ※ add와 기능적으로 동일, 큐가 찼을 때 add는 예외를 반환, offer는 false를 반환
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int now[] = queue.poll();  // 뒤에서 하나 추출
            for (int k=0; k<4; k++) {  // k: 4방향
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                if (x >= 0 && x<N && y >= 0 && y<M) {
                    if (A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        A[x][y] = A[now[0]][now[1]] + 1;  // 깊이 업데이트
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}