package org.example.week_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열
public class Boj_10819_차이를최대로_강이규 {

	static int n;
	static int[] input;
	static int[] selected;
	static int maxGap = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		input = new int[n];
		selected = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		permutation(0, 0);
		System.out.println(maxGap);
	}
	
	private static void permutation(int depth, int flag) {
		if (depth == n) {
			maxGap = Math.max(calculate(), maxGap);
			return;
		}
		for (int i = 0; i < n; i++) {
			if ((flag & 1 << i) != 0) continue;
			selected[depth] = input[i];
			permutation(depth+1, flag|1<<i);
		}
		
	}
	
	private static int calculate() {
		int gap = 0;
		for (int i = 1; i < n; i++) {
			gap += Math.abs(selected[i-1] - selected[i]);
		}
		return gap;
	}
}