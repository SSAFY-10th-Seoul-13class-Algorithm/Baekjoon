package org.example.week_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_4485_녹색옷입은애가젤다지_강이규 {

    static int n;
    static int[][] map;
    static int[][] dists;
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            map = new int[n][n];
            dists = new int[n][n];

            // 각 배열 초기화
            // map
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // dists
            for (int i = 0; i < n; i++) {
                Arrays.fill(dists[i], Integer.MAX_VALUE);
            }
            // 실행
            dijkstra();
            sb.append("Problem ").append(cnt++).append(": ").append(dists[n-1][n-1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.offer(new int[]{0, 0, map[0][0]});
        dists[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int[] move : moves) {
                int newR = cur[0] + move[0];
                int newC = cur[1] + move[1];
                if (inRange(newR, newC)) {
                    int newDist = dists[cur[0]][cur[1]] + map[newR][newC];
                    if (newDist >= dists[newR][newC]) continue;
                    dists[newR][newC] = newDist;
                    pq.offer(new int[]{newR, newC, dists[newR][newC]});
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return (0 <= r && r < n) && (0 <= c && c < n);
    }
}