class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        int INF = Integer.MAX_VALUE / 2;

        // dp[t][city] = minimum cost to reach city
        // using exactly t minutes
        int[][] dp = new int[maxTime + 1][n];

        for (int t = 0; t <= maxTime; t++) {
            for (int city = 0; city < n; city++) {
                dp[t][city] = INF;
            }
        }

        dp[0][0] = passingFees[0];

        // Bellman-Ford-style relaxation by travel time
        for (int t = 1; t <= maxTime; t++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int time = edge[2];

                if (t >= time) {
                    if (dp[t - time][u] != INF) {
                        dp[t][v] = Math.min(
                            dp[t][v],
                            dp[t - time][u] + passingFees[v]
                        );
                    }

                    if (dp[t - time][v] != INF) {
                        dp[t][u] = Math.min(
                            dp[t][u],
                            dp[t - time][v] + passingFees[u]
                        );
                    }
                }
            }
        }

        int ans = INF;

        for (int t = 0; t <= maxTime; t++) {
            ans = Math.min(ans, dp[t][n - 1]);
        }

        return ans == INF ? -1 : ans;
    }
}