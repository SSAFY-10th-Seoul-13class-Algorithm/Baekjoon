package org.example.week_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1103_게임_김희연 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int n, m, max;
	static boolean flag = false;
	static int[][] dp;
	static char[][] map;
	static boolean[][] visited;


	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dp = new int[n][m];
		map = new char[n][m];
		visited = new boolean[n][m];

		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		visited[0][0] = true;
		dfs(0, 0, 1);

		if(flag) {
			System.out.println("-1");
		} else {
			System.out.println(max);
		}
	}

	static void dfs(int x, int y, int cnt) {
		int num = map[y][x] - '0';
		dp[y][x] = cnt;

		max = Math.max(max, cnt);

		for(int i = 0; i < dx.length; i++) {
			int nx = x + (num * dx[i]);
			int ny = y + (num * dy[i]);

			if(visited[ny][nx]) {
				flag = true;
				return;
			}

			if(nx < 0 || nx >= m || ny < 0 || ny >= n || map[ny][nx] == 'H') {
				continue;
			}

			if(cnt < dp[ny][nx]) {
				continue;
			}

			visited[ny][nx] = true;
			dfs(nx, ny, cnt + 1);
			visited[ny][nx] = false;

		}
	}
}
