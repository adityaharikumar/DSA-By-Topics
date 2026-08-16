class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        int[] freq = new int[51];
        for(int i=0;i<k;i++){
            if(nums[i]<0){
                freq[-nums[i]]++;
            }
        }
        ans[0]=find(freq,x);
        for(int right=k;right<n;right++){
            int left = right-k;
            if(nums[left]<0){
                freq[-nums[left]]--;
            }
            if(nums[right]<0){
                freq[-nums[right]]++;
            }
            ans[right-k+1]=find(freq,x);
        }
        return ans;
    }
    private int find(int[] freq,int x){
        int count=0;
        for(int i=50;i>=1;i--){
            count+=freq[i];
            if(count>=x){
                return -i;
            }
        }
        return 0;
    }
}