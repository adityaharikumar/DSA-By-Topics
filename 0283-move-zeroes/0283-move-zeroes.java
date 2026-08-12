class Solution {
    public void moveZeroes(int[] nums) {
        int insertPos = 0;
        for(int right=0;right<nums.length;right++){
            
            if(nums[right]!=0){
                int temp = nums[right];
                nums[right]=nums[insertPos];
                nums[insertPos]=temp;
                insertPos++;
            }
        }
    }
}