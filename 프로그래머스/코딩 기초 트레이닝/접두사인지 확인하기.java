public class Solution {
  public int solution(String my_string, String is_prefix) {
      boolean result = true; 
      
      if (is_prefix.length() > my_string.length()) {
          result = false; 
      } else {
          for (int i=0; i<is_prefix.length(); i++) {
              if (is_prefix.charAt(i) != my_string.charAt(i)) {
                  result = false; 
              } 
          }
      }
          
      return result ? 1 : 0; 
  }
} {
  
}
