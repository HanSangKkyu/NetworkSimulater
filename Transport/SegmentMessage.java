package Transport;

public class SegmentMessage {
	private StringBuffer data = null;

	public SegmentMessage() {
		data = new StringBuffer();
	}

	public void setMessage(String message) {
		data.append(message);
	}

	public int size() {
		return data.length();
	}

	public String getMessage() {
		return data.toString();
	}

}
