import java.util.Scanner; 
import java.util.ArrayList;

public class Main {
    public static final int NUM_LEN = 3; 

    public static int n;
    public static ArrayList<Integer> series = new ArrayList<>();  // 자리수를 ArrayList<Integer>에 담아서 받을 것
    public static int[] numbers = new int[]{4, 5, 6};

    public static boolean isEqual(int start2, int end2, int start1, int end1) {
        // 탐색 구간의 길이만큼 비교: end1-start1
        for (int i=0; i<=end2-start2; i++) {
            // par1, part2의 각 부분이 동일한지 확인
            if (series.get(start1+i) != series.get(start2+i)) {
                return false;
            }
        }
        return true; 
    }

    public static boolean isPossibleSeries() {
        int len = 1; 
        while (true) {
            //== 두 개의 부분: 시작 인덱스, 끝 인덱스 설정 ==//
            // part2: 뒤에 위치한 부분
            int end2 = series.size()-1;  // series의 마지막 지점: 
            int start2 = end2-len+1;  // 시작 지점 = 끝 - len + 1;
            // part1: 앞에 위치한 부분
            int end1 = start2-1;  // start1 보다 한 칸 앞에 위치
            int start1 = end1-len+1;  // end2 보다 len-1만큼 앞에 위치

            // start1의 앞부분 인덱스가 0보다 작음: 모든 범위를 탐색했음: break
            if (start1<0) {
                break;
            }

            if (isEqual(start2, end2, start1, end1)) {
                return false;  // 연속하지 않을 경우
            }

            len++;  // 확인하는 단어의 길이 증가
        }
        return true; 
    }

    public static void findMinSeries(int cnt) {
        if (cnt == n) {
            // 만들어진 ArrayList의 값을 탐색
            for (int i=0; i<series.size(); i++) {
                System.out.print(series.get(i)); 
            }
            System.exit(0);  // *즉시 코드 종료: 연산 과정을 줄일 수 있는 방법: 공통된 조건에서 끝나는 숫자 조합의 경우, 탐색 과정에서 중단시킨다
        }
        
        for (int i=0; i<NUM_LEN; i++) {
            series.add(numbers[i]);
            if (isPossibleSeries()) {
                findMinSeries(cnt+1);
            }

            series.remove(series.size()-1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();

        findMinSeries(0); 
    }
}