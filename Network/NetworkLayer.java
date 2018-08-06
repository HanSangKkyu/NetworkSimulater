package Network;

import Transport.Segment;

public class NetworkLayer {
	Datagram dgram = null;

	public NetworkLayer() {
		this.dgram = new Datagram();
	}

	public Datagram getDgram() {
		return dgram;
	}

	public void setDgram(Datagram dgram) {
		this.dgram = dgram;
	}

	public void setDatagramHeader(String sIP, String dIP) {
		DatagramHeader dheader = new DatagramHeader();
		dheader.setdIP(dIP);
		dheader.setsIP(sIP);

		// dheader.setVersion(4);
		// dheader.setHeaderLength(5);
		// dheader.setServiceType(0);
		// dheader.setTotalLength(20);
		// dheader.setIdentification(1);
		// dheader.setFlags(2);
		// dheader.setFramentOffset(0);
		// dheader.setTimeToLive(0);
		// dheader.setProtocol(0);
		// dheader.setHeaderChecksum(0);

		dheader.setVers(4);
		dheader.setHlen(5);
		dheader.setStype(0);
		dheader.setTotallen(20);
		dheader.setIdenti(1);
		dheader.setFlags(2);
		dheader.setOffset(0);
		dheader.setTtlive(0);
		dheader.setProtocol(0);
		dheader.setHeaderchecksum(0);

		dgram.setDatagramHeader(dheader);
	}

	public void setDatagramData(Segment segment) {
		DatagramData data = new DatagramData(segment);
		dgram.setDatagramData(data);
	}

	public void printDatagram() {
		System.out.println("---------------------Datagram---------------------");
		System.out.println("SourceIP: " + dgram.getDatagramHeader().getsIP());
		System.out.println("Desrination: " + dgram.getDatagramHeader().getdIP());
		System.out.println("Version: " + dgram.getDatagramHeader().getVers());
		System.out.println("HeaderLength: " + dgram.getDatagramHeader().getHlen());
		System.out.println("ServiceType: " + dgram.getDatagramHeader().getStype());
		System.out.println("TotalLength: " + dgram.getDatagramHeader().getTotallen());
		System.out.println("Identification: " + dgram.getDatagramHeader().getIdenti());
		System.out.println("Flags: " + dgram.getDatagramHeader().getFlags());
		System.out.println("FragmentOffset: " + dgram.getDatagramHeader().getOffset());
		System.out.println("TimeToLive: " + dgram.getDatagramHeader().getTtlive());
		System.out.println("Protocol: " + dgram.getDatagramHeader().getProtocol());
		System.out.println("HeaderChecksum: " + dgram.getDatagramHeader().getHeaderchecksum());
	}
}
