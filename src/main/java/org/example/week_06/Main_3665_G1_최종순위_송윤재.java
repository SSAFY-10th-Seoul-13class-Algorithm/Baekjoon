package org.example.week_06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3665_G1_최종순위_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, T[], inDegree[];
	static boolean adjList[][];
	
	static void init() {
		T = new int[N];
		adjList = new boolean[N + 1][N + 1];
		inDegree = new int[N + 1];
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			T[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				adjList[T[i]][T[j]] = true;
			}
		}
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a][b] ^= true; 
			adjList[b][a] ^= true; 
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if(adjList[i][j]) inDegree[j]++;
			}
		}
	}
	
	static void solve() {
		Queue<Integer> que = new ArrayDeque<>();
		for (int i = 1; i < N + 1; i++) {
			if(inDegree[i] == 0) que.offer(i);
		}
		ArrayList<Integer> list = new ArrayList<>();
		while(!que.isEmpty()) {
			if(que.size() != 1) { // inDegree가 0인 노드가 1개 초과인 경우 그 노드들의 순위를 정할 수 없다.
				sb.append("?");
				return;
			}
			int cur = que.poll();
			list.add(cur);
			for (int i = 1; i < N + 1; i++) {
				if(adjList[cur][i]) {
					if(--inDegree[i] == 0) {
						que.offer(i);
					}
				}
			}
		}
		if(list.size() != N) sb.append("IMPOSSIBLE"); // 싸이클이 만들어진 경우
		else {
			for (int i = 0; i < N; i++) {
				sb.append(list.get(i)).append(" ");
			}
		}
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			input();
			solve();
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
