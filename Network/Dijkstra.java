package Network;

import java.util.ArrayList;

public class Dijkstra {
	ArrayList<String> path; // 최단경로
	private int n; // 노드들의 수
	private int maps[][]; // 노드들간의 가중치 저장할 변수
	int[] parent; // 최단경로로 진행시 내 바로 앞에 라우터의 정보를 담는다.
	String[] routerInttoIP; // 배열의 방번호는 해당 라우터의 IP 주소이다.

	public Dijkstra(int n) {
		path = new ArrayList();
		this.n = n;
		this.maps = new int[n + 1][n + 1];
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				maps[i][j] = -1;
			}
		}
		routerInttoIP = new String[n + 1];

		routerInttoIP[0] = "10.0.0.1"; // A라우터
		routerInttoIP[1] = "10.0.0.1"; // A라우터
		routerInttoIP[2] = "10.0.0.2"; // B라우터
		routerInttoIP[3] = "10.0.0.3"; // C라우터
		routerInttoIP[4] = "10.1.0.1"; // D라우터
		routerInttoIP[5] = "10.1.0.2"; // E라우터
		routerInttoIP[6] = "10.1.1.2"; // F라우터
		routerInttoIP[7] = "10.2.0.1"; // G라우터
		routerInttoIP[8] = "10.2.0.3"; // H라우터
		routerInttoIP[9] = "10.3.0.1"; // I라우터
		routerInttoIP[10] = "10.3.1.1"; // J라우터
		routerInttoIP[11] = "10.1.0.12"; // SERVER1라우터
		routerInttoIP[12] = "10.2.0.3"; // SERVER2라우터
		routerInttoIP[13] = "10.3.0.9"; // SERVER3라우터
	}

	public void input(int i, int j, int w) {
		maps[i][j] = w;
		maps[j][i] = w;
	}

	void computerPaths(int destNum) {
		int distance[] = new int[n + 1]; // 최단 거리를 저장할 변수
		boolean[] check = new boolean[n + 1]; // 해당 노드를 방문했는지 체크할 변수
		parent = new int[n + 1]; // 최단경로로 진행했을 때 내 바로 전 노드를 저장한다.

		// distance값 초기화.
		for (int i = 1; i < n + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		// 시작노드값 초기화.
		distance[1] = 0;
		check[1] = true;

		// 연결된 노드 distance갱신
		for (int i = 1; i < n + 1; i++) {
			if (!check[i] && maps[1][i] != -1) { // 방문 안하고 연결된 노드일시
				distance[i] = maps[1][i];
			}
		}

		for (int a = 0; a < n - 1; a++) {
			int min = Integer.MAX_VALUE;
			int min_index = -1;

			// 최소값 찾기(방문하지 않고 현재 연결된 노드들 중에서 가장 작은 비용인 노드를 고른다)
			for (int i = 1; i < n + 1; i++) {
				if (!check[i] && distance[i] != Integer.MAX_VALUE) {
					if (distance[i] < min) {
						min = distance[i];
						min_index = i;
					}
				}
			}

			check[min_index] = true;
			for (int i = 1; i < n + 1; i++) {
				if (!check[i] && maps[min_index][i] != -1) { // 아직 방문안하고 연결된 노드일시
					if (distance[i] > distance[min_index] + maps[min_index][i]) { // 다른 최소경로를 찾았다.
						distance[i] = distance[min_index] + maps[min_index][i];
						parent[i] = min_index;
					}
				}
			}
		}



		path.add(routerInttoIP[destNum]); // 도착지 IP를 먼저 담고
		int nextNum = parent[destNum];
		while (nextNum != 0) { // 시작점 노드가 아니라면 계속한다.
			path.add(routerInttoIP[nextNum]); // 부모 노드의 IP를 차례대로 담아준다.
			nextNum = parent[nextNum];
		}
		path.add(routerInttoIP[0]); // 마지막으로 시작점 노드를 담아준다.

	}

	ArrayList<String> getShortestPath() {
		System.out.print("Path: [");
		for (int i = 0; i < path.size(); i++) {
			System.out.print(path.get(path.size() - i - 1));
			if (i != path.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		return path;
	}

}
