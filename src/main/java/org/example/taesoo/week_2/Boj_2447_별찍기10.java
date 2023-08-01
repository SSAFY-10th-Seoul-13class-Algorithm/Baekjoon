package org.example.taesoo.week_2;

import java.util.Arrays;
import java.util.Scanner;

public class Boj_2447_별찍기10 {
	
	static char[][] diagram;
	
	static void printStar(int x,int y, int size, int flag) {
		//플래그가 1일때 공백, 0일때 * 출력
		if(flag == 1) {
			for(int i = x; i< x+size;i++) {
				for(int j = y; j<y+size;j++) {
					diagram[i][j] = ' ';
				}
			}
			return;
		}
		
		if(size == 1) {
			diagram[x][y] = '*';
			return;
		}
		
		int cnt = 0;
		int newSize = size / 3;
		
		for(int i = x; i < x + size ; i += newSize) {
			for(int j = y; j < y + size ; j += newSize) {
				cnt++;
				if(cnt == 5) {
					printStar(i,j,newSize,1);
				}
				else {
					printStar(i,j,newSize,0);
				}
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		diagram = new char[t][t];
		printStar(0,0,t,0);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < t ; i++) {
			
			for(int j = 0 ; j < t ; j++) {
				sb.append(diagram[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
