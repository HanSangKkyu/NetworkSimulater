package Transport;


public class TCPSegmentHeader extends SegmentHeader {
    public TCPSegmentHeader(int sport, int dport) {
        super(sport, dport);
    }


    private short Window, urgentData, reserved, tcpF1ags;
    private short CWR, ECE, URG, ACK, PSH, RST, SYN, FIN;
    private int flag, checksum, sequenceNumber, acknowledgementNumber;

    public void setWindow(short window) {
        Window = window;
    }

    public void setUrgentData(short urgentData) {
        this.urgentData = urgentData;
    }

    public void setReserved(short reserved) {
        this.reserved = reserved;
    }

    public void setTcpF1ags(short tcpF1ags) {
        this.tcpF1ags = tcpF1ags;
    }

    public void setCWR(short CWR) {
        this.CWR = CWR;
    }

    public void setECE(short ECE) {
        this.ECE = ECE;
    }

    public void setURG(short URG) {
        this.URG = URG;
    }

    public void setACK(short ACK) {
        this.ACK = ACK;
    }

    public void setPSH(short PSH) {
        this.PSH = PSH;
    }

    public void setRST(short RST) {
        this.RST = RST;
    }

    public void setSYN(short SYN) {
        this.SYN = SYN;
    }

    public void setFIN(short FIN) {
        this.FIN = FIN;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setAcknowledgementNumber(int acknowledgementNumber) {
        this.acknowledgementNumber = acknowledgementNumber;
    }

    public short getWindow() {
        return Window;
    }

    public short getUrgentData() {
        return urgentData;
    }

    public short getReserved() {
        return reserved;
    }

    public short getTcpF1ags() {
        return tcpF1ags;
    }

    public short getCWR() {
        return CWR;
    }

    public short getECE() {
        return ECE;
    }

    public short getURG() {
        return URG;
    }

    public short getACK() {
        return ACK;
    }

    public short getPSH() {
        return PSH;
    }

    public short getRST() {
        return RST;
    }

    public short getSYN() {
        return SYN;
    }

    public short getFIN() {
        return FIN;
    }

    public int getFlag() {
        return flag;
    }

    public int getChecksum() {
        return checksum;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public int getAcknowledgementNumber() {
        return acknowledgementNumber;
    }
}