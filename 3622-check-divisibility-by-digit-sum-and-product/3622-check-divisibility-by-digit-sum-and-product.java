class Solution {
    public boolean checkDivisibility(int n) {
      int product=1;
      int sum=0;
      int org=n;
      while(n>0){
        int digit=n%10;
        sum+=digit;
        product*=digit;
        n/=10;
      } 
      return org%(product+sum)==0; 
    }
}