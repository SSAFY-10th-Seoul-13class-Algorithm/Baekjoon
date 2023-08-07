package org.example.week_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2447_별찍기10_송윤재 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static boolean map[][];
	
	// 입력
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
	}
	
	// 풀이
	static void solve() {
		fill_star(0, 0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]) sb.append("*");
				else sb.append(" ");
			}
			sb.append("\n");
		}
	}
	
	static void fill_star(int i, int j, int length) {
		if(length == 1) { // 재귀 탈출 조건
			map[i][j] = true;
			return;
		}
		int next_length = length / 3;
		int ni = i, nj = j; // 시작 인덱스를 변경해줄 변수
		for (int x = 0; x < 3; x++) {
			nj = j;
			for (int y = 0; y < 3; y++) {
				if(x != 1 || y != 1) // 중간 값이 아닐 때 재귀 호출
					fill_star(ni, nj, next_length);
				nj += next_length;
			}
			ni += next_length;
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