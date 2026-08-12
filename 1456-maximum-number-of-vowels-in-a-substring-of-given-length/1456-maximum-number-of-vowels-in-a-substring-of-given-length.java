class Solution {
    public int maxVowels(String s, int k) {
        int curr = 0;
        for(int i=0;i<k;i++){
            if(isVowel(s.charAt(i))){
                curr++;
            }
        }
        int max = curr;
        for(int right=k;right<s.length();right++){
            if(isVowel(s.charAt(right))){
                curr++;
            }
            if(isVowel(s.charAt(right-k))){
                curr--;
            }
            max=Math.max(max,curr);
        }
        return max;
     
    }
    private boolean isVowel(char c){
         return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}