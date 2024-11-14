import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class State {
    int screen, clipboard, time;

    public State(int screen, int clipboard, int time) {
        this.clipboard = clipboard;
        this.screen = screen;
        this.time = time;
    }
}

public class Main {

    static int S;
    static boolean[][] visited;
    static Queue<State> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();  // 화면에 출력할 이모티콘 개수
        visited = new boolean[2001][2001];

        queue.add(new State(1, 0, 0));
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int screen = current.screen;
            int clipboard = current.clipboard;
            int time = current.time;

            // 종료 조건: 화면 이모티콘 개수가 S개에 도달
            if (screen == S) {
                System.out.println(time);
                return;
            }

            // 1. 복사: 현재 화면의 이모티콘을 클립보드에 복사한다
            if (!visited[screen][screen]) {
                queue.add(new State(screen, screen, time + 1));
                visited[screen][screen] = true;
            }

            // 2. 붙여넣기: 클립들의 이모티콘을 화면에 붙여넣는다
            if (0 < clipboard && screen + clipboard < 2000 && !visited[screen + clipboard][clipboard]) {
                queue.add(new State(screen + clipboard, clipboard, time + 1));
                visited[screen + clipboard][clipboard] = true;
            }

            // 3. 삭제: 화면의 이모티콘 개수를 하나 줄인다
            if (0 < screen && !visited[screen - 1][clipboard]) {
                queue.add(new State(screen - 1, clipboard, time + 1));
                visited[screen - 1][clipboard] = true;
            }
        }
    }
}
