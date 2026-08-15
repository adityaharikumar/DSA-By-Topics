class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long sum = 0;
        long MOD=1_000_000_007;
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<=n;i++){
            int current = (i==n)?0:arr[i];
            while(!st.isEmpty() && arr[st.peek()]>current){
                int mid = st.pop();
                int left = st.isEmpty()?-1:st.peek();
                int right =i;
                long leftCount = mid-left;
                long rightCount = right-mid;
                sum+=(long)arr[mid]*leftCount*rightCount;
                sum%=MOD;
            }
            if(i<n){
                st.push(i);
            }
            
        }
        return (int)sum;
    }
}