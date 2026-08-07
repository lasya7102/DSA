class Solution {
    public int makeConnected(int n, int[][] connections) {
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
         int count=0;
        int edges=0;
        for(int i=0;i<n;i++)
        {
            list.add(new ArrayList<>());
        }
         int[] visited=new int[n];
        for(int i=0;i<connections.length;i++)
        {
            
            int u=connections[i][0];
            int v=connections[i][1];
            if(visited[u]==1 && visited[v]==1) edges++;
            visited[u]=1;
            visited[v]=1;
            list.get(u).add(v);
            list.get(v).add(u);
        }
        for(int i=0;i<n;i++)
        {
         visited[i]=0;
        }
       
        
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            
            if(visited[i]==0)
            {
                count++;
                visited[i]=1;
                queue.offer(i);
                while(!queue.isEmpty())
                {
                    int t=queue.poll();
                    for(int ch:list.get(t))
                    {
                     
                        if(visited[ch]==0)
                        {
                            visited[ch]=1;
                            queue.offer(ch);
                        }
                    }
                }
            }
        }


    if(edges>=count-1)
    {
        return count-1;
    }
    return -1;
       
    }
}