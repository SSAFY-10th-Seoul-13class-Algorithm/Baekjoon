package org.example.week_06;

import java.io.*;
import java.util.*;

public class Boj_3665_최종순위_김희연 {
	static int N, M;
	static ArrayList<Integer>[] adjList;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[] lastYear = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				lastYear[i] = Integer.parseInt(st.nextToken());
			}

			// 그래프 초기화
			adjList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}
			inDegree = new int[N + 1];

			// 작년 순위를 바탕으로 그래프 생성
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					adjList[lastYear[i]].add(lastYear[j]);
					inDegree[lastYear[j]]++;
				}
			}

			// 순위 변경 정보에 따라 그래프 갱신
			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a가 b보다 앞에 있다면 순서 변경
				if (adjList[a].contains(b)) {
					adjList[a].remove((Integer) b);
					adjList[b].add(a);
					inDegree[b]--;
					inDegree[a]++;
				} else {
					adjList[b].remove((Integer) a);
					adjList[a].add(b);
					inDegree[a]--;
					inDegree[b]++;
				}
			}


			ArrayList<Integer> result = topologySort();


			if (result.size() == N) {
				for (int i : result) {
					System.out.print(i + " ");
				}
				System.out.println();
			} else if (result.size() > N) {
				System.out.println("?");
			} else {
				System.out.println("IMPOSSIBLE");
			}
		}
	}

	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<Integer>();

		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}

		while (!queue.isEmpty()) {
			if (queue.size() > 1) { // 불확실한 순위
				orderList.add(N + 1); // 임의의 값을 추가하여 크기 증가
				break;
			}

			int cur = queue.poll();
			orderList.add(cur);

			for (int next : adjList[cur]) {
				if (--inDegree[next] == 0)
					queue.offer(next);
			}
		}

		return orderList;
	}
}
