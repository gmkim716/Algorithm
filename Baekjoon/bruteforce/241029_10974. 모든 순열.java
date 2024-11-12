import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

    static int N;
    static int[] arr;
    static List<Integer> lst = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N+1];

        dfs();
    }

    static void dfs() {
        if (lst.size() == N) {
            printLst();
            return;
        }

        for (int i=1; i<=N; i++) {
            if (!visited[i]) {
                lst.add(i);
                visited[i] = true;

                dfs();

                lst.remove(lst.size()-1);
                visited[i] = false;
            }
        }
    }

    static void printLst() {
        for (Integer num: lst) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
