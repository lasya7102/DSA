class Pair
{
 int edge,dis;
 Pair(int edge,int dis)
 {
    this.edge=edge;
    this.dis=dis;
 }
}
class Solution {
   int result = 0;
int reach = Integer.MAX_VALUE;
     void dijsktras(ArrayList<ArrayList<Pair>> list,int src,int k)
    {
      int dist[]=new int[list.size()];
      for(int i=0;i<dist.length;i++)
      {
        dist[i]=Integer.MAX_VALUE;
      }
      dist[src]=0;
      PriorityQueue<Pair> pq=new PriorityQueue<>((x,y)->x.dis-y.dis);
      pq.offer(new Pair(src,0));
      while(!pq.isEmpty())
      {
       Pair p=pq.poll();
      

if (p.dis > dist[p.edge])
    continue;
        for(Pair e:list.get(p.edge))
        {
            if(e.dis+p.dis<dist[e.edge])
            {
                dist[e.edge]=e.dis+p.dis;
                pq.offer(new Pair(e.edge,dist[e.edge]));
            }
        }
      }
      int count=0;
     for (int i = 0; i < dist.length; i++) {
    if (i != src && dist[i] <= k)
        count++;

}
     if(count<=reach) 
     {
        reach=count;
        result=src;
     }
    }
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        ArrayList<ArrayList<Pair>> list=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            list.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++)
        {
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];
            list.get(u).add(new Pair(v,w));
            list.get(v).add(new Pair(u,w));
        }
        int reach=n;
        for(int i=0;i<n;i++)
        {
            dijsktras(list,i,distanceThreshold);
        }
       return result;
    }
}