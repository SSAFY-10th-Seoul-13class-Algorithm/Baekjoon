package org.example.week_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2447_별찍기10_강이규 {

    static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int range = Integer.parseInt(br.readLine());

        stars = new char[range][range];

        draw(0, 0, range, true);

        for (char[] cArr : stars) {
            for (char c : cArr) {
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void draw(int sr, int sc, int range, boolean drawable) {
        int SC = sc;
        if (range == 1) {
            stars[sr][sc] = drawable ? '*' : ' ';
            return;
        }
        range /= 3;
        for (int i = 0; i < 9; i++) { // 줄바꿈
            if (i == 2 || i == 5) {
                draw(sr, sc, range, drawable);
                sr += range;
                sc = SC;
            }
            else if (i == 4) { // 빈칸
                draw(sr, sc, range, false);
                sc += range;
            }
            else {
                draw(sr, sc, range, drawable);
                sc += range;
            }
        }
    }
}
