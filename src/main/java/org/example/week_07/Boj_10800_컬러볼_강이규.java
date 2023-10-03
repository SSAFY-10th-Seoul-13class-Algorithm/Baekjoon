package org.example.week_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_10800_컬러볼_강이규 {

    static int N;
    static int[][] arr;
    static int[] sums;

    static int[] result;
    static int totalSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sums = new int[N+1];
        totalSum = 0;
        arr = new int[N+1][3]; // color, size, idx
        result = new int[N+1];

        for (int i = 1, end = N+1; i < end; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            arr[i][0] = color;
            arr[i][1] = size;
            arr[i][2] = i;
        }
        // sort
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        // max O(2N)
        Map<Integer, Integer> sameSizeCnt = new HashMap<>(); // color, cnt
        for (int i = 1, end = N+1; i < end; i++) {
            int color = arr[i][0];
            int size = arr[i][1];
            int prevSize = arr[i-1][1];

            if (size != prevSize) {
                sameSizeCnt.forEach((k, v) -> {
                    int val = v * prevSize;
                    sums[k] += val;
                    totalSum += val;
                });
                sameSizeCnt = new HashMap<>();
            }
            Integer val = sameSizeCnt.get(color);
            sameSizeCnt.put(color, (val != null) ? val+1 : 1);
            result[arr[i][2]] = totalSum - sums[color];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1, end = N+1; i < end; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.println(sb);
    }
}