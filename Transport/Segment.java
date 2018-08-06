package Transport;

public abstract class Segment {
    public abstract SegmentHeader getHeader();
    public abstract SegmentMessage getMessage();
}
