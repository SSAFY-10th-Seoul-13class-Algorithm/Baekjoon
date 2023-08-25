package org.example.week_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_16947_서울지하철2호선_김태수 {
	static int n;
	static LinkedList[] map;
	static boolean onCycle[], flag, visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());	//역의 개수
		map = new LinkedList[n+1];				//역끼리의 연결 여부를 담을 리스트 map[from] = to
		onCycle = new boolean[n+1];				//역이 사이클 안에 있는지 확인하는 배열: 인덱스의 해당하는 역이 사이클 안에 있으면 true
		visited = new boolean[n+1];				//탐색시 방문여부를 체크하는 배열
		StringTokenizer st;
		for(int i = 0; i < n+1;i++) {			//역간의 연결 여부를 담아줍니다.
			map[i] = new LinkedList<>();
		}
		
		for(int i = 0; i<n ;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			//양방향으로 연결합니다
			map[from].add(to);
			map[to].add(from);
		}
		
		for(int i = 1 ;i <= n;i++) {
			//dfs에서는 해당 역에서 출발해서 다시 돌아올 수 있는지를 판단하는 탐색을 합니다. 만약 돌고 돌아서 자신으로 돌아올 경우, 사이클로 판단합니다.
			if(dfs(i,i,0)) break;
			//만약 위에서 break 되지 않았다면 사이클이 없는 경우로, onCycle을 초기화 하고 다음 역으로 넘어갑니다.
			onCycle = new boolean[n+1];
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n;i++) {
			//모든 역마다 depth를 구해줍니다: onCycle에서 true인 역과 얼마나 떨어져있는지 계산합니다.
			sb.append(findDepth(i,0)).append(" ");
			//findDepth시 사용된 visited를 초기화 합니다.
			visited = new boolean[n+1];
		}
		System.out.println(sb.toString());
	}
	public static boolean dfs(int cur, int init, int cnt) {
		onCycle[cur] = true;
		//앞으로 나갈 수 없다면 사이클이 되지 않으므로 false
		if(map[cur].size() == 1) return false;
		for(int i = 0, end = map[cur].size(); i < end; i++) {
			int adj = (int) map[cur].get(i);
			//앞으로 나갈 역이 처음 역이라면 && 앞으로 갔다 뒤로 돌아오는 경우가 아니라면 true
			if(adj == init && cnt >= 2) {
				//System.out.println("hit");
				return true;
			}
			if(onCycle[adj]) continue;
			onCycle[adj] = true;
			//한 역 앞으로 나가면서 cnt+1해줍니다. 만약 false를 리턴한다면 그 루트는 사이클이 아니므로 onCycle을 false로 바꿔줍니다.
			if(!dfs(adj, init, cnt+1)) {
				onCycle[adj] = false;
			}
			else return true;
		}
		return false;
	}
	public static int findDepth(int cur, int depth) {
		visited[cur] = true;
		//if(map[cur].size() == 1) return 0;
		if(onCycle[cur]) return depth;
		for(int i = 0, end = map[cur].size(); i<end;i++) {
			int adj = (int) map[cur].get(i);
			if(visited[adj]) continue;
			int result =findDepth(adj,depth+1);
			if(result != 0) return result;
		}
		return 0;
	}
}