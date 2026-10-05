import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int insideScore = stack.pop();
                int currentScore = Math.max(2 * insideScore, 1);

                int outerScore = stack.pop();
                stack.push(outerScore + currentScore);
            }
        }

        return stack.pop();
    }
}