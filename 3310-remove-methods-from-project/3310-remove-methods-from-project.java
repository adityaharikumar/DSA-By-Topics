class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
       List<List<Integer>> graph = new ArrayList<>();
       for(int i=0;i<n;i++){
        graph.add(new ArrayList<>());
       } 
       for(int[] edge:invocations){
        graph.get(edge[0]).add(edge[1]);
       }
       boolean[] suspicious = new boolean[n];
       Queue<Integer> q = new LinkedList<>();
       q.offer(k);
       suspicious[k]=true;
       while(!q.isEmpty()){
        int curr = q.poll();
        for(int next : graph.get(curr)){
            if(!suspicious[next]){
                suspicious[next]=true;
                q.offer(next);
            }
        }
       }
       for(int[] edge: invocations){
        if(!suspicious[edge[0]] && suspicious[edge[1]]){
            List<Integer> ans = new ArrayList<>();
            for(int i=0;i<n;i++){
               ans.add(i);
               
            }
            return ans;
        }
       }
       List<Integer> ans = new ArrayList<>();
       for(int i=0;i<n;i++){
        if(!suspicious[i]) ans.add(i);
       }
        return ans;
    }
}