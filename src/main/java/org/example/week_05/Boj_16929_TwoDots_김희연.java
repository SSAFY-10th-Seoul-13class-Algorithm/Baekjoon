package org.example.week_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16929_TwoDots_김희연 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] arr;
    static boolean[][] visit;
    static int N, M;
    static int firstX, firstY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visit = new boolean[N][M];
                firstX = i;
                firstY = j;
                if(dfs(i, j, 1)) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    public static boolean dfs(int x, int y, int count) {
        visit[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[x][y] == arr[nx][ny]) {
                if(visit[nx][ny] == false) {
                    visit[nx][ny] = true;
                    if(dfs(nx, ny, count + 1)) return true;
                } else {
                    if(count >= 4 && firstX == nx && firstY == ny) return true;
                }
            }
        }
        return false;
    }
}
