import java.util.Arrays; 

class Solution {
    public int[] solution(int[] arr) {
        int start = -1;
        int end = -1; 
        
        for (int i=0; i<arr.length; i++) {
            if (start == -1 && arr[i] == 2) {
                start = i+1;
                end = i+1;
            } else if (start != -1 && arr[i] ==2) {
                end = i+1;
            }
        }
        
        int[] ansList = new int[end-start+1]; 
        int idx = 0; 
        for (int i=start; i<=end; i++) {
            if (i != -1) {
                ansList[idx] = arr[i-1];
                idx ++; 
            }   
        }
        
        if (idx == 0) {
            ansList[0] = -1; 
        }
        
        return ansList; 
    }
}