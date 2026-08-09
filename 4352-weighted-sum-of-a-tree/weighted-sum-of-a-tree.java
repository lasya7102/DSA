class Solution {
     int height=1;
       
     void dfs(int i,ArrayList<ArrayList<Integer>> list,int depth[])
    {
       
        
        for(int ch:list.get(i))
        {
             depth[ch] = depth[i] + 1;
            height = Math.max(height, depth[ch]);

            dfs(ch, list, depth);
        }
        
        
        
          
    }
    public long weightedSum(int[] parent, int[] nums) {
      
       ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        for(int i=0;i<parent.length;i++)
            {
                list.add(new ArrayList<>());
            }
        for(int i=1;i<parent.length;i++)
            {
                list.get(parent[i]).add(i);
            }
       
        int depth[]=new int[parent.length];
         depth[0]=1;

        // Queue<Integer> queue=new LinkedList<>();
        // queue.offer(0);
        // while(!queue.isEmpty())
        //     {
        //         int x=queue.poll();
        //         for(int ch:list.get(x))
        //             {
        //                 depth[ch]=depth[x]+1;
        //                 height=Math.max(depth[ch],height);
        //                 queue.offer(ch);
        //             }
        //     }
        dfs(0,list,depth);
        long ans=0;
        for(int i=0;i<parent.length;i++)
            {
                
                ans += (long)nums[i]*(height - depth[i] + 1);
            }
        return ans;
    }
}