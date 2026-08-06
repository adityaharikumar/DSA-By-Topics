class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            String s = String.valueOf(n);
            int product=1;
            for(char ch:s.toCharArray()){
                product*=(ch-'0');
                if(product%t==0){
                    return n;
                }
            
            }
            n++;
        }
    }
}