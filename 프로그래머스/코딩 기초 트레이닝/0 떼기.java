class Solution {
  public String solution(String n_str) {
      boolean flag = false; 
      
      char[] cArr = n_str.toCharArray();

      String answer = ""; 
      for (int i=0; i<n_str.length(); i++) {
          if (cArr[i] != '0') {
              flag = true;
          } 
          
          if (flag) {
              answer += cArr[i]; 
          }
      }
      
      return answer;
  }
}