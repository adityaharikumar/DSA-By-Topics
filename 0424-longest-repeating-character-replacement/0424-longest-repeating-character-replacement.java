class Solution {
    public int characterReplacement(String s, int k) {
    int[] freq = new int[26];
    int left=0;
    int maxlen=0;
    int maxfreq=0;
    for(int right=0;right<s.length();right++){
        int index = s.charAt(right)-'A';
        freq[index]++;
        maxfreq = Math.max(maxfreq,freq[index]);
        int replace = (right-left+1)-maxfreq;
        if(replace>k){
            freq[s.charAt(left)-'A']--;
            left++;
        }
        maxlen = Math.max(maxlen,right-left+1);  
    }
      return maxlen;
    }
}