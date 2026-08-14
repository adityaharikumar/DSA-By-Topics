import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Count characters in s1
        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
        }

        int k = s1.length();

        // Create first window in s2
        for (int j = 0; j < k; j++) {
            count2[s2.charAt(j) - 'a']++;
        }

        // Check first window
        if (Arrays.equals(count1, count2)) {
            return true;
        }

        // Sliding window
        for (int right = k; right < s2.length(); right++) {

            // Add new character
            count2[s2.charAt(right) - 'a']++;

            // Remove old character
            int left = right - k;
            count2[s2.charAt(left) - 'a']--;

            // Check current window
            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }

        return false;
    }
}