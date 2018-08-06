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

		// 연결할 서버 IP, port 번호 입력받기
		System.out.printf("Input a Server IP to connect:");
		String destIP = new Scanner(System.in).nextLine();
		ClientSocket client = new ClientSocket(destIP, 2000);

		// 내가 연결하고자 하는 서버 찾기
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
			System.out.println("해당 서버없음");
			return;
		}

		// 라우터A 만들기
		RoutingTable rt = new RoutingTable(4);
		rt.add(0, "10.0.0.3", 2);
		rt.add(1, "10.1.0.1", 5);
		rt.add(2, "10.0.0.2", 1);
		rt.add(3, client.getsIP(), 0); // 클라이언트 IP
		Router A = new Router("10.0.0.1", rt);

		// 라우터B 만들기
		rt = new RoutingTable(3);
		rt.add(0, "10.0.0.1", 1);
		rt.add(1, "10.1.0.1", 5);
		rt.add(2, "10.2.0.1", 10);
		Router B = new Router("10.0.0.2", rt);

		// 라우터C 만들기
		rt = new RoutingTable(2);
		rt.add(0, "10.0.0.1", 2);
		rt.add(1, "10.1.0.2", 5);
		Router C = new Router("10.0.0.3", rt);

		// 라우터D 만들기
		rt = new RoutingTable(3);
		rt.add(0, "10.0.0.1", 5);
		rt.add(1, "10.1.0.2", 5);
		rt.add(2, "10.0.0.2", 1);
		Router D = new Router("10.1.0.1", rt);

		// 라우터E 만들기
		rt = new RoutingTable(4);
		rt.add(0, "10.0.0.3", 5);
		rt.add(1, "10.1.0.1", 1);
		rt.add(2, "10.1.1.2", 3);
		rt.add(3, server1.getsIP(), 0); // 서버 IP
		Router E = new Router("10.1.0.2", rt);

		// 라우터F 만들기
		rt = new RoutingTable(3);
		rt.add(0, "10.1.0.2", 3);
		rt.add(1, "10.2.0.3", 5);
		rt.add(2, "10.3.0.1", 8);
		Router F = new Router("10.1.1.2", rt);

		// 라우터G 만들기
		rt = new RoutingTable(4);
		rt.add(0, "10.0.0.2", 10);
		rt.add(1, "10.2.0.3", 2);
		rt.add(2, "10.3.1.1", 5);
		rt.add(3, server2.getsIP(), 0); // 서버 IP
		Router G = new Router("10.2.0.1", rt);

		// 라우터H 만들기
		rt = new RoutingTable(2);
		rt.add(0, "10.1.1.2", 5);
		rt.add(1, "10.2.0.1", 2);
		Router H = new Router("10.2.0.3", rt);

		// 라우터I 만들기
		rt = new RoutingTable(3);
		rt.add(0, "10.1.1.2", 8);
		rt.add(1, "10.3.1.1", 3);
		rt.add(2, server3.getsIP(), 0); // 서버 IP
		Router I = new Router("10.3.0.1", rt);

		// 라우터J 만들기
		rt = new RoutingTable(2);
		rt.add(0, "10.2.0.1", 5);
		rt.add(1, "10.3.0.1", 3);
		Router J = new Router("10.3.1.1", rt);

		// 경로비용에 대한 정보 넣어주기
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

		dijkstra.computerPaths(serverNum); // 최단경로 비용 출력
		dijkstra.getShortestPath();

		client.bind(server); // 바인딩(서버와 연결 및 핸드쉐이크)

		System.out.printf("Input a message to transport:");
		String inputMsg = new Scanner(System.in).nextLine(); //
		client.getTcp().getTcph().setACK((short) 0); // flag 원상복구

		for (int i = 0; i < inputMsg.length() / 5.0; i++) {
			client.getTcp().getTcph().setSequenceNumber(client.getTcp().getTcph().getSequenceNumber() + 1);
			client.getTcp().getTcph()
					.setAcknowledgementNumber(client.getTcp().getTcph().getAcknowledgementNumber() + 1);
			if (i == inputMsg.length() / 5) {
				client.getNetworklayer().printDatagram();
				// client.getTcp().printTCPSegment(inputMsg.substring(5 * i,
				// inputMsg.length()));

				TCPSegment segment = new TCPSegment(client.getSport(), client.getDport()); // segment만들어서
				segment.getMessage().setMessage(inputMsg.substring(5 * i, inputMsg.length())); // message를 담아준다.
				client.getNetworklayer().setDatagramData(segment); // datagram으로 만들어주고
				// client.getTcp().printTCPSegment(client.getNetworklayer().getDgram().getDatagramData().getDatagramData()
				// .getMessage().getMessage()); // datagramData에 들어있는 message를 인자로 넘겨서
				// TCPSegment 정보와 함께 출력해준다.
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
			client.getTcp().printTCPSegment(segment.getMessage().getMessage()); // datagram까지 들어가서 getMessage를 해주면 null
																				// 값으로 자동 세팅되서 그냥 TCPSegment에서 바로 해줍니다.
		}

	}

}
