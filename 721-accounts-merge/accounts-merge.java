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
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DisjointSet dj=new DisjointSet(accounts.size());
        HashMap<String,Integer> map=new HashMap<>();
        for(int i=0;i<accounts.size();i++)
        {
            for(int j=1;j<accounts.get(i).size();j++)
            {
                if(map.containsKey(accounts.get(i).get(j)))
                {
                   dj.unionBySize(i,map.get(accounts.get(i).get(j)));
                }
                else
                {
                map.put(accounts.get(i).get(j),i);
                }
            }
        }
 
    //    HashMap<Integer,List<String>> ans=new HashMap<>();
    //    for(HashMap.Entry<String, Integer> entry : map.entrySet())
    //    {
    //     int x=dj.findParent(entry.getValue());
    //     if(ans.containsKey(x))
    //     {
    //         ans.get(x).add(entry.getKey());
    //     }
    //     else
    //     {
    //         ans.put(x,new ArrayList<>());
    //         ans.get(x).add(entry.getKey());
    //     }
    //    }
    ArrayList<String>[] ans=new  ArrayList[accounts.size()];
    for(int i=0;i<accounts.size();i++)
    {
        ans[i]=(new ArrayList<String>());
    }
    for(HashMap.Entry<String, Integer> entry : map.entrySet())
    {
        int x=dj.findParent(entry.getValue());
        ans[x].add(entry.getKey());
    }
     List<List<String>> data=new ArrayList<>();
    
       for(int i=0;i<ans.length;i++)
       {
        if(ans[i].size()==0) continue;
         Collections.sort(ans[i]);
         List<String> str=new ArrayList<>();
         str.add(accounts.get(i).get(0));
         for(String c:ans[i])
         {
            str.add(c);
         }
       
           data.add(str);
       }
     
     return data;
    }
}