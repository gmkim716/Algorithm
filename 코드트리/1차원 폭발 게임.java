import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100; 
    
    public static int n, m, endOfArray, endOfTemp;

    public static int[] arr = new int[MAX_NUM];
    public static int[] tempArr = new int[MAX_NUM];

    // 주어진 시작점을 기준으로 연속된 폭탄의 끝 위치 반환
    public static int getEndIdxOfExplosion(int startIdx, int currNum) {

        int endIdx = startIdx+1;  // endIdx: 시작점은 startIdx 보다 1 이상

        while (endIdx < endOfArray) {  // endIdx가 n보다 작은 동안 
            if (arr[endIdx] == currNum) {  // 연속할 경우 
                endIdx ++;
            } else {  // 연속하지 않을 때 중단 
                break; 
            }
        }

        return endIdx-1;  
    }

    // 폭탄이 터졌음을 나타냄
    public static void fillZero(int startIdx, int endIdx) {
        for (int i=startIdx; i<=endIdx; i++) {
            arr[i] = 0;
        }
    }

    // arr에서 폭탄이 터진 결과를 temp에 임시 저장
    public static void moveToTemp() {
        endOfTemp = 0;
        for (int i=0; i<endOfArray; i++) {
            if (arr[i] != 0) {
                tempArr[endOfTemp++] = arr[i];  // endOfTemp++: 앞에서부터 채워나감
            }
        }
    }

    public static void copy() {
        endOfArray = endOfTemp;
        for (int i=0; i<endOfArray; i++) {
            arr[i] = tempArr[i];
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();  // 연속했을 때 폭탄이 터지는 개수

        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt(); 
        }

        endOfArray = n;
        
        boolean didExplode = false;
        
        // do while: 터질 수 있는 폭탄이 없어질 때까지 반복 진행
        do {
            didExplode = false;  // didExplode 초기값 = false
            
            for (int currIdx=0; currIdx<endOfArray; currIdx++) {
                
                // 연산 낭비를 줄임
                if (arr[currIdx] == 0) {
                    continue;
                }

                // 연속되는 폭탄의 마지막 인덱스 위치 확인
                int endIdx = getEndIdxOfExplosion(currIdx, arr[currIdx]);

                // 연속한 숫자가 m개 이상일 때 폭탄이 터짐
                if (endIdx-currIdx+1 >= m) {
                    fillZero(currIdx, endIdx);  // 터진 폭탄의 위치 값을 0으로 수정 
                    didExplode = true;  // 폭탄이 터졌음을 표시
                }
            }

            // 폭탄이 터진 이후의 결과를 temp에 저장
            moveToTemp();

            // arrTemp 배열을 Copy해서 arr 업데이트
            copy(); 
        } 
        while (didExplode);  // 폭탄이 터질 수 없을 때까지 반복

        // 정답 출력
        System.out.println(endOfArray);

        for (int i=0; i<endOfArray; i++) {
            System.out.println(arr[i]); 
        }
    }
}