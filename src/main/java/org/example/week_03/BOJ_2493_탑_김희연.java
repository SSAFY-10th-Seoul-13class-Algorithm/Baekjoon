package org.example.week_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493_탑_김희연 {
    static int[] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        arr = new int[N];

        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()) {
                if(arr[stack.peek()] < arr[i]) {
                    stack.pop();
                } else {
                    sb.append(stack.peek()+1);
                    break;
                }
            }

            if (stack.isEmpty()) {
                sb.append(0);
            }
            stack.push(i);
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
