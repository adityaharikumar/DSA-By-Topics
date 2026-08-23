class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      
    Stack<Integer> stack = new Stack<>();
      HashMap<Integer,Integer> map = new HashMap<>();
      for(int num:nums2){
        while(!stack.isEmpty() && num>stack.peek()){
            map.put(stack.pop(),num);
        }
        stack.push(num);
      }
      while(!stack.isEmpty()){
        map.put(stack.pop(),-1);
      }
      int n = nums1.length;
      int[] ans = new int[n];

      for(int i=0;i<n;i++){
        ans[i]=map.get(nums1[i]);
      }
      return ans;
    }
}