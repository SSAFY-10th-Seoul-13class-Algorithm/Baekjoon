package org.example.week_02;

import java.io.*;

public class BOJ_2447_별찍기_김희연 {
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        arr = new char[n][n];

        dfs(0, 0, n, false);

        for(int i=0; i<n; i++){
            bw.write(arr[i]);
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    public static void dfs(int x, int y, int n, boolean blank){
        if(blank) {
            for(int i=x; i<x+n; i++){
                for(int j=y; j<y+n; j++){
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        if(n == 1){
            arr[x][y] = '*';
            return;
        }

        int size = n/3;
        int cnt = 0;
        for(int i=x; i<x+n; i+=size) {
            for(int j=y; j<y+n; j+=size){
                cnt++;
                if(cnt == 5){
                    dfs(i, j, size, true);
                } else{
                    dfs(i, j, size, false);
                }
            }
        }
    }
}
