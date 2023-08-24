package org.example.week_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_3649_로봇프로젝트_강이규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String xSrc;
        while (true) {
            int x;
            // 입력
            try {
                xSrc = br.readLine();
                x = Integer.parseInt(xSrc) * 10_000_000;
            } catch (Exception e) {
                break;
            }
            int n = Integer.parseInt(br.readLine());
            int[] legos = new int[n];

            for (int i = 0; i < n; i++) {
                legos[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(legos);

            // 로직 실행
            int left = 0;
            int right = n-1;

            while (left < right) { // 인덱스 같은 경우 X
                int sum = legos[left] + legos[right];

                if (sum < x) left++;
                else if (sum > x) right--;
                else break;
            }
            if (left < right) sb.append("yes ").append(legos[left]).append(" ").append(legos[right]).append("\n");
            else sb.append("danger").append("\n");
        }
        System.out.println(sb);
    }

}