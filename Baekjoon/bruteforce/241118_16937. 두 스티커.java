import java.util.ArrayList;
import java.util.Scanner;

class Paper {
    int r, c;

    public Paper(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public ArrayList<Paper> getRotations() {
        ArrayList<Paper> rotations = new ArrayList<>();
        rotations.add(this); // 원래 상태
        rotations.add(new Paper(this.c, this.r)); // 90도 회전 상태
        return rotations;
    }
}

public class Main {
    static int H, W, N, maxArea = 0;
    static ArrayList<Paper> papers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            papers.add(new Paper(r, c));
        }

        dfs(0, new ArrayList<>());
        System.out.println(maxArea);
    }

    static void dfs(int count, ArrayList<Paper> selected) {
        if (count == 2) {
            Paper p1 = selected.get(0);
            Paper p2 = selected.get(1);
            maxArea = Math.max(maxArea, checkWithTwoPapers(p1, p2));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!selected.contains(papers.get(i))) { // 중복 선택 방지
                selected.add(papers.get(i));
                dfs(count + 1, selected);
                selected.remove(selected.size() - 1);
            }
        }
    }

    static int checkWithTwoPapers(Paper p1, Paper p2) {
        int localMaxArea = 0;

        for (Paper rot1 : p1.getRotations()) {  // p1의 회전 상태
            for (Paper rot2 : p2.getRotations()) {  // p2의 회전 상태

                // 첫 번째 스티커 위치 탐색
                for (int x1 = 0; x1 + rot1.r <= H; x1++) {
                    for (int y1 = 0; y1 + rot1.c <= W; y1++) {

                        // 두 번째 스티커 위치 탐색
                        for (int x2 = 0; x2 + rot2.r <= H; x2++) {
                            for (int y2 = 0; y2 + rot2.c <= W; y2++) {

                                // 겹침 확인 후 최대 넓이 갱신
                                if (!isNotOverlap(x1, y1, rot1.r, rot1.c, x2, y2, rot2.r, rot2.c)) {
                                    int area = rot1.r * rot1.c + rot2.r * rot2.c;
                                    localMaxArea = Math.max(localMaxArea, area);
                                }
                            }
                        }
                    }
                }
            }
        }

        return localMaxArea;
    }

    static boolean isNotOverlap(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return (x1 + w1 <= x2 || x2 + w2 <= x1 || y1 + h1 <= y2 || y2 + h2 <= y1);
    }
}
