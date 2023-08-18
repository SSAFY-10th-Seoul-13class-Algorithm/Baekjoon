package org.example.week_04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_1167_트리의지름_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int V, lastNode, max;
	static ArrayList<Node>[] list;
	static boolean visited[];
	
	static class Node{
		int num, cost;
		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}
	
	static void input() throws IOException{
		V = Integer.parseInt(br.readLine());
		list = new ArrayList[V + 1];
		visited = new boolean[V + 1];
		max = 0;
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			list[node] = new ArrayList<>();
			while(true) {
				int num = Integer.parseInt(st.nextToken());
				if(num == -1) break;
				int cost = Integer.parseInt(st.nextToken());
				list[node].add(new Node(num, cost));
			}
		}
	}
	
	static void solve() {
		dfs(1, 0);
		visited = new boolean[V + 1];
		dfs(lastNode, 0);
		sb.append(max);
	}
	
	static void dfs(int cur, int dist) {
		visited[cur] = true;
		for(Node node : list[cur]) {
			if(visited[node.num]) continue;
			dfs(node.num, dist + node.cost);
		}
		if(max < dist) {
			lastNode = cur;
			max = dist;
		}
	}
	public static void main(String[] args) throws IOException{
		input();
		solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
