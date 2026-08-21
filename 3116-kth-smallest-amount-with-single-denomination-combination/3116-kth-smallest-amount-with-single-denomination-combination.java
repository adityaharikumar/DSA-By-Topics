import java.util.*;

class Solution {

    public long findKthSmallest(int[] coins, int k) {

        // Remove redundant coins
        Arrays.sort(coins);

        ArrayList<Integer> useful = new ArrayList<>();

        for (int coin : coins) {
            boolean redundant = false;

            for (int x : useful) {
                if (coin % x == 0) {
                    redundant = true;
                    break;
                }
            }

            if (!redundant) {
                useful.add(coin);
            }
        }

        int n = useful.size();

        long low = 1;
        long high = (long) useful.get(0) * k;

        while (low < high) {

            long mid = low + (high - low) / 2;

            long count = countNumbers(mid, useful);

            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long countNumbers(long x, ArrayList<Integer> coins) {

        int n = coins.size();
        long count = 0;

        // Inclusion-Exclusion
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    long g = gcd(lcm, coins.get(i));

                    // Avoid overflow
                    if (lcm > x / (coins.get(i) / g)) {
                        overflow = true;
                        break;
                    }

                    lcm = lcm / g * coins.get(i);
                }
            }

            if (overflow || lcm > x) {
                continue;
            }

            long current = x / lcm;

            if (bits % 2 == 1) {
                count += current;
            } else {
                count -= current;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}