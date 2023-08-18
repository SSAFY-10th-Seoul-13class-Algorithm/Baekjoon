package org.example.week_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node{
    int e;
    int cost;

    Node(int e, int cost){
        this.e = e;
        this.cost = cost;
    }
}

public class Boj_1167_트리의지름_김희연 {
    static List<Node>[] list;
    static boolean[] visit;
    static int max = 0;
    static int node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(br.readLine());

        list = new ArrayList[V+1];

        for(int i=1; i<=V; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<V; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            while(true){
                int e = Integer.parseInt(st.nextToken());
                if(e == -1)
                    break;
                int cost = Integer.parseInt(st.nextToken());
                list[num].add(new Node(e, cost));
            }
        }

        visit = new boolean[V+1];
        dfs(1, 0);

        visit = new boolean[V+1];
        dfs(node, 0);

        System.out.println(max);
    }

    public static void dfs(int x, int len){
        if(len > max){
            max = len;
            node = x;
        }

        visit[x] = true;

        for(int i=0; i<list[x].size(); i++){
            Node n = list[x].get(i);
            if(!visit[n.e]){
                dfs(n.e, len+n.cost);
            }
        }
    }
}
