package Network;

public class DatagramHeader {
	int vers, hlen, stype, totallen, identi, flags, offset, ttlive, protocol, headerchecksum;
	String sIP,dIP;
	public int getVers() {
		return vers;
	}
	public void setVers(int vers) {
		this.vers = vers;
	}
	public int getHlen() {
		return hlen;
	}
	public void setHlen(int hlen) {
		this.hlen = hlen;
	}
	public int getStype() {
		return stype;
	}
	public void setStype(int stype) {
		this.stype = stype;
	}
	public int getTotallen() {
		return totallen;
	}
	public void setTotallen(int totallen) {
		this.totallen = totallen;
	}
	public int getIdenti() {
		return identi;
	}
	public void setIdenti(int identi) {
		this.identi = identi;
	}
	public int getFlags() {
		return flags;
	}
	public void setFlags(int flags) {
		this.flags = flags;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTtlive() {
		return ttlive;
	}
	public void setTtlive(int ttlive) {
		this.ttlive = ttlive;
	}
	public int getProtocol() {
		return protocol;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	public int getHeaderchecksum() {
		return headerchecksum;
	}
	public void setHeaderchecksum(int headerchecksum) {
		this.headerchecksum = headerchecksum;
	}
	public String getsIP() {
		return sIP;
	}
	public void setsIP(String sIP) {
		this.sIP = sIP;
	}
	public String getdIP() {
		return dIP;
	}
	public void setdIP(String dIP) {
		this.dIP = dIP;
	}
	
	
}
