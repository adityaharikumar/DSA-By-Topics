class Solution {
    public List<Integer> findAnagrams(String s, String p) { 
        List<Integer> result = new ArrayList<>();

        if (p.length() > s.length()) {
            return result;
        }

        int left = 0;
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i = 0; i < p.length(); i++) {
            count1[p.charAt(i) - 'a']++;
        }

        int k = p.length();

        for (int j = 0; j < k; j++) {
            count2[s.charAt(j) - 'a']++;
        }

        if (Arrays.equals(count1, count2)) {
            result.add(0);
        }

        for (int right = k; right < s.length(); right++) {
            count2[s.charAt(right) - 'a']++;

            left = right - k;

            count2[s.charAt(left) - 'a']--;

            if (Arrays.equals(count1, count2)) {
                result.add(left + 1);
            }
        }

        return result;
    }
}