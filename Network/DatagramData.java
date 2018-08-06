package Network;

import Transport.Segment;

public class DatagramData {
	Segment segment = null;

	public DatagramData(Segment segment) {
		this.segment = segment;
	}

	void setDatagramData(Segment segment) {
		this.segment = segment;
	}

	Segment getDatagramData() {
		return this.segment;
	}
}
