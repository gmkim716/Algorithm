import java.util.*;

public class Main {

    static int N, M;
    static char[] board;

    public static void main(String[] args) throws Exception {
        BuffredReader br = new BufferedReader(new InputStream(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 세로
        M = Integer.parseInt(st.nextToken());  // 가로

        board = new char[N+1][M+1];



    }
}