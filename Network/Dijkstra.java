package Network;

import java.util.ArrayList;

public class Dijkstra {
	ArrayList<String> path; // �ִܰ��
	private int n; // ������ ��
	private int maps[][]; // ���鰣�� ����ġ ������ ����
	int[] parent; // �ִܰ�η� ����� �� �ٷ� �տ� ������� ������ ��´�.
	String[] routerInttoIP; // �迭�� ���ȣ�� �ش� ������� IP �ּ��̴�.

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

		routerInttoIP[0] = "10.0.0.1"; // A�����
		routerInttoIP[1] = "10.0.0.1"; // A�����
		routerInttoIP[2] = "10.0.0.2"; // B�����
		routerInttoIP[3] = "10.0.0.3"; // C�����
		routerInttoIP[4] = "10.1.0.1"; // D�����
		routerInttoIP[5] = "10.1.0.2"; // E�����
		routerInttoIP[6] = "10.1.1.2"; // F�����
		routerInttoIP[7] = "10.2.0.1"; // G�����
		routerInttoIP[8] = "10.2.0.3"; // H�����
		routerInttoIP[9] = "10.3.0.1"; // I�����
		routerInttoIP[10] = "10.3.1.1"; // J�����
		routerInttoIP[11] = "10.1.0.12"; // SERVER1�����
		routerInttoIP[12] = "10.2.0.3"; // SERVER2�����
		routerInttoIP[13] = "10.3.0.9"; // SERVER3�����
	}

	public void input(int i, int j, int w) {
		maps[i][j] = w;
		maps[j][i] = w;
	}

	void computerPaths(int destNum) {
		int distance[] = new int[n + 1]; // �ִ� �Ÿ��� ������ ����
		boolean[] check = new boolean[n + 1]; // �ش� ��带 �湮�ߴ��� üũ�� ����
		parent = new int[n + 1]; // �ִܰ�η� �������� �� �� �ٷ� �� ��带 �����Ѵ�.

		// distance�� �ʱ�ȭ.
		for (int i = 1; i < n + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		// ���۳�尪 �ʱ�ȭ.
		distance[1] = 0;
		check[1] = true;

		// ����� ��� distance����
		for (int i = 1; i < n + 1; i++) {
			if (!check[i] && maps[1][i] != -1) { // �湮 ���ϰ� ����� ����Ͻ�
				distance[i] = maps[1][i];
			}
		}

		for (int a = 0; a < n - 1; a++) {
			int min = Integer.MAX_VALUE;
			int min_index = -1;

			// �ּҰ� ã��(�湮���� �ʰ� ���� ����� ���� �߿��� ���� ���� ����� ��带 ����)
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
				if (!check[i] && maps[min_index][i] != -1) { // ���� �湮���ϰ� ����� ����Ͻ�
					if (distance[i] > distance[min_index] + maps[min_index][i]) { // �ٸ� �ּҰ�θ� ã�Ҵ�.
						distance[i] = distance[min_index] + maps[min_index][i];
						parent[i] = min_index;
					}
				}
			}
		}



		path.add(routerInttoIP[destNum]); // ������ IP�� ���� ���
		int nextNum = parent[destNum];
		while (nextNum != 0) { // ������ ��尡 �ƴ϶�� ����Ѵ�.
			path.add(routerInttoIP[nextNum]); // �θ� ����� IP�� ���ʴ�� ����ش�.
			nextNum = parent[nextNum];
		}
		path.add(routerInttoIP[0]); // ���������� ������ ��带 ����ش�.

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
