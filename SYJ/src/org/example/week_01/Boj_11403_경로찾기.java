package org.example.week_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_11403_경로찾기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, graph[][];
	
	// 입력
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	// 풀이
	static void solve() {
		/*
		 * 정점 i에서 j까지 갈 수 있는 경우는
		 * 	1. i에서 j까지 가는 경로가 있을 때
		 * 	2. i에서 k까지 가는 경로가 있고	k에서 j까지 가는 경로가 있을 때
		*/
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(graph[i][k] == 1 && graph[k][j] == 1) {
						graph[i][j] = 1; 
					}
				}
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
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