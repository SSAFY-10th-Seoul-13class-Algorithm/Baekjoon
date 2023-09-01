package org.example.week_06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1647_G4_도시분할계획_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, M, parents[];
    static Edge[] edgeList;
    
    static class Edge implements Comparable<Edge>{
    	int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
    }
    
    static void make() {
    	parents = new int[N + 1];
    	for(int i = 1; i < N + 1; i++) {
    		parents[i] = i;
    	}
    }
    
    static int find(int v) {
    	if(parents[v] == v) return v;
    	return parents[v] = find(parents[v]);
    }
    
    static boolean union(int a, int b) {
    	int ar = find(a);
    	int br = find(b);
    	if(ar == br) return false;
    	parents[br] = ar;
    	return true;
    }
    
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	edgeList = new Edge[M];
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		edgeList[i] = new Edge(from, to, cost);
		}
    }
    
    static void solve() {
    	if(N == 2) {
    		sb.append("0");
    		return;
    	}
    	Arrays.sort(edgeList);
    	make();
		
		int result = 0; // MST 비용
		int count = 0; // 연결된 간선 개수
		for (Edge edge : edgeList) { // 비용이 작은 간선순으로 꺼내어서 처리
			if(union(edge.from, edge.to)) {
				result += edge.cost;
				if(++count == N - 2) break;
			}
		}
		sb.append(result);
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
