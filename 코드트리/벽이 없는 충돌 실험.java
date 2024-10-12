import java.util.Scanner;
import java.util.ArrayList;

class Marble {
    int n, x, y, w, d;
    public Marble(int x, int y, int w, int d, int n) {
        this.x = x;  
        this.y = y;
        this.w = w;  // 무게
        this.d = d;  // 이동 방향 
        this.n = n;  // 번호
    }
}

public class Main {
    public static final int BLANK = -1; 
    public static final int OFFSET = 2000;  // 좌표 조정을 위해 1000이 아닌 2000으로 설정
    public static final int MAX_NUM = 100;
    public static final int ASCII_NUM = 128; 
    public static final int COORD_SIZE = 4000;  // -2000 ~ 2000, coordinate 
    public static final int mapper[] = new int[ASCII_NUM];

    public static final int[] dx = new int[]{0, 1, -1, 0};
    public static final int[] dy = new int[]{1, 0, 0, -1};

    public static int t, n;
    public static int currTime, lastCollisionTime;

    public static ArrayList<Marble> marbles = new ArrayList<>();  // 현재 시점의 구슬 위치
    public static ArrayList<Marble> nextMarbles = new ArrayList<>();  // 다음 시점의 구슬 위치
    public static int[][] nextMarbleIndex = new int[COORD_SIZE+1][COORD_SIZE+1];


    // 1초 후에 이동 상태 반환
    public static Marble Move(Marble marble) {
        int x = marble.x;
        int y = marble.y;
        int w = marble.w;
        int d = marble.d;
        int n = marble.n;

        int nx = x + dx[d];
        int ny = y + dy[d];

        return new Marble(nx, ny, w, d, n); 
    }

    public static boolean inRange(Marble marble) {
        int x = marble.x;
        int y = marble.y;
        return 0<=x && x<=COORD_SIZE && 0<=y && y<=COORD_SIZE;        
    }

    // 현재의 marble과 충돌이 일어나는 구슬이 있는지 확인
    // 있으면 해당 구슬의 idx를 반환, 없다면 BLANK 반환
    public static int findDuplicateMarble(Marble marble) {
        int x = marble.x;
        int y = marble.y;

        return nextMarbleIndex[x][y];
    }

    // marble1, marble2가 충돌했을 때 살아남는 구슬 반환
    public static Marble Collide(Marble marble1, Marble marble2) {
        int w1 = marble1.w;
        int n1 = marble1.n;

        int w2 = marble2.w;
        int n2 = marble2.n;

        // 첫번째 구슬을 따라가는 경우: 첫 번재 구슬의 무게가 더 크거나, 무게는 같은데 번호가 더 클경우 
        if (w1 > w2 || (w1 == w2 && n1 > n2)) {
            return marble1;
        } else {
            return marble2;
        }
    }

    public static void pushNextMarble(Marble marble) {
       
        // 격자 범위를 벗어났는지 확인: (0, 0) ~ (COORD_SIZE, COORD_SIZE)
        if (!inRange(marble)) 
            return; 
        
        int index = findDuplicateMarble(marble);

        // case1) 같은 위치에 구슬이 아직 없을 때
        if (index == BLANK) {
            // 구슬 목록에 추가
            nextMarbles.add(marble);

            // 새로 추가되는 구슬의 index 표시 *why?:
            int x = marble.x;
            int y = marble.y;
            nextMarbleIndex[x][y] = nextMarbles.size()-1;  // *
        }

        // case2) 다음 구슬 목록에서 같은 위치에 구슬이 있을 때
        else {
            Marble newMarble = Collide(nextMarbles.get(index), marble);
            nextMarbles.set(index, newMarble);  // *set: 특정 인덱스의 값을 대체(업데이트)
            
            // System.out.println("else문 작동"); 
            lastCollisionTime = currTime;  // 현재 시간을 가장 최근 충돌 시간에 기록
        }

    }

    public static void simulate() {
        for (int i=0; i<marbles.size(); i++) {
            // 1. 구슬 목록에서 하나의 구슬을 가져와 Move 진행
            Marble nextMarble = Move(marbles.get(i));  
        
            // 2. 다음 구슬 목록에 반영
            pushNextMarble(nextMarble);
        }

        marbles = (ArrayList<Marble>) nextMarbles.clone();  // *캐스트 반드시 적용 필요 

        // 다음 시뮬레이션을 위해 nextMarbleIndex 기록을 초기화
        for (int i=0; i<nextMarbles.size(); i++) {
            int x = nextMarbles.get(i).x;
            int y = nextMarbles.get(i).y;
            nextMarbleIndex[x][y] = BLANK;
        }
        nextMarbles = new ArrayList<>(); 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        mapper['U'] = 0;
        mapper['R'] = 1;
        mapper['L'] = 2;
        mapper['D'] = 3;
        
        t = sc.nextInt();

        // 처음에 구슬이 놓여있지 않았다는 표시로 BLANK로 채워 넣기
        for (int i=0; i<=COORD_SIZE; i++) {
            for (int j=0; j<=COORD_SIZE; j++) {
                nextMarbleIndex[i][j] = BLANK;
            }   
        }
        
        while (t-->0) {
            // 테스트 케이스가 시작될 때마다 초기화
            marbles = new ArrayList<>();
            lastCollisionTime = -1; 

            n = sc.nextInt();
            for (int i=1; i<=n; i++) {  // 1번 부터 n번까지
                int x = sc.nextInt();
                int y = sc.nextInt();
                int w = sc.nextInt();  // 무게 
                char d = sc.next().charAt(0);  // 방향
                
                //== 좌표 조정==//
                // **좌표를 2배 불려서 1초에 한 칸씩 이동하는 문제로 바꿈, 구슬이 움직이는 도중 충돌하는 경우에 대한 고민을 덜 수 있다
                x *= 2; y *= 2;  
                x += OFFSET; y += OFFSET;

                // 구슬 목록에 등록
                marbles.add(new Marble(x, y, w, mapper[d], i)); 
            }

            // 1초부터 COORD_SIZE까지 진행
            for (int i=1; i<=COORD_SIZE; i++) {
                currTime = i; 
                simulate(); 
            }
            System.out.println(lastCollisionTime); 
        }
    }
}