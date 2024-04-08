import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] A;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N 값이 정해졌으므로 길이 지정
        A = new ArrayList[N+1];
        visited = new boolean[N+1];
        for (int i=1; i<N+1; i++) {
            A[i] = new ArrayList<Integer>();
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            A[s].add(e);
            A[e].add(s);
        }

        int cnt = 0;
        for (int i=1; i<N+1; i++) {
            if (!visited[i]) {
                cnt ++;
                DFS(i);
            }
        }
        System.out.println(cnt);
    }

    static void DFS(int v) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        for (int i: A[v]) {
            if (visited[i] == false) {
                DFS(i);
            }
        }
    }
}
