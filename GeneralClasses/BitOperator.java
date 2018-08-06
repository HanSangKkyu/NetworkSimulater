package GeneralClasses;

public class BitOperator {

	public String firstD;
	public String secondD;

	public String get16Bit(int sourcePort) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		int sq = 16; // 자릿수
		StringBuilder binary = new StringBuilder();

		int shift = sq - 1;

		for (; shift >= 0; shift--) {
			int bit = (sourcePort >> shift) & 1;

			if (bit == 1) {
				binary.append("1");
			} else {
				binary.append("0");
			}
		}

		return binary.toString();
	}

	public String get32Bit(int sequenceNumber) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		int sq = 32; // 자릿수
		StringBuilder binary = new StringBuilder();

		int shift = sq - 1;

		for (; shift >= 0; shift--) {
			int bit = (sequenceNumber >> shift) & 1;

			if (bit == 1) {
				binary.append("1");
			} else {
				binary.append("0");
			}
		}

		return binary.toString();
	}

	public String get4Bit(short reserved) {// 쇼트형 데이터를 4비트 이진수로 변환한다.
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		int sq = 4; // 자릿수
		StringBuilder binary = new StringBuilder();

		int shift = sq - 1;

		for (; shift >= 0; shift--) {
			int bit = (reserved >> shift) & 1;

			if (bit == 1) {
				binary.append("1");
			} else {
				binary.append("0");
			}
		}

		return binary.toString();
	}

	public String get4Bit(int headerLength) { // 정수형 데이터를 4비트 이진수로 변환한다.
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		int sq = 4; // 자릿수
		StringBuilder binary = new StringBuilder();

		int shift = sq - 1;

		for (; shift >= 0; shift--) {
			int bit = (headerLength >> shift) & 1;

			if (bit == 1) {
				binary.append("1");
			} else {
				binary.append("0");
			}
		}

		return binary.toString();
	}

	public String get8Bit(int flag) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		int sq = 8; // 자릿수
		StringBuilder binary = new StringBuilder();

		int shift = sq - 1;

		for (; shift >= 0; shift--) {
			int bit = (flag >> shift) & 1;

			if (bit == 1) {
				binary.append("1");
			} else {
				binary.append("0");
			}
		}

		return binary.toString();
	}

	public void split32to16(String string) {
		// TODO Auto-generated method stub
		firstD = string.substring(0, 16);
		secondD = string.substring(16, 32);
	}

}
