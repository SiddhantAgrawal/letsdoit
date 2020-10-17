// https://leetcode.com/problems/number-of-good-pairs/
class Solution {
    public int numIdenticalPairs(int[] nums) {
        
        int length = nums.length;
        int count = 0;
        for (int i = 0; i < length - 1; i++) {
            
            for (int j = i + 1; j < length; j++) {
                
                if (nums[i] == nums[j]) {
                    count++;
                }
                
            }
            
        }
        
        return count;
        
    }
}
