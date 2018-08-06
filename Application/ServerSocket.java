package Application;

import Network.NetworkLayer;
import Transport.TCP;
import Transport.TCPSegment;
import Transport.TransportLayer;

public class ServerSocket implements Runnable {
	int sport = 0, dport = 0;
	String sIP = null, dIP = null;
	boolean checker = false;
	TCP tcp;

	public TCP getTcp() {
		return tcp;
	}

	public void setTcp(TCP tcp) {
		this.tcp = tcp;
	}

	TransportLayer transportlayer = null;
	NetworkLayer networklayer = null;

	public ServerSocket(int port) {
		this("100.0.0.1", port);
	}

	public ServerSocket(String IP, int port) {
		this.sIP = IP;
		this.sport = port;
	}

	public void bind(ClientSocket clientSocket) {
		this.transportlayer = new TCP(new TCPSegment(sport, dport));
		this.networklayer = new NetworkLayer();
		this.networklayer.setDatagramHeader(sIP, dIP);

		tcp = new TCP(new TCPSegment(sport, dport));
		tcp.handshake();

		tcp.getTcph().setSYN((short) 1);
		tcp.getTcph().setACK((short) 1);
		tcp.getTcph().setSequenceNumber(clientSocket.getTcp().getTcph().getAcknowledgementNumber());
		tcp.getTcph().setAcknowledgementNumber(clientSocket.getTcp().getTcph().getSequenceNumber() + 1);

		networklayer.printDatagram();
		tcp.printHandShake();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (checker)
				break;
		}
	}

	boolean checkIPandPort(String IP, int port) {
		if ((this.sIP.equals(IP)) && (this.sport == port))
			return checker = true;
		else
			return checker = false;
	}

	boolean checkIPandPort() {
		return checker;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public int getDport() {
		return dport;
	}

	public void setDport(int dport) {
		this.dport = dport;
	}

	public String getsIP() {
		return sIP;
	}

	public void setsIP(String sIP) {
		this.sIP = sIP;
	}

	public String getdIP() {
		return dIP;
	}

	public void setdIP(String dIP) {
		this.dIP = dIP;
	}

	public boolean isChecker() {
		return checker;
	}

	public void setChecker(boolean checker) {
		this.checker = checker;
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

}
