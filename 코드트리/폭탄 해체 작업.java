import java.util.Scanner; 
import java.util.Arrays;
import java.util.PriorityQueue;  // *매 순간마다 남은 폭탄들 중 최대 점수를 빠르게 구하기 위해 우선순위 큐 사용

// TC 확인하기
// 3
// 6 1
// 9 2 
// 8 2

class Pair implements Comparable<Pair>{
    int timeLimit, score;

    public Pair(int timeLimit, int score) {
        this.timeLimit = timeLimit;
        this.score = score;
    }

    @Override
    public int compareTo(Pair p) {
        if (this.timeLimit != p.timeLimit) {  
            return this.timeLimit - p.timeLimit;  // 1차 기준
        }
        return this.score - p.score;  // 2차 기준  
    }
}

public class Main {
    public static final int MAX_T = 10000;
    public static final int MAX_N = 10000;

    public static int n;
    public static Pair[] bomb = new Pair[MAX_N];
    public static PriorityQueue<Pair> pq = new PriorityQueue<>(); 

    // bombIdx에 기록된 폭탄의 timeLimit을 return
    public static int getTimeLimit(int bombIdx) {
        int timeLimit = bomb[bombIdx].timeLimit;
        return timeLimit;
    }

    public static int getScore(int bombIdx) {
        int score = bomb[bombIdx].score;
        return score;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int point = sc.nextInt();
            int timeLimit = sc.nextInt();

            bomb[i] = new Pair(timeLimit, point);
        }

        Arrays.sort(bomb, 0, n);  // 사용자 정의 정렬

        //==MAX_T부터 1까지 확인, 가장 많이 점수를 얻을 수 있는 폭탄을 선택==//
        // 최대 점수를 갖는 폭탄을 빠르게 고르기 위해 우선순위 큐를 이용
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int bombIdx = n-1; 
        int ans = 0;

        for (int t=MAX_T; t>=1; t--) {

            // bombIdx: 감소 중이므로 0보다 크거나 같은지 확인
            // getTimeLimit: 현재 확인 중인 시간(t)보다 기한(timeLimit)이 이전인지 확인 
            while ((bombIdx >= 0) && (getTimeLimit(bombIdx) >= t)){  
                pq.add(-getScore(bombIdx));  // 조건을 통과할 때 우선순위 큐에 입력, -: 내림차순 정렬
                bombIdx--;
            }

            // pq: t 시각일 때 제거할 수 있는 폭탄의 목록, 점수기준 내림차순 정렬된 우선순위 큐에서 하나를 뽑아서 점수에 추가
            if (!pq.isEmpty()) {
                ans += -pq.poll(); 
            }
        }

        System.out.println(ans); 
    }
}