class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char task:tasks){
            freq[task-'A']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int f:freq){
            if(f>0){
                pq.offer(f);
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        int time = 0;
        while(!pq.isEmpty()|| !queue.isEmpty()){
            time++;
            while(!queue.isEmpty() && queue.peek()[1]==time){
                pq.offer(queue.poll()[0]);
            }
            if(!pq.isEmpty()){
                int count = pq.poll();
                count--;
                if(count>0){
                    queue.offer(new int[]{
                        count,time+n+1
                    });
                }
            }
        }
        return time;
    }
}