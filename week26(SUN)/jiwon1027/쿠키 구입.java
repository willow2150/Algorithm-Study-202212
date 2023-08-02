/*
    두 아들이 받는 과자는 같아야함
    왼쪽 오른쪽 개수 비교하면서 구현하면 될 듯?
*/
class Solution {
    
public int solution(int[] cookie) {
        int leftSum, leftIdx;
        int rightSum, rightIdx;
        int answer = 0;
        
        for(int i = 0; i < cookie.length-1; i++) {   
            leftSum =cookie[i];
            leftIdx = i;
            rightSum =cookie[i+1];
            rightIdx = i+1; 
            while(true) {
                if(leftSum == rightSum && answer < leftSum) {
                    answer = leftSum;
                }
                if(leftIdx > 0 && leftSum <= rightSum) {
                    leftIdx--;
                    leftSum += cookie[leftIdx];
                }else if(rightIdx < cookie.length-1 && leftSum >= rightSum) {
                    rightIdx++;
                    rightSum += cookie[rightIdx];
                }else {
                    break;
                }
            }
        }
        return answer;
    }
}