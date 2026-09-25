import java.util.*;

class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        return new ArrayList<>(new TreeSet<>(result));
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();

        while (i < s.length() && s.charAt(i) != '}') {

            if (s.charAt(i) == '{') {
                i++;

                Set<String> inside = new HashSet<>();

                while (true) {
                    Set<String> part = parse(s);
                    inside.addAll(part);

                    if (s.charAt(i) == ',') {
                        i++;
                    } else {
                        break;
                    }
                }

                i++;
                result = combine(result, inside);

            } else {
                String ch = String.valueOf(s.charAt(i));
                i++;

                Set<String> single = new HashSet<>();
                single.add(ch);

                result = combine(result, single);
            }

            if (i < s.length() && s.charAt(i) == ',') {
                break;
            }
        }

        return result;
    }

    private Set<String> combine(Set<String> a, Set<String> b) {
        if (a.isEmpty()) {
            return new HashSet<>(b);
        }

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}