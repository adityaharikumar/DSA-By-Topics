class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        // Count frequency of characters in s
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check if palindrome is possible
        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Frequency of characters in the left half
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        int halfLength = n / 2;

        // Target's left half
        char[] targetHalf = target.substring(0, halfLength).toCharArray();

        int[] remaining = halfFreq.clone();

        // Stores the latest position where we can make
        // the left half strictly greater than target's left half
        int lastPosition = -1;
        int[] lastRemaining = null;

        boolean exactPrefixPossible = true;

        for (int i = 0; i < halfLength; i++) {

            int current = targetHalf[i] - 'a';

            // Before matching target[i], check whether
            // a greater character is available here
            if (hasGreater(remaining, current)) {
                lastPosition = i;
                lastRemaining = remaining.clone();
            }

            // Try to match target character
            if (remaining[current] == 0) {
                exactPrefixPossible = false;
                break;
            }

            remaining[current]--;
        }

        // Case 1: We can make exactly the same left half
        if (exactPrefixPossible) {
            char[] left = targetHalf.clone();

            String palindrome = buildPalindrome(left, middle, n);

            // If this palindrome itself is greater
            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        // Case 2: Make the left half slightly greater
        if (lastPosition == -1) {
            return "";
        }

        char[] answerLeft = new char[halfLength];

        // Copy prefix from target
        for (int i = 0; i < lastPosition; i++) {
            answerLeft[i] = targetHalf[i];
        }

        // Find the smallest character greater than target[lastPosition]
        int current = targetHalf[lastPosition] - 'a';

        int nextChar = current + 1;

        while (nextChar < 26 && lastRemaining[nextChar] == 0) {
            nextChar++;
        }

        if (nextChar == 26) {
            return "";
        }

        answerLeft[lastPosition] = (char) ('a' + nextChar);
        lastRemaining[nextChar]--;

        // Fill remaining positions in sorted order
        int index = lastPosition + 1;

        for (int i = 0; i < 26; i++) {
            while (lastRemaining[i] > 0) {
                answerLeft[index++] = (char) ('a' + i);
                lastRemaining[i]--;
            }
        }

        return buildPalindrome(answerLeft, middle, n);
    }

    private boolean hasGreater(int[] freq, int current) {
        for (int i = current + 1; i < 26; i++) {
            if (freq[i] > 0) {
                return true;
            }
        }

        return false;
    }

    private String buildPalindrome(char[] left, char middle, int n) {
        StringBuilder sb = new StringBuilder();

        // Left half
        for (char c : left) {
            sb.append(c);
        }

        // Middle character
        if (n % 2 == 1) {
            sb.append(middle);
        }

        // Reverse of left half
        for (int i = left.length - 1; i >= 0; i--) {
            sb.append(left[i]);
        }

        return sb.toString();
    }
}