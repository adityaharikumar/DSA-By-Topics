class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
         HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        for(int right=0;right<nums.length;right++){
            sum+=nums[right];
            int remainder = sum%k;
            if(map.containsKey(remainder)){
                if(right-map.get(remainder)>=2){
                    return true;
                }
            }else{
                map.put(remainder,right);
            }
        }
        return false;
    }
}