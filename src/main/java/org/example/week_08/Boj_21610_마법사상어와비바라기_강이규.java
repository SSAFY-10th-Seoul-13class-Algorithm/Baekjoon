package org.example.week_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_21610_마법사상어와비바라기_강이규 {

    static class Cloud {
        int r;
        int c;

        Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N; // map size
    static int M; // 이동 횟수
    static int[][] map;
    static boolean[][] visited;
    static int[][] moves = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; // 왼쪽부터 시계방향
    static List<Cloud> clouds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initClouds();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            visited = new boolean[N][N];
            // 메인 로직
            moveClouds(direction, distance);
            rain();
            copyWater(); // 물 복사 버그
            updateClouds(); // 사용된 구름 삭제 & 새 구름 생성
        }
        System.out.println(getResult());
    }

    private static void initClouds() {
        clouds = new ArrayList<>();
        clouds.add(new Cloud(N-1, 0));
        clouds.add(new Cloud(N-1, 1));
        clouds.add(new Cloud(N-2, 0));
        clouds.add(new Cloud(N-2, 1));
    }

    private static void rain() {
        for (Cloud cl : clouds) {
            map[cl.r][cl.c]++;
            visited[cl.r][cl.c] = true;
        }
    }

    private static void moveClouds(int dir, int dist) {
        for (Cloud cl : clouds) {
            cl.r += moves[dir][0] * dist;
            cl.c += moves[dir][1] * dist;

            if (!inRange(cl.r, cl.c)) {
                // row
                if (cl.r < 0) cl.r = N - 1 - (-cl.r - 1) % N;
                else if (cl.r > N-1) cl.r %= N;
                // col
                if (cl.c < 0) cl.c = N - 1 - (-cl.c - 1) % N;
                else if (cl.c > N-1) cl.c %= N;
            }
        }
    }

    private static int getResult() {
        int result = 0;
        for (int[] row : map) {
            for (int col : row) {
                result += col;
            }
        }
        return result;
    }

    private static void copyWater() {
        // 물이 증가한 칸 정보는 구름 위치에서 가져온다
        for (Cloud cl : clouds) {
            int r = cl.r, c = cl.c;
            for (int d = 1; d < 8; d += 2) {
                int nr = r + moves[d][0];
                int nc = c + moves[d][1];
                if (inRange(nr, nc) && map[nr][nc] > 0)
                    map[r][c]++;
            }
        }
    }

    private static void updateClouds() {
        // 새 구름 생성
        List<Cloud> newClouds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] < 2) continue;
                if (visited[i][j]) continue; // 구름이 사라졌던 칸 제외

                Cloud cloud = new Cloud(i, j);
                map[i][j] -= 2;
                newClouds.add(cloud);
            }
        }
        // 기존 구름 제거(교체)
        clouds = newClouds;
    }

    private static boolean inRange(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < N);
    }
}
