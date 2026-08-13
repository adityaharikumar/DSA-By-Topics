class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int right=1;right<nums.length;right++){
            if(nums[right]==nums[right-1]){
                return nums[right];
            }
        }
        return nums[0];
    }
}