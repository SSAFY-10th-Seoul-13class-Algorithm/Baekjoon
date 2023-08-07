package org.example.week_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819_차이를최대로_김희연 {
    static int[] arr;
    static boolean[] visit;
    static int n;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[n];
        visit = new boolean[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, new int[n]);
        System.out.println(max);
    }

    public static void dfs(int L, int[] A) {
        if(L == n) {
            int answer = 0;
            for(int i=0; i<n-1; i++) {
                answer += Math.abs(A[i] - A[i+1]);
            }
            max = Math.max(answer, max);
            return;
        }

        for(int i=0; i<n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                A[L] = arr[i];
                dfs(L+1, A);
                visit[i] = false;
            }
        }
    }
}

