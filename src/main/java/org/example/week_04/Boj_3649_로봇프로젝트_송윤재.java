package org.example.week_04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_3649_로봇프로젝트_송윤재 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int x, n, lego[];
	// 입력
	static void input() throws IOException{
		n = Integer.parseInt(br.readLine());
		lego = new int[n];
		x *= 10_000_000; // 나노미터로 변환. x의 최대 값은 20이므로 int 범위 안
		for(int i = 0; i < n; i++) {
			lego[i] = Integer.parseInt(br.readLine());
		}
	}
	
	// 풀이
	static void solve() {
		Arrays.sort(lego); // nlogn 최대 2천만
		int start = 0;
		int end = n - 1;
		int answer = 0;
		while(start < end) {
			answer = lego[start] + lego[end];
			if(answer > x) end--;
			else if(answer < x) start++;
			else {
				sb.append("yes").append(" ").append(lego[start]).
				append(" ").append(lego[end]).append("\n");
				return;
			}
		}
		sb.append("danger").append("\n");
	}
	
	public static void main(String[] args) throws IOException{
		String in;
		while ((in = br.readLine()) != null) {
			x = Integer.parseInt(in);
			input();
			solve();
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}