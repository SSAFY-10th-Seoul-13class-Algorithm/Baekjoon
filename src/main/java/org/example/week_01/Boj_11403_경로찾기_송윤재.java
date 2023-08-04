package org.example.week_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;



public class Boj_11403_경로찾기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, graph[][];
	
	// �Է�
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
	
	// Ǯ��
	static void solve() {
		/*
		 * ���� i���� j���� �� �� �ִ� ����
		 * 	1. i���� j���� ���� ��ΰ� ���� ��
		 * 	2. i���� k���� ���� ��ΰ� �ְ�	k���� j���� ���� ��ΰ� ���� ��
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
