import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100; 
    public static final int BLANK = -1; 
    public static final int WILL_EXPLODE = 0;

    public static int n, m, k, endOfNumbers_1d, endOfTemp_1d;
    
    public static int[][] numbers_2d = new int[MAX_NUM][MAX_NUM];
    public static int[][] temp_2d = new int[MAX_NUM][MAX_NUM];
    public static int[] numbers_1d = new int[MAX_NUM];
    public static int[] temp_1d = new int[MAX_NUM];


    // 격자의 특정 열을 일차원 배열에 복사
    public static void copyColumn(int col) {
        endOfNumbers_1d = 0;
        for (int i=0; i<n; i++) {
            if (numbers_2d[i][col] != BLANK) {
                numbers_1d[endOfNumbers_1d++] = numbers_2d[i][col];
            }
        }
    }
    
    public static void moveToTemp() {
        endOfTemp_1d = 0;
        for(int i=0; i<endOfNumbers_1d; i++) {
            if(numbers_1d[i] != WILL_EXPLODE) {
                temp_1d[endOfTemp_1d++] = numbers_1d[i];
            }
        }
    }

    public static void copyFromTemp() {
        endOfNumbers_1d = endOfTemp_1d;
        for (int i=0; i<endOfNumbers_1d; i++) {
            numbers_1d[i] = temp_1d[i];
        }

    }

    public static int getEndIdxOfExplosion(int startIdx, int currNum) {
        int endIdx = startIdx+1;
        while(endIdx < endOfNumbers_1d) {
            if(numbers_1d[endIdx] == currNum)
                endIdx++;
            else{
                break;
            }
        }
    
        return endIdx-1;
    }

    public static void fillZero(int startIdx, int endIdx) {
        for(int i = startIdx; i <= endIdx; i++) {
            numbers_1d[i] = WILL_EXPLODE;
        }
    }
    // **방식 익히기
    public static void explode() {
        
        boolean didExplode;
        do {
            didExplode = false; 
            // 각 위치마다 폭탄이 m개 이상 있는지 확인
            for (int i=0; i<endOfNumbers_1d; i++) {

                // 이미 터지기로 예정된 폭탄은 패스
                if (numbers_1d[i] == WILL_EXPLODE) continue;

                // 연속해서 같은 숫자를 갖는 폭탄 중, 가장 마지막 위치 찾기
                int endIdx = getEndIdxOfExplosion(i, numbers_1d[i]);

                if (endIdx-i+1 >= m) {
                    fillZero(i, endIdx);  // i~endIdx 범위: 폭탄이 터졌음을 기록
                    didExplode = true;
                }
            }

            // Arr에서 폭탄이 터진 이후의 결과를 Temp에 임시 저장
            moveToTemp();

            // Temp 배열을 그대로 copy
            copyFromTemp();

        } while (didExplode);
    }

    public static void copyResult(int col) {
        int resultIdx = endOfNumbers_1d-1;
        for (int i=n-1; i>=0; i--) {
            if (resultIdx >= 0) {
                numbers_2d[i][col] = numbers_1d[resultIdx--];
            } else {
                numbers_2d[i][col] = BLANK;
            }
        }
    }

    public static void simulate() {
        for (int col=0; col<n; col++) {
            copyColumn(col);  // 특정 열을 배열에 복사 
            explode();  //  폭탄을 터트림
            copyResult(col);  // 결과를 기존 배열에 업데이트 
        }
    }

    public static void rotate() {
        // temp 초기화 
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++) {
                temp_2d[i][j] = BLANK;
            }
        }

        int currIdx;
        for(int i=n-1; i>=0; i--) {
            currIdx = n-1;
            for(int j=n-1; j>= 0; j--) {
                if(numbers_2d[i][j] != BLANK)
                    temp_2d[currIdx--][n-i-1] = numbers_2d[i][j];
            }
        }

        // temp의 값을 기존 격자에 복사 
        for (int i=0; i<n; i++) {
            for (int j=0 ;j<n; j++) {
                numbers_2d[i][j] = temp_2d[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for(int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                numbers_2d[i][j] = sc.nextInt();
            }
        }

        simulate();
        while (k-->0) {
            rotate();
            simulate();
        }
        
        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (numbers_2d[i][j] != BLANK) {
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }
}