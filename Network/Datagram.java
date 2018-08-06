package Network;

public class Datagram {
	DatagramHeader dheader = null;
	DatagramData data = null;

	public Datagram(DatagramHeader dheader, DatagramData data) {
		this.dheader = dheader;
		this.data = data;
	}

	public Datagram() {
		this(null, null);
	}

	void setDatagramHeader(DatagramHeader dh) {
		this.dheader = dh;
	}

	void setDatagramData(DatagramData dd) {
		this.data = data;
	}

	public DatagramHeader getDatagramHeader() {
		return this.dheader;
	}

	public DatagramData getDatagramData() {
		return this.data;
	}

	
}
