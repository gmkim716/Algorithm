import java.util.Arrays;
import java.util.ArrayList; 


class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        ArrayList<int[]> list = new ArrayList<int[]>(); 
        
        int expIdx;
        switch (ext) {
            case "code": expIdx = 0; break;
            case "date": expIdx = 1; break;
            case "maximum": expIdx = 2; break;
            case "remain": expIdx = 3; break;
            default:  expIdx = -1; break;
        }
        
        int sortIdx;
        switch (sort_by) {
           case "code": sortIdx = 0; break;
           case "date": sortIdx = 1; break;
           case "maximum": sortIdx = 2; break;
           case "remain": sortIdx = 3;  break;
           default: sortIdx = -1; break;
        }
        
        for (int i=0; i<data.length; i++) {
            if (data[i][expIdx] < val_ext) {
                list.add(data[i]);
            }
        }
        
        // 람다식을 이용해 정렬
        list.sort((arr1, arr2) -> Integer.compare(arr1[sortIdx], arr2[sortIdx]));
        
        int[][] ans = new int[list.size()][]; 
        
        // 정답 형식에 맞춰 변형
        for (int i=0; i<list.size(); i++) {
            ans[i] = list.get(i);
        }
        
        return ans;
    }
}