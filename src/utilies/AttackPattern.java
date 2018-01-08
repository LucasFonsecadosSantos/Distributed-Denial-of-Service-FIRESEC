package utilies;

import java.io.Serializable;

public class AttackPattern implements Serializable{

    private static final long serialVersionUID = 1L;
    private int port;
    private String ipAddress;
    private int threadAmount;
    private int attackTimeOut;
    private int connectionTimeOut;
    private String protocol;
    private String message;

    
    public AttackPattern(String protocol ,String ipAddress, int port, int threadAmount, int connectionTimeOut, String message) {
        setPort(port);
        setIP(ipAddress);
        setThreadAmount(threadAmount);
        setProtocol(protocol);
        setConnectionTimeOut(connectionTimeOut);
        setMessage(message);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public int getConnectionTimeOut() {
        return this.connectionTimeOut;
    }
}