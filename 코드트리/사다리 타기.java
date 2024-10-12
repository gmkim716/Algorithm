import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Collections; 

class Pair implements Comparable<Pair>{
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair b) {
        if (x != b.x) {
            return x - b.x;  // x를 기준으로 정렬
        }
        return y - b.y;  // y를 기준으로 정렬
    }
}

public class Main {
    public static final int MAX_INT = Integer.MAX_VALUE;
    public static final int MAX_N = 11;
    public static final int MAX_M = 15;

    public static int n, m;
    public static int ans = MAX_INT;

    public static ArrayList<Pair> lines = new ArrayList<>(); 
    public static ArrayList<Pair> selectedLines = new ArrayList<>(); 

    public static boolean possible() {
        
        // num배열 초기화 
        int[] num1 = new int[MAX_N];
        int[] num2 = new int[MAX_N];
        for (int i=0; i<n; i++) {
            num1[i] = num2[i] = i;  // *자바에서 이렇게도 전개가 가능한가 보다
        }

        //== num1, num2에 해당하는 숫자를 바꿔줌 ==//
        for (int i=0; i<lines.size(); i++) {
            int idx = lines.get(i).y;  
            int temp = num1[idx]; 
            
            // idx, idx+1에 위치한 값 교체
            num1[idx] = num1[idx+1];  
            num1[idx+1] = temp;  
        }

        for (int i=0; i<selectedLines.size(); i++) {
            int idx = selectedLines.get(i).y;
            int temp = num2[idx];

            // idx, idx+1에 위치한 값 교체
            num2[idx] = num2[idx+1];
            num2[idx+1] = temp; 
        }

        // 두 상황의 결과가 동일한지 확인
        for (int i=0; i<n; i++) {
            if(num1[i] != num2[i]) {
                return false; 
            }
        }
        return true;
    }

    public static void findMinLines(int cnt) {
        
        // 종료 조건: cnt == m  // Q.why? A.M개의 가로줄을 사용해서 만들 수 있는 모든 조합 탐색이 목표
        if (cnt == m) {
            if (possible()) {
                ans = Math.min(ans, selectedLines.size()); 
            }
            return; 
        }

        selectedLines.add(lines.get(cnt)); 
        findMinLines(cnt+1);
        selectedLines.remove(selectedLines.size()-1);

        findMinLines(cnt+1);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();  // 세로줄 개수
        m = sc.nextInt();  // 가로줄 개수

        for (int i=0; i<m; i++) {  
            int a = sc.nextInt();  // a번째 세로줄 & a+1번재 세로줄 연결 
            int b = sc.nextInt();  // b번째 위치에 가로줄이 그어짐

            lines.add(new Pair(b, a-1));  // b행 a열, 오른쪽 연결
        }

        // 정렬: 사다리 위에서부터 아래로 처리를 위함 
        Collections.sort(lines);

        findMinLines(0); 

        System.out.println(ans); 
    }
}