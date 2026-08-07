class DisjointSet {
     int n;
      int[] rank;
      int[] parent;
       int[] size;
      
             
    public DisjointSet(int n) {
        this.n=n;
         rank=new int[n];
          parent=new int[n];
          size=new int[n];
        for(int i=0;i<n;i++)
        {
            rank[i]=0;
            size[i]=1;
            parent[i]=i;
          

        }
    }

   public int findParent(int x)
   {
    if(parent[x]==x)
    {
        return x;
    }
     parent[x]=findParent(parent[x]);
     return parent[x];
   }
    public boolean find(int u, int v) {
       if(findParent(u)==findParent(v))
       {
        return true;
       }
       return false;
    }

    public void unionByRank(int u, int v) {
       int p1=findParent(u);
       int p2=findParent(v);
       if(p1==p2) return;
       if(rank[p1]<rank[p2])
       {
         parent[p1]=p2;
       }
       else if(rank[p1] > rank[p2])
       {
        parent[p2]=p1;
       }
       else{
         parent[p1]=p2;
         rank[p2]++;
       }
    }

    public void unionBySize(int u, int v) {
        int p1=findParent(u);
        int p2=findParent(v);
        if(p1 == p2) return;
        if(size[p1]<=size[p2])
        {
            parent[p1]=p2;
            size[p2]+=size[p1];
        }
        else{
            parent[p2]=p1;
            size[p1]+=size[p2];
        }
    }
}





class Solution {
    public int removeStones(int[][] stones) {
        int maxRow=-1;
        int maxCol=-1;
        for(int i=0;i<stones.length;i++)
        {
            maxRow = Math.max(maxRow, stones[i][0]);
maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet dj=new DisjointSet(maxRow+maxCol+2);
        HashSet<Integer> list = new HashSet<>();
         for(int i=0;i<stones.length;i++)
        {
          int u=stones[i][0];
          int v=stones[i][1];
          dj.unionBySize(u,v+maxRow+1);
        list.add(u);
        list.add(v+maxRow+1);
        }
        int count=0;
        for(int ch:list)
        {
            if(dj.parent[ch]==ch)
            {
                count++;
            }
        }
      return stones.length-count;
    }
}