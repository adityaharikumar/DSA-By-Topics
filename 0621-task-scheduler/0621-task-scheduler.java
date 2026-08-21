class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char task:tasks){
            freq[task-'A']++;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for(int f:freq){
            if(f>0){
                heap.offer(f);
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        int time=0;
        while(!heap.isEmpty() || !queue.isEmpty()){
            time++;
            while(!queue.isEmpty() && queue.peek()[1]<=time){
                heap.offer(queue.poll()[0]);
            }
            if(!heap.isEmpty()){
                int count = heap.poll();
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