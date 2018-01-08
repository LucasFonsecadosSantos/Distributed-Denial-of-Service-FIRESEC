package protocols.interfaces;

import java.net.SocketAddress;
import utilies.AttackPattern;

public abstract class Protocol implements Runnable {

    protected AttackPattern attackPattern;
    private SocketAddress address;

    public Protocol(AttackPattern attackPattern) {
        this.attackPattern = attackPattern;
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
}
