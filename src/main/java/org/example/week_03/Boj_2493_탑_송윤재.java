package org.example.week_03;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_2493_탑_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, top[], lazer[];
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	top = new int[N + 1];
    	lazer = new int[N + 1];
    	top[0] = Integer.MAX_VALUE;
    	for(int i = 1; i <= N; i++) {
    		top[i] = Integer.parseInt(st.nextToken());
    	}
    }
    
    static void solve() {
    	Stack<Integer> stack = new Stack<>();
    	stack.add(0);
    	for(int i = 1; i <= N; i++) {
    		while(top[stack.peek()] < top[i]) {
    			stack.pop();
			}
    		lazer[i] = stack.peek();
			stack.add(i);
    	}
    	for(int i = 1; i <= N; i++) {
    		sb.append(lazer[i]).append(" ");
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

