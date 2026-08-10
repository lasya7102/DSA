class Solution {
    class Pair implements Comparable<Pair> {
        int elevation, row, col;

        Pair(int elevation, int row, int col) {
            this.elevation = elevation;
            this.col = col;
            this.row = row;
        }

        public int compareTo(Pair p) {
            return this.elevation - p.elevation;
        }
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean visited[][] = new boolean[n][n];

        pq.offer(new Pair(grid[0][0], 0, 0));
        visited[0][0] = true;
        int deltaDir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int elevation = p.elevation, row = p.row, col = p.col;
            if (row == n - 1 && col == n - 1)
                return elevation;
            for (int dir[] : deltaDir) {
                int nr = row + dir[0];
                int nc = col + dir[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n && visited[nr][nc] == false) {
                    visited[nr][nc] = true;
                    pq.offer(new Pair(Math.max(elevation, grid[nr][nc]), nr, nc));
                }
            }
        }
        return -1;
    }
}