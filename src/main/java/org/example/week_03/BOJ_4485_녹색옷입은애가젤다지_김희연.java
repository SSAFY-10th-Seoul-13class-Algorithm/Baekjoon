package org.example.week_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
    int x, y, cost;

    public Point(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Point o) {
        return this.cost - o.cost;
    }
}

public class BOJ_4485_녹색옷입은애가젤다지_김희연 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static int[][] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());

            if(N == 0) break;

            map = new int[N][N];
            num = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    num[i][j] = Integer.MAX_VALUE;
                }
            }

            sb.append("Problem " + (T++) + ": " + dijkstra() + "\n");
        }

        System.out.println(sb);
    }

    public static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        num[0][0] = map[0][0];
        pq.offer(new Point(0, 0, num[0][0]));

        while(!pq.isEmpty()){
            Point point = pq.poll();
            int x = point.x;
            int y = point.y;

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N){
                    continue;
                }

                if(num[nx][ny] > num[x][y] + map[nx][ny]){
                    num[nx][ny] = num[x][y] + map[nx][ny];
                    pq.offer(new Point(nx, ny, num[nx][ny]));
                }
            }
        }
        return num[N-1][N-1];
    }
}
