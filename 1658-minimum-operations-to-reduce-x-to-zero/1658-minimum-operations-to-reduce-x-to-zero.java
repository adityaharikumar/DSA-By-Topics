class Solution {
    public int minOperations(int[] nums, int x) {
        int count =0;
        int sum=0;
        for(int num:nums){
            sum+=num;
        }
        int target = sum-x;
        int left=0;
        int currentSum=0;
        int maxLen=-1;
        for(int right=0;right<nums.length;right++){
            currentSum+=nums[right];
            while(currentSum>target && left<=right){
                currentSum-=nums[left];
                left++;
            }
            if(currentSum==target){
                maxLen=Math.max(maxLen,right-left+1);
            }
        }
        return maxLen==-1?-1:nums.length-maxLen;
    }
}