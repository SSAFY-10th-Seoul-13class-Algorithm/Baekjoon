package org.example.week_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_3665_최종순위_김태수 {
	static int size, inDegree[];
	static boolean map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc = 1; tc <= testCase ; tc++) {
			size = Integer.parseInt(br.readLine());
			map = new boolean[size+1][size+1];
			st = new StringTokenizer(br.readLine());
			inDegree = new int[size+1];
			
            //inDegree와 map 초기화: 먼저 들어온 순서대로 낮은 위상을 부여
			for(int i = 0 ; i < size;i++) {
				int num = Integer.parseInt(st.nextToken());
				inDegree[num] = i;
                //자신보다 높은 위상의 번호는 길이 있다고 처리
				for(int j = 1; j <= size;j++) {
					if(j != num && !map[j][num]) map[num][j] = true;
				}
			}  
			
			int N = Integer.parseInt(br.readLine());
			
			for(int i = 0;i < N;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
                //순서를 바꿈. 이때, inDegree도 같이 갱신
				if(map[from][to]) {
					map[from][to] = false;
					map[to][from] = true;
					inDegree[from]++;
					inDegree[to]--;
				}
				else {
					map[to][from] = false;
					map[from][to] = true;
					inDegree[to]++;
					inDegree[from]--;
				}
				
			}
			String answer = topologySort();
			System.out.println(answer);
		}
	}
	
	public static String topologySort() {
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= size; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}
		
		for (int i = 1; i <= size; i++) {
            //위상이 1이상 차이나는 곳에서는 사이클이 발생
			if (queue.size() == 0)
				return "IMPOSSIBLE";

            // 위상이 겹치는 곳이 있다면, 어떤 번호가 우선순위인지 모름.. 맞나?
			if (queue.size() > 1)
				return "?";
			int cur = queue.poll();
			sb.append(cur + " ");

			for (int j = 1; j <= size; j++) {
				if (map[cur][j]) {
					map[cur][j] = false;
					if (--inDegree[j] == 0)
						queue.offer(j);
				}
			}
		}
		return sb.toString();
	}
}
