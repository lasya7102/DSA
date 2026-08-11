class Solution {
    int timer=1;
    void dfs(int i,int parent,int[] time,int[] low,List<List<Integer>> result,List<List<Integer>> list,int[] visited)
    {

        visited[i]=1;
        low[i]=time[i]=timer;
        timer++;
        for(int ch:list.get(i))
        {
         if(ch==parent) continue;
         if(visited[ch]==0)
         {
            dfs(ch,i,time,low,result,list,visited);
             low[i]=Math.min(low[ch],low[i]);
             if(low[ch]>time[i]) result.add(Arrays.asList(ch,i));
         }
         else
         {
            low[i]=Math.min(low[ch],low[i]);
         }

        }

    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> list=new ArrayList<>();
        int[] visited=new int[n];
        int[] time=new int[n];
        int[] low=new int[n];
        for(int i=0;i<n;i++)
        {
            list.add(new ArrayList<>());
        }
        for(int i=0;i<connections.size();i++)
        {
            int u=connections.get(i).get(0);
            int v=connections.get(i).get(1);
            list.get(u).add(v);
            list.get(v).add(u);
            
           

        }
        List<List<Integer>> result=new ArrayList<>();
            dfs(0,-1,time,low,result,list,visited);
         return result;
    }
}