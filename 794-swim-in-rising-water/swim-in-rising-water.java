class Solution {
    public int swimInWater(int[][] grid) {
        int[][] vis=new int[grid.length][grid[0].length];
        PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->x[0]-y[0]);
        pq.offer(new int[]{grid[0][0],0,0});
        vis[0][0]=0;
        int dx[]={0,0,-1,1};
        int dy[]={1,-1,0,0};
      
        while(!pq.isEmpty())
        {
            // arr[0]=time,arr[1]=maxTime,arr[2]=row,arr[3]=col
            int arr[]=pq.poll();
             vis[arr[1]][arr[2]]=1;
             int time=arr[0];
            if(arr[1]==grid.length-1 && arr[2]==grid[0].length-1) return time;
           
            for(int k=0;k<4;k++)
            {
                int i=dx[k]+arr[1];
                int j=dy[k]+arr[2];
                if(i>=0 && i<grid.length && j>=0 && j<grid[0].length && vis[i][j]==0)
                {
                      int newTime=Math.max(time,grid[i][j]);
                        pq.offer(new int[]{newTime,i,j});
                }
            }

        }

        return 0;
    }
}