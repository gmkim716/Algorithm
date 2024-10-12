// (|x|, x)를 원소로 하는 우선순위 큐 배열 만들기

import java.util.Scanner;
import java.util.PriorityQueue; 

class Pair implements Comparable<Pair> {
    int absX, x;

    public Pair (int absX, int x) {
        this.absX = absX;
        this.x = x;
    }

    @Override
    public int compareTo(Pair p) {
        if (this.absX != p.absX) {
            return this.absX - p.absX;
        } else {
            return this.x - p.x;
        }
    }
}

public class Main {
    public final static int MAX_N = 100000;

    public static int n;

    public static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int x = sc.nextInt();

            if (x != 0) {
                pq.add(new Pair(Math.abs(x), x));
            } else {
                if (pq.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
                System.out.println(pq.poll().x);
            }
        }
    }
}