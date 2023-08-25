package org.example.week_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_16947_서울지하철2호선_강이규 {

    static int n;
    static int[] dists;
    static List<Integer>[] adjList;
    static boolean[] isCycle;
    static int start;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n+1];
        dists = new int[n+1];
        isCycle = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 무향 그래프
            adjList[from].add(to);
            adjList[to].add(from);
        }
        // 사이클 찾기
        for (int i = 1; i <= n; i++) {
            start = i;
            if (dfs(i, i)) break;
            isCycle = new boolean[n+1];
        }
        // 거리 구하기
        for (int i = 1; i <= n; i++) {
            if (isCycle[i]) continue;
            dists[i] = bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dists[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static int bfs(int start) {
        // 사이클로부터의 거리 반환
        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new ArrayDeque<>(); // 노드, 거리
        q.offer(new int[]{start, 0});
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int cur = info[0];

            int nextDist = info[1] + 1;
            for (int adj : adjList[cur]) {
                if (visited[adj]) continue;
                if (isCycle[adj]) return nextDist;
                q.offer(new int[]{adj, nextDist});
                visited[adj] = true;
            }
        }
        return -1;
    }

    private static boolean dfs(int cur, int pre) {
        // 사이클 찾기
        isCycle[cur] = true;

        for (int next : adjList[cur]) {
            if (isCycle[next]) {
                if (next == start && next != pre) return true;
            } else if (dfs(next, cur)) return true;
        }
        isCycle[cur] = false;
        return false;
    }
}
