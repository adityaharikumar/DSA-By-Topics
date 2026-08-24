class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result,-1);
        for(int i=0;i<2*n;i++){
            int index = i%n;
            while(!stack.isEmpty() && nums[stack.peek()]<nums[index]){
                result[stack.pop()]=nums[index];
            }
            if(i<n){
                stack.push(index);
            }
        }
        return result;
    }
} 