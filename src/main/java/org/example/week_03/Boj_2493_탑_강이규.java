package org.example.week_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_2493_탑_강이규 {
    static int n;
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] output = solve();
        // 출력
        for (int i : output) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static int[] solve() {
        Stack<int[]> s = new Stack<>(); // (value, idx)
        int[] output = new int[n];

        int i = n-1;
        while (i >= 0) {
            if (!s.isEmpty() && input[i] >= s.peek()[0]) {
                output[s.pop()[1]] = i+1; // 이미 지난 자리만 변경된다.
            } else {
                s.push(new int[] {input[i], i});
                i--;
            }
        }
        return output;
    }
}
