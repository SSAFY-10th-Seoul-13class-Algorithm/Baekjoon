package org.example.taesoo.week_2;

import java.util.Scanner;

public class Boj_10819_차이를최대로 {
	static int[] target;
	static int[] bag;
	static boolean[] visited;
	static int len;
	static int optimum;
	
	public static void diffToMax(int depth) {
		if(depth == len) {
			int sum = 0;
			for(int i = 0; i < len - 1 ; i++) {
				sum += Math.abs(bag[i] - bag[i+1]);
			}
			//System.out.println(sum);
			optimum = Math.max(optimum, sum);
			return;
		}
		
		for(int i = 0; i < len ; i ++) {
			if(visited[i]) continue;
			visited[i] = true;
			bag[depth] = target[i];
			diffToMax(depth + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		len = sc.nextInt();
		target = new int[len];
		bag = new int[len];
		visited = new boolean[len];
		
		for(int i = 0 ; i < len ; i++) {
			target[i] = sc.nextInt();
		}

		diffToMax(0);
		System.out.println(optimum);
	}
}
