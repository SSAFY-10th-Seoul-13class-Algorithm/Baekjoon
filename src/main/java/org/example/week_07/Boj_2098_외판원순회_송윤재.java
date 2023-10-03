package org.example.week_07;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2098_외판원순회_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int W[][], N, dp[][];
    static final int INF = 16_000_000; // 최대값 100만 * 16
    static void init() {
    	W = new int[N][N];
    	dp = new int[N][(1 << N) - 1];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < N; j++) {
    			W[i][j] = Integer.parseInt(st.nextToken());
    		}
    		Arrays.fill(dp[i], -1);
    	}
    }
    
    static void solve() {
    	sb.append(dfs(0, 1)); // 어떤 노드에서 시작해도 상관 없으니 첫 노드에서 시작
    }
    
    static int dfs(int node, int bitmask) {
    	if(bitmask == (1 << N) - 1) {
    		if(W[node][0] == 0) return INF;
    		return W[node][0]; // 모든 노드를 방문 한 경우 출발지와 이어준다.
    	}
    	if(dp[node][bitmask] != -1) return dp[node][bitmask]; // dp 값이 갱신된 경우

    	dp[node][bitmask] = INF; // 방문 처리
    	
    	for(int i = 0; i < N; i++) {
    		if(W[node][i] == 0) continue; // 경로가 없는 경우
    		if((bitmask & (1 << i)) != 0) continue; // 방문한 경우
    		dp[node][bitmask] = Math.min(dp[node][bitmask], dfs(i, bitmask | (1 << i)) + W[node][i]);
    		// dp 값 갱신
    	}
    	return dp[node][bitmask];
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