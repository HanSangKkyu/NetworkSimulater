package GeneralClasses;

import Transport.TCPSegment;
import Transport.TCPSegmentHeader;

import java.util.InputMismatchException;
import java.util.Scanner;

import Transport.SegmentMessage;

public class DataInput {

	Scanner s;
	TCPSegment tcpsegment = null;
	TCPSegmentHeader tcpsegmentheader = null;
	SegmentMessage segmentmessage = null;

	public DataInput() {
		s = new Scanner(System.in);
	}

	public TCPSegment dataInputStream() {
		System.out.println("---------Input TCP segment Data---------");
		System.out.printf(">>Source Port : ");
		int sport = s.nextInt();

		System.out.printf(">>Destination Port : ");
		int dport = s.nextInt();
		s.nextLine(); // 버퍼 비우기

		tcpsegmentheader = new TCPSegmentHeader(sport, dport);

		System.out.printf(">>Sequence Number : ");
		while (true) {
			try {
				tcpsegmentheader.setSequenceNumber(s.nextInt());
				break;
			} catch (InputMismatchException e) {
				System.out.println("******Type mismatch********* ");
				System.out.printf(">>Sequence Number : ");
				s.nextLine(); // 버퍼 비우기

			}
		}
		System.out.printf(">>Acknowledgment Number : ");
		tcpsegmentheader.setAcknowledgementNumber(s.nextInt());

		System.out.printf(">>Header Length : ");
		tcpsegmentheader.setHeaderLength(s.nextShort());

		System.out.printf(">>Reserved : ");
		tcpsegmentheader.setReserved(s.nextShort());

		System.out.println(">>TCPFlags -");

		System.out.printf(">> CWR : ");
		tcpsegmentheader.setCWR(s.nextShort()); // 기본적으로 0이 들어간다.

		System.out.printf(">> ECE : ");
		tcpsegmentheader.setECE(s.nextShort()); // 기본적으로 0이 들어간다.

		System.out.printf(">> URG : ");
		tcpsegmentheader.setURG(s.nextShort());

		System.out.printf(">> ACK : ");
		tcpsegmentheader.setACK(s.nextShort());

		System.out.printf(">> PSH : ");
		tcpsegmentheader.setPSH(s.nextShort());

		System.out.printf(">> RST : ");
		tcpsegmentheader.setRST(s.nextShort());

		System.out.printf(">> SYN : ");
		tcpsegmentheader.setSYN(s.nextShort());

		System.out.printf(">> FIN : ");
		while (true) {
			tcpsegmentheader.setFIN(s.nextShort());
			if (tcpsegmentheader.getFIN() < 2) {
				break;
			} else {
				System.out.println("*********Out of Range******** ");
				System.out.printf(">> FIN : ");
				s.nextLine();
			}
		}

		String TCPFlags = String.valueOf(tcpsegmentheader.getCWR()) + String.valueOf(tcpsegmentheader.getECE()
				+ String.valueOf(tcpsegmentheader.getURG()) + String.valueOf(tcpsegmentheader.getACK())
				+ String.valueOf(tcpsegmentheader.getPSH()) + String.valueOf(tcpsegmentheader.getRST())
				+ String.valueOf(tcpsegmentheader.getSYN()) + String.valueOf(tcpsegmentheader.getFIN()));

		int foo = Integer.parseInt(TCPFlags, 2);
		tcpsegmentheader.setFlag(foo);

		System.out.printf(">>Window : ");
		tcpsegmentheader.setWindow(s.nextShort());

		System.out.printf(">>Urgent Data Pointer : ");
		tcpsegmentheader.setUrgentData(s.nextShort());

		segmentmessage = new SegmentMessage();

		System.out.printf(">>Payload : ");
		String msg = s.next();
		segmentmessage.setMessage(msg);

		// TCPsegmentheader와 segmentmessage에 값을 모두 입력했기 때문에 TCPSegment라는 객체를 만들어서 리턴해준다.
		return tcpsegment = new TCPSegment(tcpsegmentheader, segmentmessage);
		
	}
}
