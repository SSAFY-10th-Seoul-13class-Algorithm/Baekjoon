package org.example.week_01;

// 풀이 1 : 216ms, 17608kb
/* 플로이드 워셜
모든 노드를 돌면서,
간선 [i][node] = [node][j] = 1 이면,
간선 [i][j] = 1
--> 모든 노드에 대해, 배열을 모두 탐색해야 하므로 시간복잡도 = O(N^3)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11403_경로찾기_강이규 {

    static int[][] dists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력받기
        int n = Integer.parseInt(br.readLine());

        dists = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dists[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dp
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dists[i][j] < 1 && dists[i][k] + dists[k][j] > 1) { // == 0
                        dists[i][j]++;
                    }
                }
            }
        }
        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(dists[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

//// 개선 내용 : StringTokenizer 삭제, dists 타입 int -> boolean / if문 삭제 -> 196ms, 16588kb
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class Q_11403 {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        // 입력받기
//        int n = Integer.parseInt(br.readLine());
//
//        boolean[][] moveable = new boolean[n][n];
//        for (int i = 0; i < n; i++) {
//            String line = br.readLine();
//            for (int j = 0; j < n; j++) {
//                moveable[i][j] = line.charAt(j*2) != '0';
//            }
//        }
//        // dp
//        for (int k = 0; k < n; k++) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    moveable[i][j] = moveable[i][j] || (moveable[i][k] && moveable[k][j]);
//                }
//            }
//        }
//        // 출력
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                sb.append((moveable[i][j] ? 1 : 0) + " ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
//    }
//}