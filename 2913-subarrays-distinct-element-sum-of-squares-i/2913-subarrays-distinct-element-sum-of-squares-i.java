import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int total = 0;

        for (int i = 0; i < nums.size(); i++) {
            Set<Integer> distinct = new HashSet<>();

            for (int j = i; j < nums.size(); j++) {
                distinct.add(nums.get(j));

                int count = distinct.size();
                total += count * count;
            }
        }

        return total;
    }
}