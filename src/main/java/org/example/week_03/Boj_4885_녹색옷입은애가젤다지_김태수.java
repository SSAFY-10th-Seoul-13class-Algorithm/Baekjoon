package org.example.week_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_4885_녹색옷입은애가젤다지_김태수 {
	
	//방향 벡터
	static int[][] direction = new int[][]{
		{1,0},
		{0,1},
		{-1,0},
		{0,-1}
	};

	public static void main(String[] args) throws NumberFormatException, IOException {
		int idx = 1;													//문제 번호
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			int T = Integer.parseInt(br.readLine());					//map의 사이즈
			if(T == 0) break;											//0: while 종료 조건
			int [][] map = new int[T][T];								//cost를 담는 배열
			int[][] result = new int[T][T];								//최소 누적 cost를 담는 배열
			
			StringTokenizer st;
			
			for(int i = 0 ;  i < T; i++){
				st = new StringTokenizer(br.readLine());
				for(int j= 0 ; j <T;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					result[i][j] = Integer.MAX_VALUE;					//최솟값을 저장하기 위해 가장 큰 값으로 초기화
				}
			}
			
			//방문할 노드를 관리할 우선순위 큐
			PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2)->(p1[2] - p2[2]));	//cost순으로 오름차순 정렬
			pq.offer(new int[] {0,0,map[0][0]});
			
			
			/*시간복잡도: O(N^2)
			 * 
			 * 어떤 노드를 방문한다면 그 노드에 최소 코스트로 왔기때문
			 * -> 다시 방문하지 않기때문에 최악의 경우는 모든 노드를 탐색할때 
			 * -> 맞는지 모르겠음.
			 * 
			 * 
			 * 공간복잡도: O(N^2)
			 * 
			 * 2차원 배열이 가장 큰 공간
			 * 
			 * */
			//탐색 시작
			while(!pq.isEmpty()) {
				int[] state = pq.poll();
				for(int i = 0; i < 4; i++) {
					int dx = state[0] + direction[i][0];
					int dy = state[1] + direction[i][1];
					
					//가능한 움직임인지 검증: result에서 최솟값이 갱신이 가능한 경우만 진행
					if(dx<0 || dx >=T || dy < 0 || dy>=T ||result[dx][dy] <= state[2] + map[dx][dy]) continue;

					result[dx][dy] = state[2] + map[dx][dy];
					pq.offer(new int[] {dx,dy,result[dx][dy]});
				}
			}

			System.out.println("Problem " +(idx++)+": "+result[T-1][T-1]);
		}
	}
}
