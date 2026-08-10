class Solution {
    public int swimInWater(int[][] grid) {

        int n = grid.length;

        boolean[][] vis = new boolean[n][n];

        // {maximum elevation encountered, row, col}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[]{grid[0][0], 0, 0});

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int time = cur[0];
            int row = cur[1];
            int col = cur[2];

            if (vis[row][col])
                continue;

            vis[row][col] = true;

            // Reached destination
            if (row == n - 1 && col == n - 1)
                return time;

            for (int k = 0; k < 4; k++) {

                int nr = row + dx[k];
                int nc = col + dy[k];

                if (nr >= 0 && nr < n &&
                    nc >= 0 && nc < n &&
                    !vis[nr][nc]) {

                    int newTime =
                        Math.max(time, grid[nr][nc]);

                    pq.offer(new int[]{
                        newTime, nr, nc
                    });
                }
            }
        }

        return -1;
    }
}