import java.util.ArrayList;

class Solution {
    public int[] solution(int n, int k) {
        ArrayList<Integer> multiples = new ArrayList<>();

        // k의 배수를 찾기 위해 반복문 사용
        for (int i = k; i <= n; i += k) {
            multiples.add(i);
        }

        // ArrayList를 int 배열로 변환
        int[] result = new int[multiples.size()];
        for (int i = 0; i < multiples.size(); i++) {
            result[i] = multiples.get(i);
        }

        return result;
    }
}
 {
  
}
