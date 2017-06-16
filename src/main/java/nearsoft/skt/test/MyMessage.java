package nearsoft.skt.test;

/**
 * Created by rfonseca on 6/15/17.
 */
public class MyMessage {

    private long currentTime;
    private String header;
    private PayloadMessage message;

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public PayloadMessage getMessage() {
        return message;
    }

    public void setMessage(PayloadMessage message) {
        this.message = message;
    }
}
