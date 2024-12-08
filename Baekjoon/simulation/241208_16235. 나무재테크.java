package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Tree implements Comparable<Tree> {
    int r, c, age;

    Tree(int r, int c, int age) {
        this.r = r;
        this.c = c;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        return this.age - o.age;
    }
}

public class Main {
    static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int N, M, K;
    static int[][] A;
    static int[][] nutrients;
    static List<Tree> trees = new ArrayList<>();
    static Queue<Tree> deadTrees = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        nutrients = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(nutrients[i], 5);  // 처음 입력 값을 5로 채운다
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }

        // K번 반복
        for (int year = 0; year < K; year++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    static void spring() {
        Collections.sort(trees);  // 어린 나무가 먼저 양분을 섭취할 수 있도록 정렬
        List<Tree> survivedTrees = new ArrayList<>();  // 생존한 나무들을 새롭게 정리

        for (Tree tree : trees) {
            int r = tree.r;
            int c = tree.c;
            if (nutrients[r][c] >= tree.age) {
                nutrients[r][c] -= tree.age;
                tree.age++;
                survivedTrees.add(tree);
            } else {
                deadTrees.add(tree);
            }
        }

        trees = survivedTrees;  // 살아남은 나무들로 갱신
    }

    static void summer() {
        while (!deadTrees.isEmpty()) {
            Tree deadTree = deadTrees.poll();
            int r = deadTree.r;
            int c = deadTree.c;
            nutrients[r][c] += deadTree.age / 2;
        }
    }

    static void fall() {
        List<Tree> newTrees = new ArrayList<>();

        for (Tree tree : trees) {
            if (tree.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = tree.r + dr[i];
                    int nc = tree.c + dc[i];
                    if (inRange(nr, nc)) {
                        newTrees.add(new Tree(nr, nc, 1));
                    }
                }
            }
        }

        trees.addAll(newTrees);  // 나무 추가
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrients[i][j] += A[i][j];
            }
        }
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
