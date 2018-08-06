package Network;

public class Router {
	String rIP = null;
	RoutingTable rtable;

	public Router(String rIP, RoutingTable rtable) {
		this.rIP = rIP;
		this.rtable = rtable;
	}

	public String getrIP() {
		return rIP;
	}

	public void setrIP(String rIP) {
		this.rIP = rIP;
	}

	public RoutingTable getRtable() {
		return rtable;
	}

	public void setRtable(RoutingTable rtable) {
		this.rtable = rtable;
	}

}
