package protocols.interfaces;

import java.net.SocketAddress;
import utilies.AttackPattern;

public abstract class Protocol implements Runnable {

    protected AttackPattern attackPattern;
    private SocketAddress address;
    private String masterAddress;
    private int masterPort;

    public Protocol(AttackPattern attackPattern, String masterAddress, int masterPort) {
        this.attackPattern = attackPattern;
        setMasterAddress(masterAddress);
        setMasterPort(masterPort);
    }

    public abstract void writeMessage(String message);

    public abstract void createConnection();

    public abstract void connect();

    public abstract void closeConnection();

    public AttackPattern getAttackPattern() {
        return this.attackPattern;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    public SocketAddress getAddress() {
        return this.address;
    }

    public String getMasterAddress() {
        return this.masterAddress;
    }

    public int getMasterPort() {
        return this.masterPort;
    }

    public void setMasterAddress(String address) {
        this.masterAddress = address;
    }

    public void setMasterPort(int masterPort) {
        this.masterPort = masterPort;
    }
}
