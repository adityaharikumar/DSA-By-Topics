class Solution {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int second = Integer.MIN_VALUE;
        for(int i=nums.length-1;i>=0;i--){
            while(!stack.isEmpty() && stack.peek()<nums[i]){
                second = stack.pop();
            }
            if(nums[i]<second){
                return true;
            }
            stack.push(nums[i]);
        }
        return false;
    }
}