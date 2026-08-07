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
    public int makeConnected(int n, int[][] connections) {
         int count=0;
        
         int[] visited=new int[n];
         DisjointSet dj=new DisjointSet(n);
         if(connections.length<n-1) return -1;
        for(int i=0;i<connections.length;i++)
        {
            
            int u=connections[i][0];
            int v=connections[i][1];
            if(!dj.find(u,v))
            {
                dj.unionBySize(u,v);
            }
            

        }

        for(int i=0;i<n;i++)
        {
            if(dj.findParent(i)==i)
            {
                count++;
            }
        }
       
       
        
       


      return count-1;
    
 
       
    }
}