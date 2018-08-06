package Application;

import Network.NetworkLayer;
import Transport.TCP;
import Transport.TCPSegment;
import Transport.TransportLayer;

public class ClientSocket {
	TransportLayer transportlayer = null;
	NetworkLayer networklayer = null;

	String dIP = null;
	String sIP = null;
	int dport = 0;
	int sport = 2001;
	TCP tcp;

	public TCP getTcp() {
		return tcp;
	}

	public void setTcp(TCP tcp) {
		this.tcp = tcp;
	}

	public ClientSocket(String IP, int port) {
		this.sIP = "10.0.0.21"; // 그림은 10.0.0.20이지만 출력예시는 10.0.0.21이다.
		this.dIP = IP;
		this.dport = port;
	}

	public void bind(ServerSocket server) {
		if (server.checkIPandPort(dIP, dport)) { // 연결하려는 서버가 존재하는지 확인
			server.setdIP(sIP); // 서버의 목적지를 클라로 만들어준다
			server.setDport(sport);
			this.transportlayer = new TCP(new TCPSegment(sport, dport));
			this.networklayer = new NetworkLayer();
			this.networklayer.setDatagramHeader(sIP, dIP);

			tcp = new TCP(new TCPSegment(sport, dport));
			tcp.handshake();

			// 1번 상태
			tcp.getTcph().setSYN((short) 1);
			tcp.getTcph().setACK((short) 0);
			networklayer.printDatagram();
			tcp.printHandShake();

			// 서버포트 2번 상태
			server.bind(this);

			// 3번 상태
			tcp.getTcph().setSYN((short) 0);
			tcp.getTcph().setACK((short) 1);
			tcp.getTcph().setSequenceNumber(server.getTcp().getTcph().getAcknowledgementNumber());
			tcp.getTcph().setAcknowledgementNumber(server.getTcp().getTcph().getSequenceNumber() + 1);
			networklayer.printDatagram();
			tcp.printHandShake();

		} else {
			System.out.println("해당 서버가 없습니다.");
			System.exit(0);
		}
	}

	public TransportLayer getTransportlayer() {
		return transportlayer;
	}

	public void setTransportlayer(TransportLayer transportlayer) {
		this.transportlayer = transportlayer;
	}

	public NetworkLayer getNetworklayer() {
		return networklayer;
	}

	public void setNetworklayer(NetworkLayer networklayer) {
		this.networklayer = networklayer;
	}

	public String getdIP() {
		return dIP;
	}

	public void setdIP(String dIP) {
		this.dIP = dIP;
	}

	public String getsIP() {
		return sIP;
	}

	public void setsIP(String sIP) {
		this.sIP = sIP;
	}

	public int getDport() {
		return dport;
	}

	public void setDport(int dport) {
		this.dport = dport;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

}
