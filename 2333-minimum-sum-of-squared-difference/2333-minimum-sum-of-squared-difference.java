class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {
        long operations = (long) k1 + k2;
        long totalDifference = 0;
        int maxDifference = 0;

        for (int i = 0; i < nums1.length; i++) {
            int difference = Math.abs(nums1[i] - nums2[i]);

            totalDifference += difference;
            maxDifference = Math.max(maxDifference, difference);
        }

        if (operations >= totalDifference) {
            return 0;
        }

        int left = 0;
        int right = maxDifference;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long required = 0;

            for (int i = 0; i < nums1.length; i++) {
                int difference = Math.abs(nums1[i] - nums2[i]);
                required += Math.max(0, difference - mid);
            }

            if (required <= operations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int limit = left;
        long remaining = operations;
        long answer = 0;

        for (int i = 0; i < nums1.length; i++) {
            int difference = Math.abs(nums1[i] - nums2[i]);

            remaining -= Math.max(0, difference - limit);

            long reduced = Math.min(difference, limit);
            answer += reduced * reduced;
        }

        return answer - remaining * (2L * limit - 1);
    }
}