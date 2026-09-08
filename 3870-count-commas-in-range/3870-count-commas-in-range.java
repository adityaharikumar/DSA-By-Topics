class Solution {
    public int countCommas(int n) {
        int ans = 0;
        for(int p=1000;p<=n;p*=1000){
            ans+=(n-p+1);
        }
        return ans;
    }
}