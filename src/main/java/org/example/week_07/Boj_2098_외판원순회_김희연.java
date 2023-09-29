package org.example.week_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2098_외판원순회_김희연 {
    static int N;
    static int INF = 11000000;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][(1<<N)-1];
        for(int i=0; i<N; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(dfs(0, 1));
    }

    static int dfs(int cur, int visit){

        //모든 도시를 방문했을 경우
        if(visit == (1<<N)-1){
            if(arr[cur][0] == 0) return INF;
            return arr[cur][0]; //현재 도시에서 시작 도시까지의 이동 거리
        }

        if(dp[cur][visit] != -1) //dp에 값이 존재하면 해당 값을 리턴
            return dp[cur][visit];
        dp[cur][visit] = INF;

        for(int i=0; i<N; i++){
            if((visit & (1<<i)) == 0 && arr[cur][i] != 0){
                dp[cur][visit] = Math.min(dfs(i, visit | (1 << i)) + arr[cur][i], dp[cur][visit]);
            }
        }
        return dp[cur][visit];
    }
}
