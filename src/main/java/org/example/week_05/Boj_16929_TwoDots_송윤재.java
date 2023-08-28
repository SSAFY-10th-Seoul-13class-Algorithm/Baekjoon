package org.example.week_05;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16929_TwoDots_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, M, sr, sc;
    static char board[][];
    static boolean visited[][], flag;
    static int dr[] = {1,-1,0,0};
    static int dc[] = {0,0,1,-1};
    
    static void init() {
    	board = new char[N][M];
    	visited = new boolean[N][M];
    }
    
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	init();
    	for (int i = 0; i < N; i++) {
    		String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
			}
		}
    }
    
    static void solve() {
    	for(int i = 0; i < N; i++) {
    		for (int j = 0; j < M; j++) {
				if(visited[i][j]) continue;
				sr = i;
				sc = j;
				visited[i][j] = true;
				dfs(sr, sc, board[i][j], 0);
				visited[i][j] = false;
				if(flag) return;
			}
    	}
    }
    
    static void dfs(int r, int c, char cur, int depth) {
    	if(flag) return;
    	for(int i = 0; i < 4; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		if(!isInside(nr, nc)) continue;
    		if(cur != board[nr][nc]) continue;
    		if(nr == sr && nc == sc && depth > 2) { flag = true; return; }
    		if(visited[nr][nc]) continue;
    		visited[nr][nc] = true;
    		dfs(nr, nc, cur, depth + 1);
    		visited[nr][nc] = false;
    	}
    }
    
    static boolean isInside(int r, int c) {
    	return r < N && r >= 0 && c < M && c >= 0; 
    }
	public static void main(String[] args) throws IOException{
		input();
        solve();
    	if(flag) sb.append("Yes");
    	else sb.append("No");
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}