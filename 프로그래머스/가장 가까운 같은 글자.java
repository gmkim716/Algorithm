class Solution {
  public int[] solution(String s) {
      char[] arr = s.toCharArray();
      int[] ans = new int[s.length()];
      
      for (int i=0; i<ans.length; i++) {
          ans[i] = -1; 
      }
      
      for (int i=0; i<arr.length; i++) {
          findSameWork(arr[i], i, arr, ans); 
      }
      
      return ans; 
  }
  
  public void findSameWork(char c, int idx, char[] arr, int[] ans) {
      int cnt = 0; 
      
      for (int i=1; i<=idx; i++) {
          cnt += 1;
          if (c == arr[idx-i]) {
              ans[idx] = cnt; 
              break; 
          }
      }
  }

}