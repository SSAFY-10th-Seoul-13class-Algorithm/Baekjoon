package org.example.week_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_16947_서울지하철2호선_김희연 {
    static int N;
    static ArrayList<Integer>[] list;
    static boolean[] visit;
    static boolean[] cycle;
    static int[] ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        visit = new boolean[N + 1];
        cycle = new boolean[N + 1];

        // 순환선이 있는지 확인
        for(int i = 1; i <= N; i++){
            if(isCycle(i, -1, i)){ // 순환선을 찾으면 탐색 중지
                break;
            } else {
                Arrays.fill(visit, false); // 방문 기록 초기화
            }
        }

        ans = new int[N +1];
        bfs();  // 각 역에서 순환선까지의 거리 계산

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
        br.close();
    }

    // 순환선과 각 역 사이의 거리를 계산하는 함수
    private static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(visit, false);
        for(int i = 1; i <= N; i++){
            if(cycle[i]) {  // 순환선에 속하는 역을 시작점으로 큐에 추가
                visit[i] = true;
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : list[u]){
                if(!visit[v]){
                    q.offer(v);
                    visit[v] = true;
                    ans[v] = ans[u]+1;  // 거리 증가
                }
            }
        }
    }

    private static boolean isCycle(int u, int parent, int start){
        visit[u] = true;
        for(int v : list[u]){
            if(!visit[v]){
                if(isCycle(v, u, start)){   // 새로운 역을 탐색하며 순환선 찾기
                    cycle[v] = true;
                    return true;
                }
            } else if(v != parent && v == start){   //시작역을 다시 만났을 때 순환선 찾음
                cycle[v] = true;
                return true;
            }
        }
        return false;
    }
}