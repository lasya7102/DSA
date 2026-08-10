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
    public int largestIsland(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int max=0;
        int dx[]={0,0,-1,1};
        int dy[]={1,-1,0,0};
        DisjointSet dj=new DisjointSet(n*m +1);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j]==1)
                {
                int node= i*m + j;
                for(int k=0;k<4;k++)
                {
                   
                    int x=dx[k]+i;
                    int y=dy[k]+j;
                      if(x>=0 && x<n && y>=0 && y<m && grid[x][y]==1)
                    {
                    int adjNode= x*m + y;
                    if(!dj.find(node,adjNode))
                    {
                        dj.unionBySize(node,adjNode);
                    }
                    }
                }
                }
            }
        }

 for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j]==0)
                {
                    // int node= i*m + j;
                     HashSet<Integer> set=new HashSet<>();
                for(int k=0;k<4;k++)
                {
                 
                    int x=dx[k]+i;
                    int y=dy[k]+j;
                    if(x>=0 && x<n && y>=0 && y<m && grid[x][y]==1)
                    {
                    int adjNode= x*m + y;
                    set.add(dj.findParent(adjNode));
                    }
                }
                int sum=0;
                for(int ch:set)
                {
                   sum+=dj.size[ch];
                }
                max=Math.max(max,sum+1);

                }
            }
        }

         for (int i = 0; i < n * m; i++) {
            if (dj.findParent(i) == i) {
                max = Math.max(max, dj.size[i]);
            }
        }
     return max;
    }
}