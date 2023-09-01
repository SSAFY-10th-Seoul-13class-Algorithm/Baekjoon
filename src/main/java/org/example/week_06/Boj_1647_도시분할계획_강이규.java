package org.example.week_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1647_도시분할계획_강이규 {

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int V, E;
    static Edge[] edges;
    static int[] parents;

    static void make() {
        parents = new int[V+1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        if (V == 2) {
            System.out.println(0);
            return;
        }

        edges = new Edge[E+1];
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, cost);
        }

        edges[0] = new Edge(0, 0, Integer.MAX_VALUE); // 정렬 오류 방지. 마지막까지 선택되지 않을 값으로 설정
        Arrays.sort(edges);

        // V개의 정점으로 make set
        make();

        // MST
        long result = 0;
        int count = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                result += e.weight;
                if (++count == V-2) break;
            }
        }
        System.out.println(result);
    }

}