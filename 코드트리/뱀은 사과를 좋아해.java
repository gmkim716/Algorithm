import java.util.Scanner;
import java.util.ArrayList; 

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int MAX_N = 100;
    public static final int ASCII_NUM = 128;

    public static final int[] dx = new int[]{-1,1,0,0};
    public static final int[] dy = new int[]{0,0,1,-1};

    public static int n, m, k, ans;

    public static ArrayList<Pair> snake = new ArrayList<>(); 
    public static int[][] grid = new int[MAX_N+1][MAX_N+1];
    public static boolean[][] apple = new boolean[MAX_N+1][MAX_N+1];
    public static int[] dirMapper = new int[ASCII_NUM];  // *mapper을 이곳에 정의해도 괜찮아

    public static boolean canGo(int x, int y) {
        return 1<=x && x<=n && 1<=y && y<=n;
    }

    // *꼬이는 지 여부를 boolean 타입 메서드로 정의
    public static boolean isTwisted(Pair newHead) {
        for (int i=0; i<snake.size(); i++) {
            if (newHead.x == snake.get(i).x && newHead.y == snake.get(i).y) {
                return true; 
            }
        }
        return false;
    }
    
    // *사과를 먹었을 때를 boolean 타입 메서드로 정의
    public static boolean pushFront(Pair newHead) {
        // 몸이 꼬이는지 확인
        if (isTwisted(newHead)) {
            return false;
        }
        snake.add(0, newHead);  // **.add(0, element): element를 가장 맨 앞에 추가 
        return true;
    }

    // 꼬리를 지우기
    public static void popBack() {
        snake.remove(snake.size()-1); 
    }

    // 정상적으로 움직일 수 있는 지 여부 확인
    public static boolean moveSnake(int nx, int ny) {
        
        // 1.사과가 있을 때: 추가
        if (apple[nx][ny]) {
            // 1-1. 머리 한칸 추가
            if(!pushFront(new Pair(nx, ny))){   // snake.add는 pushFront 내부에서 진행됨
                return false;
            }
            // 1-2. 먹은 사과를 제거
            apple[nx][ny] = false;  // 먹은 사과는 사라짐
        } 

        // 2.사과가 없을 때: 이동
        else {
            //== 주의: 2-1, 2-2 순서가 바뀌었을 때 오류발생 ==//
            // 2-1. 꼬리 한칸 제거
            popBack();
            // 2-2. 머리 한칸 추가
            if(!pushFront(new Pair(nx, ny))) {
                return false; 
            }

        }
        return true;
    }

    // 뱀을 dir 방향으로 num번 움직인다
    public static boolean move(int dir, int num) {
        while (num-->0) {
            ans ++;

            Pair head = snake.get(0);
            int nx = head.x + dx[dir];
            int ny = head.y + dy[dir];

            //==움직일 수 없는 상황 의==//
            if (!canGo(nx, ny)) {  // 1. 범위 밖으로 나갔을 때 
                return false; 
            }
            if (!moveSnake(nx, ny)) {  // 2. 뱀이 움직일 수 없을 때
                return false;
            }
        }
        return true;  // 정상적인 수행을 의미
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // dirMapper 값 설정
        dirMapper['U'] = 0;
        dirMapper['D'] = 1;
        dirMapper['R'] = 2;
        dirMapper['L'] = 3;

        n = sc.nextInt();
        m = sc.nextInt();  // 사과의 개수
        k = sc.nextInt();  // 방향 변환 횟수
        ans = 0;

        // 사과의 위치
        for (int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            apple[x][y] = true; 
        }

        // 시작 위치 (1, 1)
        snake.add(new Pair(1, 1));

        // 뱀의 방향 정보
        for (int i=0; i<k; i++) {
            
            int d = dirMapper[sc.next().charAt(0)];  // 방향
            int p = sc.nextInt();  // 거리

            if(!move(d,p)) {  // 움직이는 것이 불가능할 때 멈춤
                break;
            }
        }

        System.out.println(ans); 
    }
}