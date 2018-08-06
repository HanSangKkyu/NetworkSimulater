package Transport;

import GeneralClasses.BitOperator;
import GeneralClasses.DataInput;
import GeneralClasses.PrintTCPsegment;

public class TCP extends TransportLayer {
	TCPSegmentHeader tcph = null;
	SegmentMessage tcpm = null;
	BitOperator bo = null;

	public TCPSegmentHeader getTcph() {
		return tcph;
	}

	public void setTcph(TCPSegmentHeader tcph) {
		this.tcph = tcph;
	}

	public SegmentMessage getTcpm() {
		return tcpm;
	}

	public void setTcpm(SegmentMessage tcpm) {
		this.tcpm = tcpm;
	}

	public BitOperator getBo() {
		return bo;
	}

	public void setBo(BitOperator bo) {
		this.bo = bo;
	}

	public TCP(Segment seg) {
		generateSegment(seg);
		this.bo = new BitOperator();
	}

	void generateSegment(Segment seg) {
		this.tcph = (TCPSegmentHeader) seg.getHeader();
		this.tcpm = seg.getMessage();
	}

	void generateSegmentByInput(TCPSegmentHeader tcph, DataInput di, PrintTCPsegment pt) {

	}

	void setSegmentHeader() {
		tcph.setSequenceNumber(200);
		tcph.setAcknowledgementNumber(300);
		tcph.setHeaderLength((short) 5);
		tcph.setReserved((short) 0);

		tcph.setCWR((short) 0);
		tcph.setECE((short) 0);
		tcph.setURG((short) 0);
		tcph.setACK((short) 0);
		tcph.setPSH((short) 0);
		tcph.setRST((short) 0);
		tcph.setSYN((short) 1);
		tcph.setFIN((short) 0);

		String TCPFlags = (String.valueOf(tcph.getCWR()) + String.valueOf(tcph.getECE()) + String.valueOf(tcph.getURG())
				+ String.valueOf(tcph.getACK()) + String.valueOf(tcph.getPSH()) + String.valueOf(tcph.getRST())
				+ String.valueOf(tcph.getSYN()) + String.valueOf(tcph.getFIN()));

		int foo = Integer.parseInt(TCPFlags, 2);
		tcph.setFlag(foo);
		tcph.setWindow((short) 0);
		tcph.setChecksum(0);
		tcph.setUrgentData((short) 0);

	}

	public void handshake() {
		// this.tcph = new TCPSegmentHeader(2001, 2000);
		// this.tcpm = new SegmentMessage(); // tcpheader만들어주고
		setSegmentHeader(); // 값 설정해준다
	}

	public void printHandShake() {
		System.out.println("----------TCPHandShake----------");
		System.out.println("SourcePort: " + tcph.getSourcePort());
		System.out.println("DestinationPort: " + tcph.getDestPort());
		System.out.println("SequenceNumber: " + tcph.getSequenceNumber());
		System.out.println("AcknowledgementNumber: " + tcph.getAcknowledgementNumber());
		System.out.println("HeaderLength: " + tcph.getHeaderLength());
		System.out.println("TCPFlag:"); // 플래그들 출력
		System.out.println("\t-CWR: " + tcph.getCWR());
		System.out.println("\t-ECE: " + tcph.getECE());
		System.out.println("\t-URG: " + tcph.getURG());
		System.out.println("\t-ACK: " + tcph.getACK());
		System.out.println("\t-PSH: " + tcph.getPSH());
		System.out.println("\t-RST: " + tcph.getRST());
		System.out.println("\t-SYN: " + tcph.getSYN());
		System.out.println("\t-FIN: " + tcph.getFIN());
		System.out.println("Reserved: " + tcph.getReserved());
		System.out.println("CheckSum: " + tcph.getChecksum());
		System.out.println("Window: " + tcph.getWindow());
		System.out.println("UrgentData: " + tcph.getUrgentData());

	}

	public void printTCPSegment(String sliceMsg) {
		System.out.println("----------TCPSegment----------");
		System.out.println("SourcePort: " + tcph.getSourcePort());
		System.out.println("DestinationPort: " + tcph.getDestPort());
		System.out.println("SequencNumber: " + tcph.getSequenceNumber());
		System.out.println("AcknowledgementNumber: " + tcph.getAcknowledgementNumber());
		System.out.println("HeaderLength: " + tcph.getHeaderLength());
		System.out.println("TCPFlag:");
		System.out.println("\t-CWR: " + tcph.getCWR());
		System.out.println("\t-ECE: " + tcph.getECE());
		System.out.println("\t-URG: " + tcph.getURG());
		System.out.println("\t-ACK: " + tcph.getACK());
		System.out.println("\t-PSH: " + tcph.getPSH());
		System.out.println("\t-RST: " + tcph.getRST());
		System.out.println("\t-SYN: " + tcph.getSYN());
		System.out.println("\t-FIN: " + tcph.getFIN());
		System.out.println("Reserved: " + tcph.getReserved());
		System.out.println("CheckSum: " + tcph.getChecksum());
		System.out.println("Window: " + tcph.getWindow());
		System.out.println("UrgentData: " + tcph.getUrgentData());
		System.out.println("Message: " + sliceMsg);
	}

	TCPSegmentHeader getSegmentHeader() {
		return this.tcph;
	}

	SegmentMessage getSegmentMessage() {
		return this.tcpm;
	}

	String makeChecksumHeader2BitString() {
		StringBuffer header = new StringBuffer();
		header.append(bo.get16Bit(tcph.getSourcePort()));
		header.append(" ");
		header.append(bo.get16Bit(tcph.getDestPort()));
		header.append(" ");
		bo.split32to16(bo.get32Bit(tcph.getSequenceNumber()));
		header.append(bo.firstD);
		header.append(" ");
		header.append(bo.secondD);
		header.append(" ");
		bo.split32to16(bo.get32Bit(tcph.getAcknowledgementNumber()));
		header.append(bo.firstD);
		header.append(" ");
		header.append(bo.secondD);
		header.append(" ");
		header.append(bo.get4Bit(tcph.getHeaderLength()) + bo.get4Bit(tcph.getReserved())
				+ bo.get8Bit((short) tcph.getFlag()));
		header.append(" ");
		header.append(bo.get16Bit(tcph.getWindow()));
		header.append(" ");
		header.append(bo.get16Bit(tcph.getUrgentData()));

		return header.toString();

	}

	void makeChecksumField(String header2Bit) {
		int tmp = 0;
		String[] fields = header2Bit.split(" ");
		for (int i = 0; i < fields.length; i++) {
			tmp += Integer.parseInt(fields[i], 2);
			if (tmp > 65536) {
				tmp = tmp - 65536 + 1;
			}
		}
		tcph.setChecksum(~tmp);
	}

	void checksum() {
		// 실습시간에 만든 코드로 checksum을 계산한다. 그 후 checksum값을 저장한다.
		makeChecksumField(makeChecksumHeader2BitString());
	}

	void print() {
		TCPSegment ts = new TCPSegment(tcph, tcpm); // checksum 까지 저장한 TCPSegment를 만들어준다.
		PrintTCPsegment pt = new PrintTCPsegment(); // 결과를 출력하기 위해 PrintTCPsegment 객체를 생성해준다.
		pt.printSegmentDecimal(ts); // TCPSegment의 헤더값을 10진수로 출력하기 위해 PrintTCPsegement 객체에 함수 인자로 넘겨준다.
		pt.printSegmentBinary(ts); // TCPSegment의 헤더값을 10진수로 출력하기 위해 PrintTCPsegement 객체에 함수 인자로 넘겨준다.
	}
}