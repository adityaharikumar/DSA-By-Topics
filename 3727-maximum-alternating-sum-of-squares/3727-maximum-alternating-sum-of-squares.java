import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            squares[i] = 1L * nums[i] * nums[i];
        }

        Arrays.sort(squares);

        int plusCount = (n + 1) / 2;
        long answer = 0;

        for (int i = 0; i < n; i++) {
            if (i >= n - plusCount) {
                answer += squares[i];
            } else {
                answer -= squares[i];
            }
        }

        return answer;
    }
}