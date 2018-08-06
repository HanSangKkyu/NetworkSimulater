package Network;

public class RoutingTable {
	String[] rIP;
	int[] cost;

	public RoutingTable(int size) {
		this.rIP = new String[size];
		this.cost = new int[size];
	}

	void add(int idx, String rIP, int cost) {
		this.rIP[idx] = rIP;
		this.cost[idx] = cost;
	}

	public String[] getrIP() {
		return rIP;
	}

	public void setrIP(String[] rIP) {
		this.rIP = rIP;
	}

	public int[] getCost() {
		return cost;
	}

	public void setCost(int[] cost) {
		this.cost = cost;
	}
	
	
}
