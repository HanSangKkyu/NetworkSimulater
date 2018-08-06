package Transport;

public class TCPSegment extends Segment {
	TCPSegmentHeader tcph = null;
	SegmentMessage tcpm = null;

	public TCPSegment(TCPSegmentHeader tcph, SegmentMessage tcpm) {
		this.tcph = tcph;
		this.tcpm = tcpm;
	}

	public TCPSegment(int sport, int dport) {
		this.tcph = new TCPSegmentHeader(sport, dport);
		this.tcpm = new SegmentMessage();
	}

	public TCPSegmentHeader getHeader() {
		return tcph;
	}

	public SegmentMessage getMessage() {
		return tcpm;
	}
}
