package org.example.week_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_3665_최종순위_강이규 {

    static int N, M;
    static int[] teams;
    static int[] inDegree;
    static boolean[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            teams = new int[N+1];
            adjMatrix = new boolean[N+1][N+1];
            inDegree = new int[N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                teams[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= N; i++) {
                for (int j = i+1; j <= N; j++) {
                    adjMatrix[teams[i]][teams[j]] = true;
                    inDegree[teams[j]]++;
                }
            }

            M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                if (adjMatrix[start][end]) {
                    adjMatrix[start][end] = false;
                    adjMatrix[end][start] = true;
                    inDegree[end]--;
                    inDegree[start]++;
                } else {
                    adjMatrix[end][start] = false;
                    adjMatrix[start][end] = true;
                    inDegree[start]--;
                    inDegree[end]++;
                }
            }

            List<Integer> res = topologySort();

            if (res != null) {
                for (int team : res) {
                    sb.append(team).append(" ");
                }
                sb.append("\n");
            } else {
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.println(sb);
    }

    private static List<Integer> topologySort() {
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        // find start
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);

            for (int i = 1; i <= N; i++) {
                if (!adjMatrix[cur][i]) continue;
                if (--inDegree[i] == 0) q.offer(i);
            }
        }

        if (res.size() != N) return null;
        return res;
    }
}
