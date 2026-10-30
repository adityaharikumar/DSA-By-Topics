import java.util.*;

class Solution {
    private int[][] up;
    private int[] depth, tin, tout, bit;
    private int log, n;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        log = 1;
        while ((1L << log) <= n) log++;

        up = new int[log][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        bit = new int[n + 1];

        int[] parent = new int[n];
        int[] order = new int[n];
        int[] size = new int[n];
        Arrays.fill(size, 1);

        int[] stack = new int[n];
        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = ++timer;
            order[timer - 1] = u;
            up[0][u] = parent[u];

            for (int v : graph.get(u)) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            tout[u] = tin[u] + size[u] - 1;

            if (u != 0) {
                size[parent[u]] += size[u];
            }
        }

        for (int j = 1; j < log; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }

        char[] labels = s.toCharArray();

        for (int u = 0; u < n; u++) {
            rangeXor(tin[u], tout[u], 1 << (labels[u] - 'a'));
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split("\\s+");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);

                int change = (1 << (labels[u] - 'a'))
                           ^ (1 << (c - 'a'));

                rangeXor(tin[u], tout[u], change);
                labels[u] = c;

            } else {
                int v = Integer.parseInt(parts[2]);
                int ancestor = lca(u, v);

                int mask = prefixXor(tin[u])
                         ^ prefixXor(tin[v])
                         ^ (1 << (labels[ancestor] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int difference = depth[u] - depth[v];

        for (int j = 0; j < log; j++) {
            if (((difference >> j) & 1) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = log - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    private void add(int index, int value) {
        while (index <= n) {
            bit[index] ^= value;
            index += index & -index;
        }
    }

    private void rangeXor(int left, int right, int value) {
        add(left, value);
        add(right + 1, value);
    }

    private int prefixXor(int index) {
        int result = 0;

        while (index > 0) {
            result ^= bit[index];
            index -= index & -index;
        }

        return result;
    }
}