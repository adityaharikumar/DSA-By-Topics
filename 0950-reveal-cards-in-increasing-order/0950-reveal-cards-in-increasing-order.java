class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.sort(deck);
        for(int i=0;i<deck.length;i++){
            queue.offer(i);
        }
        int[] result  = new int[deck.length];
        int i=0;
        while(!queue.isEmpty()){
            int index = queue.poll();
            result[index]=deck[i];
            i++;
            if(!queue.isEmpty()){
                queue.offer(queue.poll());
            }
        }
        return result;
    }
}