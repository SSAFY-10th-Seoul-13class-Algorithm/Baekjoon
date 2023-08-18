package org.example.week_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_2493_탑_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Stack<Integer> stkTower = new Stack<Integer>();		//탑을 담는 스택
		Stack<Integer> stkIdx = new Stack<Integer>();		//탑의 인덱스를 담는 스택
		int[] tower,result;
		StringTokenizer st;
		StringBuilder sb;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());			//탑의 수
		tower = new int[T];									//탑을 담을 배열	
		result = new int[T];								//결과를 담을 배열
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < T; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		/*
		 * 시간복잡도: O(N)
		 * 
		 * while문에서도 모든 원소는 한번씩만 거쳐가니까 총 n번의 연산만 하게된다.
		 * 
		 * 공간복잡도: O(N)
		 * 
		 * tower 배열, 그리고 stack또한 최대의 경우에도 N만큼 찬다.
		 * 
		 * */
		
		for(int i = 0 ; i < T ; i++) {						 
			int target = tower[i];
			//자신보다 큰 탑이 나올때까지 tower를 팝, 인덱스도 같이 팝
			while(!stkTower.isEmpty() && stkTower.peek() < target) {
				stkTower.pop();
				stkIdx.pop();
			}
			//스택에 남아있는 결과에 따라 result에 값을 넣어줌: 스택이 비어있다면 0, 무언가 들어있다면 그 원소의 인덱스
			result[i] = (stkTower.isEmpty()) ? 0: stkIdx.peek();
			stkTower.push(target);
			stkIdx.push(i+1);
		}
		sb = new StringBuilder();
		for(int i: result) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
