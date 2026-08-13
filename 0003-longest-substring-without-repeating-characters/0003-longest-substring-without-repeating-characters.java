class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] freq = new int[128];

        int left = 0;
        int maxlen = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            freq[ch]++;

            while (freq[ch] > 1) {

                freq[s.charAt(left)]--;

                left++;
            }

            maxlen = Math.max(maxlen, right - left + 1);
        }

        return maxlen;
    }
}