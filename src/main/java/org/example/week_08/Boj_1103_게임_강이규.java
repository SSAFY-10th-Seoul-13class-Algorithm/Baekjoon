package org.example.week_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1103_게임_강이규 {
    static int N, M;
    static int[][] map; // hole = -1
    static boolean[][] visited;
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] memo;
    static int maxCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        memo = new int[N][M];


        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = c != 'H' ? c - '0' : -1;
            }
        }

        dfs(0, 0, 1);
        System.out.println(maxCnt);
    }

    static void dfs(int r, int c, int cnt) {
        visited[r][c] = true;
        memo[r][c] = cnt;
        maxCnt = Math.max(maxCnt, cnt);

        for (int[] move : moves) {
            int nr = r + (move[0] * map[r][c]);
            int nc = c + (move[1] * map[r][c]);
            if (!isValid(nr, nc)) continue;
            if (visited[nr][nc]) { // 사이클 존재
                System.out.println(-1);
                System.exit(0);
            }
            if (cnt+1 <= memo[nr][nc]) continue;
            dfs(nr, nc, cnt+1);
        }
        visited[r][c] = false; // 이렇게 하면, visited[r][c]는 'map[r][c]를 방문했고, 거기부터 생기는 가지들의 탐색이 아직 끝나지 않았는지' 를 의미한다.
    }

    static boolean isValid(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M) && map[r][c] != -1;
    }
}
