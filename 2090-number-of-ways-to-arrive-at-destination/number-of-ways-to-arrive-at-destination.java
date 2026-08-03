class Solution {
    public int countPaths(int n, int[][] roads) {
        PriorityQueue<long[]> pq=new PriorityQueue<>((x,y)->Long.compare(x[1], y[1]));
        ArrayList<ArrayList<int[]>> list=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            list.add(new ArrayList<>());
        }
       long dist[] = new long[n];
Arrays.fill(dist, Long.MAX_VALUE);
int MOD = 1000000007;


        int ways[]=new int[n];
        ways[0]=1;
        for(int i=0;i<roads.length;i++)
        {
            int u=roads[i][0];
            int v=roads[i][1];
            int dis=roads[i][2];
            list.get(u).add(new int[]{v,dis});
            list.get(v).add(new int[]{u,dis});
        }
        pq.offer(new long[]{0,0});
        dist[0]=0;
   
        while(!pq.isEmpty())
        {
            long arr[]=pq.poll();// arr[0] is edge and arr[1]  is distance
            for(int ch[]:list.get((int) arr[0]))
            {
               
               if(arr[1]+ch[1]<dist[ch[0]])
               {
                  dist[ch[0]]=arr[1]+ch[1];
                   ways[ch[0]] = ways[(int) arr[0]];
                  pq.offer(new long[]{ch[0],dist[ch[0]]});
               }
                else if (arr[1]+ch[1]==dist[ch[0]]) {
  ways[ch[0]] = (ways[ch[0]] + ways[(int) arr[0]]) % MOD;
}
                }
            }
        
     
     return ways[n-1];
    }
}