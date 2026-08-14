class Solution {
    public int longestOnes(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int left=0;
        int max = 0;
        int zeros=0;

        for(int right=0;right<nums.length;right++){
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            
            while(nums[right]==0 && map.get(nums[right])>k){
                map.put(nums[left],map.get(nums[left])-1);
                left++;
            }
            max=Math.max(max,right-left+1);
        }
        return max;
    }
}