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

import java.net.SocketAddress;
import utilies.AttackPattern;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This abstract class represents a model of Protocol.
 * It implements some simples modifiers and accessor methods,
 * but not other specifics methods as write method and others.
 * 
 * It implements the Runnable interface because this will be executed
 * by a JVM thread as a task.
 */
public abstract class Protocol implements Runnable {

    /**
     * The attack pattern object.
     */
    protected AttackPattern attackPattern;

    /**
     * A attack target socket IP address.
     */
    private SocketAddress address;

    /**
     * A master server IP address.
     */
    private String masterAddress;

    /**
     * A master server connection port value.
     */
    private int masterPort;

    /**
     * The protocol object constructor. It receives a AttackPattern object, the master
     * server IP address and master server connection port value and sets these parameters
     * in this attributes.
     * 
     * @param attackPattern The attack pattern object.
     * @param masterAddress The text master server IP address.
     * @param masterPort The master server connection port value.
     */
    public Protocol(AttackPattern attackPattern, String masterAddress, int masterPort) {
        this.attackPattern = attackPattern;
        setMasterAddress(masterAddress);
        setMasterPort(masterPort);
    }

    /**
     * Write method specific of to each protocol.
     * It will be implemented into specific classes.
     * 
     * @param message A text message that the user wants to send.
     */
    public abstract void writeMessage(String message);

    /**
     * Create connection abstract method is a specific method
     * of to each protocol because each protocol have one method
     * to create new socket.
     */
    public abstract void createConnection();

    /**
     * This abstract method turn on socket connection.
     */
    public abstract void connect();

    /**
     * This method closes the socket connection.
     */
    public abstract void closeConnection();

    /**
     * The attack pattern attribute state accessor method.
     * 
     * @return AttackPattern The attack pattern object state.
     */
    public AttackPattern getAttackPattern() {
        return this.attackPattern;
    }

    /**
     * The socket address attribute state modifier method.
     * 
     * @param address The socket address object.
     */
    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    /**
     * The socket address attribute state accessor method.
     * 
     * @return SocketAddress The socket address object.
     */
    public SocketAddress getAddress() {
        return this.address;
    }

    /**
     * The master server address attribute state
     * accessor method.
     * 
     * @return String The server master IP address.
     */
    public String getMasterAddress() {
        return this.masterAddress;
    }

    /**
     * The master server port attribute state
     * accessor method.
     * 
     * @return int The server master port value.
     */
    public int getMasterPort() {
        return this.masterPort;
    }

    /**
     * The master server address attribute state
     * modifier method.
     * 
     * @param address The server master IP address.
     */
    public void setMasterAddress(String address) {
        this.masterAddress = address;
    }

    /**
     * The master server port attribute state
     * modifier method.
     * 
     * @param masterPort The server master port value.
     */
    public void setMasterPort(int masterPort) {
        this.masterPort = masterPort;
    }
}
