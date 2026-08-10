class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n+1];
        for(int i=0;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                int remian = i-j*j;
                if(!dp[remian]){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }
}