package org.example.week_03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_4485_녹색옷입은애가젤다지_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, map[][], dist[][];
	static int dr[] = {1,-1,0,0};
	static int dc[] = {0,0,1,-1};
	static boolean visited[][];
	static final int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node>{
		int row, col, cost;

		public Node(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static void init() {
		map = new int[N][N];
		visited = new boolean[N][N];
		dist = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}
	}
	
	static int input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return N;
	}
	
	static void solve() {
		dijkstra();
		sb.append(dist[N - 1][N - 1]).append("\n");
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, map[0][0]));
		visited[0][0] = true;
		dist[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int c_row = cur.row;
			int c_col = cur.col;
			for(int i = 0; i < 4; i++) {
				int n_row = c_row + dr[i];
				int n_col = c_col + dc[i];
				
				if(n_row < 0 || n_row >= N || n_col < 0 || n_col >= N) continue;
				if(visited[n_row][n_col]) continue;

				visited[n_row][n_col] = true;
				if(dist[n_row][n_col] > dist[c_row][c_col] + map[n_row][n_col]) {
					dist[n_row][n_col] = dist[c_row][c_col] + map[n_row][n_col];
					pq.offer(new Node(n_row, n_col, dist[n_row][n_col]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		int test_cast = 0;
		while(input() != 0) {
			sb.append("Problem ").append(++test_cast).append(": ");
			solve();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
