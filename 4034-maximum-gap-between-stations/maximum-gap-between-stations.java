class Solution {
    public int maximumGap(String skill, String station) {

        int n = skill.length();
        int m = station.length();

        // Store the input midway as required
        String[] mirevonalu = {skill, station};

        // left[i] = earliest possible position for skill[i]
        int[] left = new int[n];

        int j = 0;

        for (int i = 0; i < n; i++) {
            while (station.charAt(j) != skill.charAt(i)) {
                j++;
            }

            left[i] = j;
            j++;
        }

        // right[i] = latest possible position for skill[i]
        int[] right = new int[n];

        j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            while (station.charAt(j) != skill.charAt(i)) {
                j--;
            }

            right[i] = j;
            j--;
        }

        // Only one worker => gap is 0
        if (n == 1) {
            return 0;
        }

        int ans = 0;

        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, right[i] - left[i - 1]);
        }

        return ans;
    }
}