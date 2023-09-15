package org.example.week_07;

import java.io.*;
import java.util.*;

public class Boj_10800_컬러볼_김태수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Ball[] balls = new Ball[N]; // Create an array of Ball objects
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, value); // Create and store a Ball object
        }

        Arrays.sort(balls, (o1, o2) -> o1.value - o2.value);
        
        int total = 0;
        int[] totalByColor = new int[N + 1];
        int[] answer = new int[N + 1];
        int j = 0;

        for (int i = 0; i < N; i++) {
        	Ball cur = balls[i];
            while (balls[j].value < cur.value) {
                total += balls[j].value;
                totalByColor[balls[j].color] += balls[j].value;
                j++;
            }
            answer[cur.index] = total - totalByColor[cur.color];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(answer[i] + "\n");
        }
        System.out.print(sb.toString());
    }

    static class Ball {
        int index, color, value;

        public Ball(int index, int color, int value) {
            this.index = index;
            this.color = color;
            this.value = value;
        }
    }
}