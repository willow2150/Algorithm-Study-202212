class Solution {
  public int solution(int[] stones, int k) {
      int answer = 0;
      int low = 1; 
      int high = 200000000;
      
      while (low <= high) {
          int mid = (low + high) / 2;
          
          // 건넜음 => 더 큰 값 찾으러
          if(sol(stones, mid, k)) {
              low = mid + 1;
              answer = Math.max(answer, mid);
          } 
          // 못 건넜음 => 최소값으로 다시
          else {
              high = mid - 1;
          }
          
      }
      
      return answer;
  }
  
  private static boolean sol(int [] stones, int people, int k) {
      int pass = 0;
      
      for(int s : stones) {
          
          // 사람 수 보다 작으면 못 건넘
          if(s < people) {
              pass += 1;
          }
          // 건널 수 있음
          if(s >= people) {
              pass = 0;
          }
          // k 이상은 다리가 짧음
          // 이 순서가 비교 로직 앞으로 가면 70%만 정답이다 왜일까
          if(pass == k) return false;
      }
      
      return true;
  }
}