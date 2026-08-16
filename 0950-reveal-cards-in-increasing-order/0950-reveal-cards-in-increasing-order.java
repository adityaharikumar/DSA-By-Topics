class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Arrays.sort(deck);

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            queue.add(i);
        }
        int[] ans = new int[n];
        for(int card:deck){
            int index=queue.poll();
            ans[index]=card;
            if(!queue.isEmpty()){
                queue.add(queue.poll());
            }
        }
        return ans;
    }
}