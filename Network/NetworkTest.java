package Network;

import java.util.Scanner;

import Application.ClientSocket;
import Application.ServerSocket;
import Transport.Segment;
import Transport.TCPSegment;

public class NetworkTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket server1 = new ServerSocket("10.1.0.12", 2000);
		ServerSocket server2 = new ServerSocket("10.2.0.3", 2000);
		ServerSocket server3 = new ServerSocket("10.3.0.9", 2000);

		System.out.println("********** Server1-IP: " + server1.getsIP() + "**********");
		System.out.println("********** Server2-IP: " + server2.getsIP() + "**********");
		System.out.println("********** Server3-IP: " + server3.getsIP() + "**********");

		// ������ ���� IP, port ��ȣ �Է¹ޱ�
		System.out.printf("Input a Server IP to connect:");
		String destIP = new Scanner(System.in).nextLine();
		ClientSocket client = new ClientSocket(destIP, 2000);

		// ���� �����ϰ��� �ϴ� ���� ã��
		ServerSocket server = null;
		int serverNum = -1;
		if (server1.getsIP().equals(destIP)) {
			server = server1;
			serverNum = 11;
		} else if (server2.getsIP().equals(destIP)) {
			server = server2;
			serverNum = 12;
		} else if (server3.getsIP().equals(destIP)) {
			server = server3;
			serverNum = 13;
		} else {
			server = new ServerSocket(destIP, 2000);
		}

		if (serverNum == -1) {
			System.out.println("�ش� ��������");
			return;
		}

		// �����A �����
		RoutingTable rt = new RoutingTable(4);
		rt.add(0, "10.0.0.3", 2);
		rt.add(1, "10.1.0.1", 5);
		rt.add(2, "10.0.0.2", 1);
		rt.add(3, client.getsIP(), 0); // Ŭ���̾�Ʈ IP
		Router A = new Router("10.0.0.1", rt);

		// �����B �����
		rt = new RoutingTable(3);
		rt.add(0, "10.0.0.1", 1);
		rt.add(1, "10.1.0.1", 5);
		rt.add(2, "10.2.0.1", 10);
		Router B = new Router("10.0.0.2", rt);

		// �����C �����
		rt = new RoutingTable(2);
		rt.add(0, "10.0.0.1", 2);
		rt.add(1, "10.1.0.2", 5);
		Router C = new Router("10.0.0.3", rt);

		// �����D �����
		rt = new RoutingTable(3);
		rt.add(0, "10.0.0.1", 5);
		rt.add(1, "10.1.0.2", 5);
		rt.add(2, "10.0.0.2", 1);
		Router D = new Router("10.1.0.1", rt);

		// �����E �����
		rt = new RoutingTable(4);
		rt.add(0, "10.0.0.3", 5);
		rt.add(1, "10.1.0.1", 1);
		rt.add(2, "10.1.1.2", 3);
		rt.add(3, server1.getsIP(), 0); // ���� IP
		Router E = new Router("10.1.0.2", rt);

		// �����F �����
		rt = new RoutingTable(3);
		rt.add(0, "10.1.0.2", 3);
		rt.add(1, "10.2.0.3", 5);
		rt.add(2, "10.3.0.1", 8);
		Router F = new Router("10.1.1.2", rt);

		// �����G �����
		rt = new RoutingTable(4);
		rt.add(0, "10.0.0.2", 10);
		rt.add(1, "10.2.0.3", 2);
		rt.add(2, "10.3.1.1", 5);
		rt.add(3, server2.getsIP(), 0); // ���� IP
		Router G = new Router("10.2.0.1", rt);

		// �����H �����
		rt = new RoutingTable(2);
		rt.add(0, "10.1.1.2", 5);
		rt.add(1, "10.2.0.1", 2);
		Router H = new Router("10.2.0.3", rt);

		// �����I �����
		rt = new RoutingTable(3);
		rt.add(0, "10.1.1.2", 8);
		rt.add(1, "10.3.1.1", 3);
		rt.add(2, server3.getsIP(), 0); // ���� IP
		Router I = new Router("10.3.0.1", rt);

		// �����J �����
		rt = new RoutingTable(2);
		rt.add(0, "10.2.0.1", 5);
		rt.add(1, "10.3.0.1", 3);
		Router J = new Router("10.3.1.1", rt);

		// ��κ�뿡 ���� ���� �־��ֱ�
		Dijkstra dijkstra = new Dijkstra(13);
		dijkstra.input(1, 2, 1);
		dijkstra.input(1, 3, 2);
		dijkstra.input(1, 4, 5);
		dijkstra.input(2, 4, 5);
		dijkstra.input(2, 7, 10);
		dijkstra.input(3, 5, 5);
		dijkstra.input(4, 5, 1);
		dijkstra.input(5, 6, 3);
		dijkstra.input(5, 11, 0);
		dijkstra.input(6, 8, 5);
		dijkstra.input(6, 9, 8);
		dijkstra.input(7, 10, 5);
		dijkstra.input(7, 12, 0);
		dijkstra.input(9, 13, 0);
		dijkstra.input(9, 10, 3);

		dijkstra.computerPaths(serverNum); // �ִܰ�� ��� ���
		dijkstra.getShortestPath();

		client.bind(server); // ���ε�(������ ���� �� �ڵ彦��ũ)

		System.out.printf("Input a message to transport:");
		String inputMsg = new Scanner(System.in).nextLine(); //
		client.getTcp().getTcph().setACK((short) 0); // flag ���󺹱�

		for (int i = 0; i < inputMsg.length() / 5.0; i++) {
			client.getTcp().getTcph().setSequenceNumber(client.getTcp().getTcph().getSequenceNumber() + 1);
			client.getTcp().getTcph()
					.setAcknowledgementNumber(client.getTcp().getTcph().getAcknowledgementNumber() + 1);
			if (i == inputMsg.length() / 5) {
				client.getNetworklayer().printDatagram();
				// client.getTcp().printTCPSegment(inputMsg.substring(5 * i,
				// inputMsg.length()));

				TCPSegment segment = new TCPSegment(client.getSport(), client.getDport()); // segment����
				segment.getMessage().setMessage(inputMsg.substring(5 * i, inputMsg.length())); // message�� ����ش�.
				client.getNetworklayer().setDatagramData(segment); // datagram���� ������ְ�
				// client.getTcp().printTCPSegment(client.getNetworklayer().getDgram().getDatagramData().getDatagramData()
				// .getMessage().getMessage()); // datagramData�� ����ִ� message�� ���ڷ� �Ѱܼ�
				// TCPSegment ������ �Բ� ������ش�.
				client.getTcp().printTCPSegment(segment.getMessage().getMessage());
				break;
			}
			client.getNetworklayer().printDatagram();
			// client.getTcp().printTCPSegment(inputMsg.substring(5 * i, 5 * (i + 1)));

			TCPSegment segment = new TCPSegment(client.getSport(), client.getDport());
			segment.getMessage().setMessage(inputMsg.substring(5 * i, 5 * (i + 1)));
			client.getNetworklayer().setDatagramData(segment);

			// client.getTcp().printTCPSegment(
			// client.getNetworklayer().getDgram().getDatagramData().getDatagramData().getMessage().getMessage());
			client.getTcp().printTCPSegment(segment.getMessage().getMessage()); // datagram���� ���� getMessage�� ���ָ� null
																				// ������ �ڵ� ���õǼ� �׳� TCPSegment���� �ٷ� ���ݴϴ�.
		}

	}

}
