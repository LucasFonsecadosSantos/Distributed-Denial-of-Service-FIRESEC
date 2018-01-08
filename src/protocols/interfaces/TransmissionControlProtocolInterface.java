package protocols.interfaces;

import utilies.AttackPattern;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import app.TurboFireSlave;

public abstract class TransmissionControlProtocolInterface extends Protocol {

    private Socket socket;

    public TransmissionControlProtocolInterface(AttackPattern attackPattern) {
        super(attackPattern);
    }

    @Override
    public void run() {
        
        
        //SE DER RUIM AQUI, TEM QUE COLOCAR CONDICAO DE PARADA STOP THREAD
        //while(!Thread.currentThread().isInterrupted() && (this.socket.isConnected() && !this.socket.isClosed())) {
        while(true) {
            createConnection();
            connect();
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
        setAddress(new InetSocketAddress(getAttackPattern().getIP(), getAttackPattern().getPort()));
        this.socket = new Socket();
        
        try {
            this.socket.setKeepAlive(true);
            this.socket.setSoTimeout(getAttackPattern().getConnectionTimeOut());
        } catch (SocketException se) {
            se.printStackTrace();
        }
    }

    public void connect() {
        try {
            if(this.socket != null) {
                this.socket.connect(getAddress());
            }
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (SocketException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if(this.socket != null && !this.socket.isClosed() && this.socket.isBound()) {
                this.socket.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

}