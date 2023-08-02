import java.util.*;
/*
    금액에 대해서 10퍼씩 계속 하면되겠네
    재귀로도 할 수 있겠지만 그냥 while로 편하게 하자
*/
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] result = new int[enroll.length];
        
        for(int i = 0; i < seller.length; i++) {
            int cost = amount[i] * 100;
            String currentSeller = seller[i];
            
            while(!currentSeller.equals("-")) {
                int idx = 0;  
                for(int j = 0; j < enroll.length; j++) {
                    if(currentSeller.equals(enroll[j])) {
                        idx = j;
                        break;
                    }
                }
                
                String parent = referral[idx]; 
                double tenPercent = cost * 0.1; 
                int sellerCost = cost - (int) tenPercent; //가져가는 금액
                result[idx] += sellerCost;
                
                currentSeller = parent;
                cost = (int) tenPercent;
            }
            
        }
        return result;
    }
}
