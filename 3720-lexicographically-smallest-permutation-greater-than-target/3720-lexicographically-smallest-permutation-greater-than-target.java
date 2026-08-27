class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder prefix = new StringBuilder();
        int n = s.length();
        int i;

        // Match target as long as possible
        for (i = 0; i < n; i++) {
            int curr = target.charAt(i) - 'a';

            if (freq[curr] > 0) {
                freq[curr]--;
                prefix.append(target.charAt(i));
            } else {
                break;
            }
        }

        // Try making position i greater than target[i]
        if (i < n) {
            String result = buildGreater(prefix, freq, target.charAt(i));
            if (!result.equals("")) {
                return result;
            }
        }

        // Backtrack and try making an earlier position greater
        for (i = prefix.length() - 1; i >= 0; i--) {

            char current = prefix.charAt(i);
            freq[current - 'a']++;

            prefix.setLength(i);

            String result = buildGreater(prefix, freq, target.charAt(i));

            if (!result.equals("")) {
                return result;
            }
        }

        return "";
    }

    private String buildGreater(StringBuilder prefix, int[] freq, char targetChar) {
        // Find smallest available character greater than targetChar
        for (int c = targetChar - 'a' + 1; c < 26; c++) {
            if (freq[c] > 0) {
                StringBuilder ans = new StringBuilder(prefix);

                ans.append((char) ('a' + c));
                freq[c]--;

                // Add remaining characters in sorted order
                for (int j = 0; j < 26; j++) {
                    while (freq[j] > 0) {
                        ans.append((char) ('a' + j));
                        freq[j]--;
                    }
                }

                return ans.toString();
            }
        }

        return "";
    }
}