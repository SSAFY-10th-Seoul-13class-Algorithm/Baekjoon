package org.example.week_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Boj_10819_차이를최대로_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, A[], numbers[], max;
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken()); 
		}
	}
	
	
	static void solve() {
		permutation(0, 0);
		sb.append(max);
	}
	
	static void permutation(int cnt, int flag) {
		if(cnt == N) {
			int result = 0;
			for(int i = 1; i < N; i++) {
				result += Math.abs(numbers[i - 1] - numbers[i]);
			}
			max = Math.max(max, result);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if((flag & 1 << i) != 0) continue;
			numbers[cnt] = A[i];
			permutation(cnt + 1, flag | 1 << i);
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
