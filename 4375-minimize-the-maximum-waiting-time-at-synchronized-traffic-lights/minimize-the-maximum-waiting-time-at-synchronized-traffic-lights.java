class Solution {
    static boolean Find(int a[],int k)
    {
    int l=0,h=a.length-1;
    while(l<=h)
        {
            int m=(l+h)/2;
            if(a[m]>k)
            {
                return true;
            }
            else if(a[m]<=k)
            {
                l=m+1;
            }
            
        }
    return false;
    }
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int max=-1;
        Arrays.sort(lights);
        for(int i=0;i<arrivalTime.length;i++)
            {
                int x=arrivalTime[i]%period;
                int wT=0;
                if(!Find(lights,x))
                {
                    wT=period-x;
                }
                max=Math.max(max,wT);
            }
        return max;
    }
}