class Solution {
    
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
        int height=1;
       
        int depth[]=new int[parent.length];
         depth[0]=1;
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        while(!queue.isEmpty())
            {
                int x=queue.poll();
                for(int ch:list.get(x))
                    {
                        depth[ch]=depth[x]+1;
                        height=Math.max(depth[ch],height);
                        queue.offer(ch);
                    }
            }
        long ans=0;
        for(int i=0;i<parent.length;i++)
            {
                
                ans += (long)nums[i]*(height - depth[i] + 1);
            }
        return ans;
    }
}