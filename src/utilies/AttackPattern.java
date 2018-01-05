package utilies;

public class AttackPattern {

    private int port;
    private String ipAddress;
    private int threadAmount;
    
    public AttackPattern(String ipAddress, int port, int threadAmount) {
        setPort(port);
        setIP(ipAddress);
        setThreadAmount(threadAmount);
    }

    public void setIP(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIP() {
        return this.ipAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void setThreadAmount(int threadAmount) {
        this.threadAmount = threadAmount;
    }

    public int getThreadAmount() {
        return this.threadAmount;
    }
}