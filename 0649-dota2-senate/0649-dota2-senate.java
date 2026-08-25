class Solution {
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> rad = new LinkedList<>();
        Queue<Integer> da = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(senate.charAt(i)=='R'){
                rad.offer(i);
            }else{
                da.offer(i);
            }
        }
        while(!rad.isEmpty() && !da.isEmpty()){
            int r = rad.poll();
            int d = da.poll();
            if(r<d){
                rad.offer(r+n);
            }else{
                da.offer(d+n);
            }
        }
        if(rad.isEmpty()){
            return "Dire";
        }else{
            return "Radiant";
        }
    }
}