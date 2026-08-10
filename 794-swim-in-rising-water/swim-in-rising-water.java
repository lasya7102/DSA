class Solution {
    public int swimInWater(int[][] grid) {
        int[][] vis=new int[grid.length][grid[0].length];
        PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->x[0]-y[0]);
        pq.offer(new int[]{vis[0][0],vis[0][0],0,0});
        vis[0][0]=0;
        int dx[]={0,0,-1,1};
        int dy[]={1,-1,0,0};
        int max=0;
        while(!pq.isEmpty())
        {
            // arr[0]=time,arr[1]=maxTime,arr[2]=row,arr[3]=col
            int arr[]=pq.poll();
            if(arr[2]==grid.length-1 && arr[3]==grid[0].length-1) return arr[1];
            if(grid[arr[2]][arr[3]]>max) max=grid[arr[2]][arr[3]];
            for(int k=0;k<4;k++)
            {
                int i=dx[k]+arr[2];
                int j=dy[k]+arr[3];
                if(i>=0 && i<grid.length && j>=0 && j<grid[0].length && vis[i][j]==0)
                {
                      
                       pq.offer(new int[]{grid[i][j],Math.max(grid[i][j],max),i,j});
                       vis[i][j]=1;
                     
                }
            }

        }

        return 0;
    }
}