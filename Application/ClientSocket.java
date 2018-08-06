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
		this.sIP = "10.0.0.21"; // �׸��� 10.0.0.20������ ��¿��ô� 10.0.0.21�̴�.
		this.dIP = IP;
		this.dport = port;
	}

	public void bind(ServerSocket server) {
		if (server.checkIPandPort(dIP, dport)) { // �����Ϸ��� ������ �����ϴ��� Ȯ��
			server.setdIP(sIP); // ������ �������� Ŭ��� ������ش�
			server.setDport(sport);
			this.transportlayer = new TCP(new TCPSegment(sport, dport));
			this.networklayer = new NetworkLayer();
			this.networklayer.setDatagramHeader(sIP, dIP);

			tcp = new TCP(new TCPSegment(sport, dport));
			tcp.handshake();

			// 1�� ����
			tcp.getTcph().setSYN((short) 1);
			tcp.getTcph().setACK((short) 0);
			networklayer.printDatagram();
			tcp.printHandShake();

			// ������Ʈ 2�� ����
			server.bind(this);

			// 3�� ����
			tcp.getTcph().setSYN((short) 0);
			tcp.getTcph().setACK((short) 1);
			tcp.getTcph().setSequenceNumber(server.getTcp().getTcph().getAcknowledgementNumber());
			tcp.getTcph().setAcknowledgementNumber(server.getTcp().getTcph().getSequenceNumber() + 1);
			networklayer.printDatagram();
			tcp.printHandShake();

		} else {
			System.out.println("�ش� ������ �����ϴ�.");
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
