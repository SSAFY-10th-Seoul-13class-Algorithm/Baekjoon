package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q_2098 {

    static int N;
    static int[][] adjMatrix;
    static int[][] dp;
    static final int INF = 16_000_000; // 최대값
    static int maxFlag; // 모두 방문했을 때

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N][1<<(N+1)];
        maxFlag = (1<<N) - 1;

        adjMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int cur, int flag) {
        // 모두 방문
        if (flag == maxFlag) {
            int tmpCost = adjMatrix[cur][0];
            return tmpCost != 0 ? tmpCost : INF;
        }
        // 방문했던 노드일 때
        if (dp[cur][flag] != -1) return dp[cur][flag];
        dp[cur][flag] = INF;

        // next
        for (int i = 0; i < N; i++) {
            if ((flag & 1<<i) != 0 || adjMatrix[cur][i] == 0) continue;
            // 방문하지 않았고, 갈 수 있으면
            dp[cur][flag] = Math.min(dp[cur][flag], dfs(i, flag | 1<<i) + adjMatrix[cur][i]);
        }
        return dp[cur][flag];
    }
}
