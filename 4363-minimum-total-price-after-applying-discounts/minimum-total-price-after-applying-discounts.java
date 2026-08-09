class Solution {
    public double minPrice(int[] prices, int[] discounts) {
        double ans=0;
        Arrays.sort(prices);
        Arrays.sort(discounts);
        int ind=discounts.length-1;
        int i=prices.length-1;
        while(ind>=0 && i>=0)
            {
                ans += (prices[i]*(100-discounts[ind]))*0.01;
                ind--;
                i--;
            }
        while(i>=0)
            {
                ans += prices[i];
                i--;
            }
        return ans;
    }
}