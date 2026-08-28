class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
       int sum=0;
       int maxLen=0;
       for(int right=0;right<nums.length;right++){
        if(nums[right]==1){
            sum++;
        }else{
            sum--;
        }
        if(map.containsKey(sum)){
            maxLen=Math.max(maxLen,right-map.get(sum));
        }else{
            map.put(sum,right);
        }
       } 
       return maxLen;
    }
}