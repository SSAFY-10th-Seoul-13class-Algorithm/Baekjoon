package org.example.taesoo.week_01;

import java.util.Scanner;

public class Boj_11403_경로찾기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		int[][] diagram = new int[t][t];
		int[][] result = new int[t][t];
		
		for(int i = 0; i < t ; i++) {
			for(int j = 0 ; j < t ; j++) {
				diagram[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0 ; i < t ; i ++) {
			for(int j = 0 ; j < t ; j ++) {
				for(int k = 0; k < t ; k++) {
					if(diagram[j][i]== 1 && diagram[i][k] == 1) {
						diagram[j][k] = 1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t ; i ++) {
			for(int j = 0; j < t ; j++) {
				sb.append(diagram[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

