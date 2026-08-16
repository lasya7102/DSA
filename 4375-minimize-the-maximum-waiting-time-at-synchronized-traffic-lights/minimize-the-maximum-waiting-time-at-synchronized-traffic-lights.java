class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        //Arrays.sort(lights);
        int n = lights.length;
        int time = 0;
        int max = 0;
        for(int i=0;i<n;i++){
            if(lights[i] > max) max = lights[i];
        }
        for(int i=0;i<arrivalTime.length;i++){
           int r = arrivalTime[i]%period;
            int wait = 0;
           if(r < max){
               wait += 0;
           }else{
               wait += period - r;
           }
            time = Math.max(time, wait);
        }
        
    return time;    
    }
}