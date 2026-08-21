class Solution {
    public int leastInterval(char[] tasks, int n) {

        // 1. Count frequency of each task
        int[] freq = new int[26];

        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // 2. Max heap
        PriorityQueue<Integer> pq =
            new PriorityQueue<>(Collections.reverseOrder());

        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }

        // 3. Queue: [remainingCount, availableTime]
        Queue<int[]> queue = new LinkedList<>();

        int time = 0;

        // Continue while tasks remain
        while (!pq.isEmpty() || !queue.isEmpty()) {

            time++;

            // Move cooled-down tasks back to heap
            while (!queue.isEmpty() && queue.peek()[1] <= time) {
                pq.offer(queue.poll()[0]);
            }

            // Execute a task
            if (!pq.isEmpty()) {

                int count = pq.poll();

                count--;

                // Task still has remaining copies
                if (count > 0) {
                    queue.offer(new int[] {
                        count,
                        time + n + 1
                    });
                }
            }
        }

        return time;
    }
}