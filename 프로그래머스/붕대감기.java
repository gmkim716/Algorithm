class Solution {
  private static int[] attacks_list; 
  private static int success_cnt = 0; 
  private static int HEALTH; 
  private static int TIME; 
  
  public int solution(int[] bandage, int health, int[][] attacks) {
      // static 변수 초기화
      HEALTH = health;
      attacks_list = new int[1001];
      for (int i=0; i<attacks.length; i++) {
          attacks_list[attacks[i][0]] = attacks[i][1]; 
      }
      int endTime = attacks[attacks.length-1][0];  // 몬스터의 마지막 공격 시각
      
      while (TIME < endTime && !isDead()) {
          TIME += 1; 
          
          if (isAttacked(TIME)) {
              success_cnt = 0;  // 연속 회복 초기화
              HEALTH -= attacks_list[TIME];  // 데미지를 입는다
          } else { 
              // 현재 체력이 최대 체력보다 낮을 때만, 붕대를 감는다
              if (HEALTH < health) {
                  success_cnt += 1;  // 붕대감기 시전 시간 증가 
                  heal(bandage[1], health); 
                  
                  if (isCompleteBandage(bandage[0])) {
                      heal(bandage[2], health); 
                      success_cnt = 0; 
                  }
              }
          }
      } 
      
      int answer = HEALTH > 0 ? HEALTH : -1;
      return answer;
  }
  
  public void attacked(int damage) {
      HEALTH += damage; 
  }
  
  public boolean isCompleteBandage(int needTime) {
      return success_cnt == needTime;
  }
  
  public void heal(int x, int maxHealth) {
      if (HEALTH + x <= maxHealth) {
          HEALTH += x; 
      } else {
          HEALTH = maxHealth; 
      }
  }

  public boolean isDead() {
      return HEALTH <= 0; 
  }
      
  public boolean isAttacked(int time) {
      return attacks_list[time] > 0; 
  }
}
