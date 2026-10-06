class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int insertions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (open > 0) {
                open--;
            } else {
                // No unmatched '(' to pair with this ')'
                insertions++;
            }
        }

        // Each remaining '(' needs a ')'
        return insertions + open;
    }
}