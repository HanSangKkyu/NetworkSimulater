package Transport;

public class SegmentHeader {
    private int sourcePort,destPort,headerLength;

    SegmentHeader(int sport,int dport){
        this.sourcePort = sport;
        this.destPort =dport;
    }

    void setSourcePort(short sourcePort){
        this.sourcePort=sourcePort;
    }

    void setDestPort(short destPort){
        this.destPort =destPort;
    }

    public void setHeaderLength(short headerLength){
        this.headerLength = headerLength;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public int getDestPort() {
        return destPort;
    }

    public int getHeaderLength() {
        return headerLength;
    }
}
