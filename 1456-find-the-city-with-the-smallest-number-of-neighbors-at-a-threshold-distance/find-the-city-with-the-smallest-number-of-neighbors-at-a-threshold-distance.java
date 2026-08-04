class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int dist[][]=new int[n][n];
        int INF = 100000000;

for (int i = 0; i < n; i++) {
    Arrays.fill(dist[i], INF);
    dist[i][i] = 0;
}
        for(int i=0;i<edges.length;i++)
        {
            int u=edges[i][0];
            int v=edges[i][1];
            int dis=edges[i][2];
            dist[u][v]=dis;
            dist[v][u]=dis;
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                for(int k=0;k<n;k++)
                {
                    if(dist[j][i]!=INF && dist[i][k]!=INF && dist[j][i]+dist[i][k]<dist[j][k])
                    {
                        dist[j][k]=dist[j][i]+dist[i][k];
                    }
                }
            }
        }
       
        int min=Integer.MAX_VALUE;
        int ans=n;
        for(int i=0;i<n;i++)
        {
            int count=0;
            for(int j=0;j<n;j++)
            {
                if(i!=j && dist[i][j]<=distanceThreshold) count++;
            }
           if(count<=min)
           {
            min=count;
            ans=i;
           }
        }
      
        return ans;
    }
}