package org.example.week_05;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16947_서울지하철2호선_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, result[];
    static boolean visited[], isCycle[];
    static ArrayList<Integer> list[];
    
    static void init() {
    	list = new ArrayList[N + 1];
    	for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
    	result = new int[N + 1];
    	visited = new boolean[N + 1];
    	isCycle = new boolean[N + 1];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		list[a].add(b);
    		list[b].add(a);
    	}
    }
    
    static void solve() {
    	dfs(1);
    }
    
    static void dfs(int cur) {
    	visited[cur] = true;
    	System.out.println(cur);
    	for(int next : list[cur]) {
    		if(visited[next]) {
    			System.out.println(Arrays.toString(visited));
    			return;
    		}
    		dfs(next);
    		visited[next] = false;
    	}
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