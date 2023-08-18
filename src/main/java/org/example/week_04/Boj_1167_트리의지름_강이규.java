package org.example.week_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_1167_트리의지름_강이규 {

    static int v;
    static List<int[]>[] adjList;
    static boolean[] visited;
    static int maxDist;
    static int farNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        v = Integer.parseInt(br.readLine());
        adjList = new ArrayList[v+1];
        visited = new boolean[v+1];

        for (int i = 1; i <= v; i++) {
            adjList[i] = new ArrayList<>();
        }


        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to < 0) break;
                int dist = Integer.parseInt(st.nextToken());

                adjList[from].add(new int[]{to, dist});
                adjList[to].add(new int[]{from, dist});
            }
        }

        // 루트부터 가장 먼 노드 찾기
        dfs(1, 0);

        int startNode = farNode;

        // 먼 노드부터, dfs
        maxDist = 0;
        farNode = -1;
        visited = new boolean[v+1];

        dfs(startNode, 0);

        System.out.println(maxDist);
    }

    private static void dfs(int cur, int dist) {
        // visited action
        visited[cur] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farNode = cur;
        }

        for (int[] edge : adjList[cur]) {
            int next = edge[0];
            int cost = edge[1];
            if (visited[next]) continue;
            dfs(next, dist + cost);
        }
    }
}
