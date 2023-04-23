import java.util.ArrayList;

class Solution {
  static char [] operation = {'+', '-', '*'}, order;
  static boolean [] isSelected;
  static ArrayList<Long> nums;
  static ArrayList<Character> opers;
  static long result;

  public long solution(String expression) {
    // public static void main(String[] args) {
      isSelected = new boolean [3];
      order = new char [3];
      nums = new ArrayList<>();
      opers = new ArrayList<>();

      String tempNum = "";
      for(int i = 0; i < expression.length(); i++) {
        char now = expression.charAt(i);
        if(now >= '0' && now <= '9') {
          tempNum += now;
        } else {
          // 연산자일때 숫자 끊어주고
          nums.add(Long.parseLong(tempNum));
          // 초기화
          tempNum = "";
          // 연산 저장
          opers.add(now);
        }
      }

      nums.add(Long.parseLong(tempNum));
      // for(Long l : nums) {
      //   System.out.print(l + " ");
      // }
      // for(Character l : opers) {
      //   System.out.print(l + " ");
      // }
      perm(0);

      return result;
      // System.out.println(result);
  }

  private static void perm(int count) {
    if(count == 3) {
      long maxNum = sol();
      if(result < maxNum) result = maxNum;
      return;
    }

    for(int i = 0; i < 3; i++) {
      if(isSelected[i]) continue;

      order[count] = operation[i];
      isSelected[i] = true;
      perm(count + 1);
      isSelected[i] = false;
    }
  }

  private static long sol() {
    ArrayList<Long> numsCopy = new ArrayList<>(nums);
    ArrayList<Character> opersCopy = new ArrayList<>(opers);

    for(char priority : order) {
      for(int i = 0; i < opersCopy.size(); i++) {
        if(priority == opersCopy.get(i)) {
          Long afterOper = calc(numsCopy.remove(i), numsCopy.remove(i), priority);
          // 연산끝나면 다시 삽입
          numsCopy.add(i, afterOper);
          opersCopy.remove(i);
          i--;
        }
      }
    }

    return Math.abs(numsCopy.get(0));
  }

  private static long calc(Long a, Long b, char oper) {
    if(oper == '+') return a + b;
    if(oper == '-') return a - b;
    if(oper == '*') return a * b;
    return -1;
  }
}