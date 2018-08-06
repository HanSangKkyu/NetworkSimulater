package GeneralClasses;

import Transport.TCPSegment;

public class PrintTCPsegment {

	public void printSegmentDecimal(TCPSegment tcpsegment) { // tcpsegment 객체 전체의 값을 받아서 출력해준다.
		BitOperator bo = new BitOperator();
		System.out.println("---------Output TCP segment Decimal Data---------");
		System.out.println(">>SourcePort : " + tcpsegment.getHeader().getSourcePort());
		System.out.println(">>DestinationPort : " + tcpsegment.getHeader().getDestPort());
		System.out.println(">>SequenceNumber : " + tcpsegment.getHeader().getSequenceNumber());
		System.out.println(">>AcknowledgementNumber : " + tcpsegment.getHeader().getAcknowledgementNumber());
		System.out.println(">>HeaderLength : " + tcpsegment.getHeader().getHeaderLength());
		System.out.println(">>Reserved : " + tcpsegment.getHeader().getReserved());
		System.out.println(">>TCPFlags : " + tcpsegment.getHeader().getFlag());
		System.out.println(">>ReceiveWindow : " + tcpsegment.getHeader().getWindow());
		System.out.println(">>Checksum : " + tcpsegment.getHeader().getChecksum());
		System.out.println(">>UrgentData : " + tcpsegment.getHeader().getUrgentData());
		System.out.println(">>Payload : " + tcpsegment.getMessage().getMessage());
	}

	public void printSegmentBinary(TCPSegment tcpsegment) { // tcpsegment 객체 전체의 값을 받아서 이진수로 출력해준다.
		BitOperator bo = new BitOperator();
		System.out.println("---------Output TCP segment Binary Data--------- ");
		System.out.println(">>BinarySourcePort : " + bo.get16Bit(tcpsegment.getHeader().getSourcePort()));
		System.out.println(">>BinaryDestinationPort : " + bo.get16Bit(tcpsegment.getHeader().getDestPort()));
		System.out.println(">>BinarySequenceNumber : " + bo.get32Bit(tcpsegment.getHeader().getSequenceNumber()));
		System.out.println(
				">>BinaryAcknowledgementNumber : " + bo.get32Bit(tcpsegment.getHeader().getAcknowledgementNumber()));
		System.out.println(">>BinaryHeaderLength : " + bo.get4Bit(tcpsegment.getHeader().getHeaderLength()));
		System.out.println(">>BinaryReserverd : " + bo.get4Bit(tcpsegment.getHeader().getReserved()));
		System.out.println(">>BinaryTCPFlags : " + bo.get8Bit((short) tcpsegment.getHeader().getFlag()));
		System.out.println(">>BinaryReceiveWindow : " + bo.get16Bit(tcpsegment.getHeader().getWindow()));
		System.out.println(">>BinaryChecksum : " + bo.get16Bit(tcpsegment.getHeader().getChecksum()));
		System.out.println(">>BinaryUrgentData : " + bo.get16Bit(tcpsegment.getHeader().getUrgentData()));
		System.out.println(">>BinaryPayload : " + bo.get32Bit(Integer.parseInt(tcpsegment.getMessage().getMessage())));

	}
}
