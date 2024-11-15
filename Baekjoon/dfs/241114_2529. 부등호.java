import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int K;
    static boolean[] visited;
    static char[] arr;
    static String minAns = Long.MAX_VALUE + "";
    static String maxAns = Long.MIN_VALUE + "";
    static List<Integer> lst = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        arr = new char[K];
        visited = new boolean[10];

        for (int i=0; i<K; i++) {
            arr[i] = sc.next().charAt(0);
        }

        dfs(0);

        System.out.println(maxAns);
        System.out.println(minAns);
    }

    static void dfs(int cnt) {
        if (cnt == K+1) {
            StringBuilder sb = new StringBuilder();
            sb.append(lst.get(0));

            for (int i=1; i<lst.size(); i++) {
                int first = lst.get(i-1);
                int second = lst.get(i);

                switch (arr[i-1]) {
                    case '<':
                        if (first < second) {
                            sb.append(second);
                        } else {
                            return;
                        }
                        break;
                    case '>':
                        if (first > second) {
                            sb.append(second);
                        } else {
                            return;
                        }
                        break;
                }
            }

            long longResult = Long.parseLong(sb.toString());

            if (longResult < Long.parseLong(minAns)) {
                minAns = sb.toString();
            }

            if (longResult > Long.parseLong(maxAns)) {
                maxAns = sb.toString();
            }
            return;
        }

        for (int i=0; i<=9; i++) {
            if (!visited[i]) {
                lst.add(i);
                visited[i] = true;

                dfs(cnt+1);

                lst.remove(lst.size()-1);
                visited[i] = false;
            }
        }
    }
}
