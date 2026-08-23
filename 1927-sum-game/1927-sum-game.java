class Solution {
    public boolean sumGame(String num) {

        int leftsum = 0;
        int rightsum = 0;
        int leftQ = 0;
        int rightQ = 0;

        int n = num.length();

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQ++;
            } else {
                leftsum += c - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                rightQ++;
            } else {
                rightsum += c - '0';
            }
        }

        return (leftsum - rightsum) !=
               9.0 * (rightQ - leftQ) / 2;
    }
}