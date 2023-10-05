package org.example.week_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_15486_퇴사2_김희연 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+1];
        int[] P = new int[N+1];
        int[] dp = new int[N+2];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=N; i++){
            dp[i] = Math.max(dp[i], dp[i-1]);

            int day = T[i] + i;
            if(day <= N+1){
                dp[day] = Math.max(dp[day], dp[i] + P[i]);
            }
        }

        dp[N+1] = Math.max(dp[N+1], dp[N]);

        System.out.println(dp[N+1]);
    }
}