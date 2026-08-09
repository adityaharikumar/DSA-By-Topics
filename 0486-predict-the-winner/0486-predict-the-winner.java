class Solution {
    int[][] dp;
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new int[n][n+1];
        return solve(nums,0,n-1)>=0;
    }

    public int solve(int[] nums,int left,int right){
        if(left==right){
            return nums[left];
        }
        if(dp[left][right]!=0){
            return dp[left][right];
        }
        int leftt = nums[left]-solve(nums,left+1,right);
        int rightt = nums[right]-solve(nums,left,right-1);
        dp[left][right]=Math.max(leftt,rightt);
        return dp[left][right];
    }
}