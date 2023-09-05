package org.example.week_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj_1647_도시분할계획_김희연 {

	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;

		Edge(int start, int end, int weight){
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int N, M;
	static int[] parents;
	static ArrayList<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edgeList = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(start, end, weight));
		}

		make();

		Collections.sort(edgeList);

		int ans = 0;
		int cost = 0;
		for(int i=0; i<edgeList.size(); i++) {
			Edge edge = edgeList.get(i);

			if(union(edge.start, edge.end)) {
				ans += edge.weight;

				cost = edge.weight;
			}
		}

		System.out.println(ans - cost);
	}

	public static void make() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;

		parents[bRoot] = aRoot;
		return true;
	}
}
