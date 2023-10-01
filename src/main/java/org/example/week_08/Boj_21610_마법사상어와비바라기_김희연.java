package org.example.week_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_21610_마법사상어와비바라기_김희연 {

    static class Cloud {
        int x;
        int y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int n;
    static int[][] arr;
    static boolean[][] visit;
    static Queue<Cloud> clouds = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.add(new Cloud(n - 1, 0));
        clouds.add(new Cloud(n - 1, 1));
        clouds.add(new Cloud(n - 2, 0));
        clouds.add(new Cloud(n - 2, 1));

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            step12(d, s);
            stept34();
            step5();
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void step12(int d, int s) {
        // 구름 이동, 구름 칸 물의 양 1 증가
        for (Cloud cloud : clouds) {
            cloud.x = (n + cloud.x + dx[d] * (s % n)) % n;
            cloud.y = (n + cloud.y + dy[d] * (s % n)) % n;
            arr[cloud.x][cloud.y]++;
        }

    }

    private static void stept34() {
        // 구름이 모두 사라짐
        while (!clouds.isEmpty()) {
            Cloud cloud = clouds.poll();

            //구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함
            visit[cloud.x][cloud.y] = true;

            //물복사버그 마법 시전
            int cnt = 0;

            for (int i = 1; i <= 7; i += 2) {
                int nx = cloud.x + dx[i];
                int ny = cloud.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (arr[nx][ny] > 0)
                        cnt++;
                }

            }
            arr[cloud.x][cloud.y] += cnt;
        }
    }

    private static void step5() {
        //물의 양이 2이상인 칸에 구름 생성, 물의 양 2 감소
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && arr[i][j] >= 2) {
                    clouds.add(new Cloud(i, j));
                    arr[i][j] -= 2;
                }
            }
        }
        visit = new boolean[n][n];
    }
}
