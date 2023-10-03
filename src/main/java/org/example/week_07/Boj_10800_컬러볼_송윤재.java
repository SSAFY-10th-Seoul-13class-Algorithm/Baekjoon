package org.example.week_07;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_10800_컬러볼_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, result[];
    static Ball balls[];
    
    static class Ball implements Comparable<Ball>{
    	int num, color, size;

		public Ball(int num, int color, int size) {
			this.num = num;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			return Integer.compare(this.size, o.size);
		}
    }
    
    static void init() {
    	balls = new Ball[N + 1];
    	result = new int[N + 1];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	balls[0] = new Ball(0, 0, 0); // 정렬 시 오류 해결을 위한 0번 인덱스
    	for(int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int color = Integer.parseInt(st.nextToken());
    		int size = Integer.parseInt(st.nextToken());
    		balls[i] = new Ball(i, color, size);
    	}
    }
    
    static void solve() {
    	Arrays.sort(balls); // 크기 순 정렬
    	int[] color_sum = new int[N + 1]; // 색깔별 누적합
    	int sum = 0;
    	int idx = 1; // 비교할 공의 인덱스
    	for (int i = 1; i < N + 1; i++) {
			Ball cur = balls[i];
			while(balls[idx].size < cur.size) {
				sum += balls[idx].size;
				color_sum[balls[idx].color] += balls[idx].size;
				idx++;
			}
			result[cur.num] = sum - color_sum[cur.color]; // 전체 누적합에서 현재 색의 누적합을 뺀 값이 곧 결과
		}
    	
    	for(int i = 1; i < N + 1; i++) {
    		sb.append(result[i]).append("\n");
    	}
    }
    
	public static void main(String[] args) throws IOException{
		input();
        solve();
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}