/**
 * FIRESEC TURBO FIRE
 * A stress network analysis tool.
 * ------------------------------------
 * Written by Lucas Fonseca dos Santos.
 * Copyleft 2018 - Rights not reserved.
 * ------------------------------------
 * 
 * Pratical project for discipline Distributed
 * Systems of Federal Univeristy of Lavras - MG,
 * Brazil.
 * 
 * CONTACT:
 * lucas@lcfcompany.com.br
 * github.com/LucasFonsecaDosSantos
 */
package protocols.interfaces;

import utilies.AttackPattern;
import view.GUI;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import app.TurboFireSlave;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This abstract class describes the Transmission Control
 * Protocol interface, specific of Protocol (so it extends protocol
 * abstract class). It contains the algorithm for a TCP transmission
 * in network by JVM and other settings about DDOS attack operation.
 */
public abstract class TransmissionControlProtocolInterface extends Protocol {

    /**
     * Socket connection to server.
     */
    private Socket socket;

    /**
     * Transmission control protocol interface constuctor. It
     * receives a attack pattern object, the master server address
     * and the master server port and send these parameters to super class,
     * Protocol abstract class in this case.
     * 
     * @param attackPatter The attack pattern object.
     * @param masterAddress The master server IP address.
     * @param masterPort THe master server port value.
     */
    public TransmissionControlProtocolInterface(AttackPattern attackPattern, String masterAddress, int masterPort) {
        super(attackPattern, masterAddress, masterPort);
    }

    /**
     * How the super class Protocol implements the Runnable interface,
     * this class implements the run method for thread task.
     * In this method, all socket connection will be initialized and
     * the message will be transmited.
     */
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

    /**
     * This method is responsible for creating a new
     * socket connection and setting other settings as
     * socket time out and socket inet address.
     */
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

    /**
     * This method does the connection with target
     * by socket.
     */
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

    /**
     * This method is responsible for closing the
     * socket connection with target.
     */
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

    /**
     * The socket state attribute modifier method.
     * 
     * @param socket A socket object.
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * The socket state attribute accessor method.
     * 
     * @return Socket A socket object.
     */
    public Socket getSocket() {
        return this.socket;
    }

}