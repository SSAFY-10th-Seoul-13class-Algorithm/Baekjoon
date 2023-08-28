package org.example.week_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16929_TwoDots_강이규 {

    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char color;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) continue;

                color = map[i][j];
                dfs(i, j, i, j);
            }
        }
        System.out.println("No");
    }

    private static void dfs(int r, int c, int preR, int preC) {
        visited[r][c] = true;

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (!isValid(nr, nc)) continue;
            if (visited[nr][nc]) {
                if (nr == preR && nc == preC) continue;
                // visited & not pre
                System.out.println("Yes");
                System.exit(0);
            }
            dfs(nr, nc, r, c);
        }
    }

    private static boolean isValid(int r, int c) {
        return (0 <= r && r < n) && (0 <= c && c < m) && map[r][c] == color;
    }

}