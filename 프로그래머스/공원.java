import java.util.Arrays;

class Solution {
    private static int answer = -1; 
    private static int MAX_COL;
    private static int MAX_ROW;
    private static String[][] MAP;
    
    public int solution(int[] mats, String[][] park) {
        
        // 공원의 최대 크기 저장 
        MAX_COL = park.length; 
        MAX_ROW = park[0].length;
        MAP = park; 
        
        // mats를 역순으로 정렬        
        reverseSort(mats); 
        
        // 길이가 mats[i]일 때 탐색 진행
        for (int i=0; i<mats.length; i++) {
            if (tryWithLength(mats[i])) {
                break;
            }
        }
        
        return answer;
    }
    
    // 길이가 len일 때의 탐색 진행
    // true이면 탐색 종료
    public boolean tryWithLength(int len) {
        for (int i = 0; i < MAX_COL; i++) {
            for (int j = 0; j < MAX_ROW; j++) {
                if (possible(i, j, len) && check(i, j, len)) {
                    answer = len;
                    return true;
                }
            }
        }
        return false; 
    }
                   
    // 최대 범위가 MAP 내부에 있는지 확인한다
    public boolean possible(int c, int r, int len) {
        return (c + len <= MAX_COL) && (r + len <= MAX_ROW); 
    }
                    
    // 돗자리를 펼 수 있는지 확인한다
    public boolean check(int c, int r, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!MAP[c + i][r + j].equals("-1")) {
                    return false; 
                }
            }
        }
        return true;
    }
    
    // 배열을 역순으로 정렬하는 메서드
    public void reverseSort(int[] mats) {
        Arrays.sort(mats); 
        
        for (int i = 0; i < mats.length / 2; i++) {
            int temp = mats[i]; 
            mats[i] = mats[mats.length - i - 1];
            mats[mats.length - i - 1] = temp;
        }
    }
}
