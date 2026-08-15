class Solution {
    public int maxWidthRamp(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int max=0;
        for(int i=0;i<nums.length;i++){
            if(st.isEmpty() || nums[i]<nums[st.peek()]){
                st.push(i);
            }
        }
        for(int j=nums.length-1;j>=0;j--){
            while(!st.isEmpty() && nums[st.peek()]<=nums[j]){
                max=Math.max(max,j-st.pop());
            }
        }
        return max;
    }
}