package org.example.week_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_16929_TwoDots_김태수 {
	static int r,c,startX,startY;
	static char[][] map;
	static boolean[][] v;
	static boolean flag;
	
	static int[][] direction = {
			{1,0},
			{0,1},
			{-1,0},
			{0,-1}
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());	//행
		c = Integer.parseInt(st.nextToken());	//열
		map = new char[r][c];					//데이터를 받는 배열
		v = new boolean[r][c];					//방문 배열
		
		//맵을 받습니다.
		for(int i=0;i <r;i++) {
			String ss = br.readLine();
			for(int j=0;j<c;j++) {
				map[i][j] = ss.charAt(j);
			}
		}
		for(int i = 0 ; i < r;i++) {
			for(int j = 0 ; j < c ;j++) {
				if(!v[i][j]) {
					//방문하지 않은 모든 점들에 대해서 visited배열을 초기화 하고, dfs탐색을 해줍니다.
					init(i,j);
					dfs(i,j,0);
				}  
			}
		}
		//DFS 탐색시 사이클을 발견했다면 flag 가 true가 됩니다.
		if(!flag) System.out.println("No");
		else System.out.println("Yes");
	}
	
	public static void dfs(int x, int y, int cnt) {
		//탐색하는 점이 처음 탐색을 시작한 점과 같다면 && 탐색의 길이가 4가 넘는다면 사이클로 인정
		//cnt가 없다면 뒤로갔다 앞으로 돌아오는 경우를 체크하기 힘듬 
		if(x == startX &&  y == startY && cnt >=4) {
			flag = true;
			return;
		}
		
		//dfs탐색
		for(int i = 0 ; i < 4;i++) {
			int nx = direction[i][0] + x;
			int ny = direction[i][1] + y;
			
			if(nx < 0 || nx >=r || ny<0 || ny >= c) continue;
			if(map[x][y] != map[nx][ny]) continue;
			if(v[nx][ny]) continue;
			v[nx][ny] = true;
			dfs(nx,ny,cnt+1);
		}
	}
	public static void init(int i, int j) {
		//visited배열을 새로운 dfs시작전 초기화 해줍니다.
		v = new boolean[r][c];
		//dfs가 시작하는 점을 따로 저장합니다.
		startX = i;
		startY = j;
	}
	
}