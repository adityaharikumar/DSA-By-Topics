class Solution {
    public String predictPartyVictory(String senate) {
      Queue<Integer> rad = new LinkedList<>();
      Queue<Integer> di = new LinkedList<>();

      int n = senate.length();
      for(int i=0;i<n;i++){
        if(senate.charAt(i)=='R'){
            rad.add(i);
        }else{
            di.add(i);
        }
      }
      while(!rad.isEmpty() && !di.isEmpty()){
        int r = rad.poll();
        int d = di.poll();
        if(r<d){
            rad.add(n+r);
        }else{
            di.add(n+d);
        }
      }
      if(!rad.isEmpty()){
        return "Radiant";
      }else{
        return "Dire";
      }

    }
}