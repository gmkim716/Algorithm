class Solution {
    
  private static int min_fold = 0;
  private static int wallet_width; 
  private static int wallet_height; 
  private static int bill_width;
  private static int bill_height;
  
  public int solution(int[] wallet, int[] bill) {
      
      findWidthHeightOfWallet(wallet);
      findWidthHeightOfBill(bill);
      
      while (shouldFold()) {
          foldBill(); 
          if (shouldRotate()) {
              rotate();
          }
      }
      return min_fold;
  }
  
  public boolean shouldFold() {
      if (bill_width > wallet_width || bill_height > wallet_height) {
          return true;
      }
      return false;
  }
  
  public void foldBill() {
      min_fold += 1; 
      bill_width = bill_width / 2;
  }

  public boolean shouldRotate() {
      if (bill_width < bill_height) {
          return true; 
      }
      return false;
  }
  
  public void rotate() {
      int temp = bill_width; 
      bill_width = bill_height;
      bill_height = temp;
  }
  
  public void findWidthHeightOfWallet(int[] wallet) {
      if (wallet[0] >= wallet[1]) {
          wallet_width = wallet[0]; 
          wallet_height = wallet[1];
      } else {
          wallet_width = wallet[1]; 
          wallet_height = wallet[0];
      }
  }
  
  public void findWidthHeightOfBill(int[] bill) {
      if (bill[0] >= bill[1]) {
          bill_width = bill[0]; 
          bill_height = bill[1];
      } else {
          bill_width = bill[1]; 
          bill_height = bill[0];
      }
  }  
}