class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        char min = s.charAt(0);
        char max = s.charAt(0);

        for (int i = 1; i < n; i++) {
            char ch = s.charAt(i);

            if (s.charAt(i - 1) > ch) {
                sorted = false;
            }

            min = (char) Math.min(min, ch);
            max = (char) Math.max(max, ch);
        }

        if (sorted) {
            return 0;
        }

        // With two characters, only single-character substrings are allowed.
        if (n == 2) {
            return -1;
        }

        // Sort everything except the already-correct endpoint.
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // An interior minimum or maximum lets us finish in two operations.
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == min || s.charAt(i) == max) {
                return 2;
            }
        }

        return 3;
    }
}