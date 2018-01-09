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
package utilies;

import java.io.Serializable;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class descibes a pattern of distributed denial of services (DDOS)
 * attacks provided by this software. It contains all information about
 * target as IP address end the technical informations about operation as
 * thread amount, connection time out, sockets time out, protocol that will
 * be used, attack time out limit, a string text message that will be send
 * by zombie host and aditional informations for other controllers.
 */
public class AttackPattern implements Serializable{

    /**
     * The serial version of this object into JVM.
     * Used by JVM for stream object serialization
     * operation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * An integer port value of target.
     */
    private int port;

    /**
     * The target IP address.
     */
    private String ipAddress;

    /**
     * A thread amount value. It works as a number of attack guns.
     */
    private int threadAmount;

    /**
     * The attack time out value. If the target does not respond,
     * the attack time out is the limit that will be respect by
     * the software.
     */
    private int attackTimeOut;

    /**
     * A TCP time out, connection time out of zombie socket.
     */
    private int connectionTimeOut;

    /**
     * The range of attack shots.
     */
    private int attackRange;

    /**
     * A protocol that will be used by DDOS attack.
     */
    private String protocol;

    /**
     * A text message that will be send by zombie to target.
     */
    private String message;

    /**
     * The additional information field used for anythings.
     * It is mostly used in zombie response to master server
     * with a zoombie IP address.
     */
    private String additionalInformation;

    /**
     * The attack pattern object constructor. It receives a protocol, target ip address,
     * target connection port, thread amount used by DDOS attack, connection time out of
     * socket, attack shot range, a text message end any additional information and
     * sets these parameters in object attributes.
     * 
     * @param protocol THe protocol that will be used by DDOS attack.
     * @param ipAddress The string target IP address.
     * @param port A integer target port value.
     * @param threadAmount A integer thread amount of attack.
     * @param connectionTimeOut A integer connection time out of sockets created.
     * @param attackRange The attack shots time range.
     * @param message A text message that will be send by zombie host.
     * @param additionalInformation Any additional text information field.
     */
    public AttackPattern(String protocol ,String ipAddress, int port, int threadAmount, int connectionTimeOut, int attackRange, String message, String additionalInformation) {
        setPort(port);
        setIP(ipAddress);
        setThreadAmount(threadAmount);
        setProtocol(protocol);
        setConnectionTimeOut(connectionTimeOut);
        setMessage(message);
        setAttackRange(attackRange);
    }

    /**
     * The additional information state attribute modifier method.
     * 
     * @param information A text additional information.
     */
    public void setAdditionalInformation(String information) {
        this.additionalInformation = information;
    }

    /**
     * The additional information state attribute accessor method.
     * 
     * @return String A text additional information.
     */
    public String getAdditionalInformation() {
        return this.additionalInformation;
    }
    
    /**
     * The attack range information state attribute accessor method.
     * 
     * @return int A integer attack shot time range value.
     */
    public int getAttackRange() {
        return this.attackRange;
    }

    /**
     * The attack range information state attribute modifier method.
     * 
     * @param attackRange A integer attack shot time range value.
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * The message state attribute accessor method.
     * 
     * @return String The message will be send into packets.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * The message state attribute modifier method.
     * 
     * @param message The message will be send into packets.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * The protocol state attribute accessor method.
     * 
     * @return String The protocol will be used by attack.
     */
    public String getProtocol() {
        return this.protocol;
    }

    /**
     * The protocol state attribute modifier method.
     * 
     * @param protocol The protocol will be used by attack.
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * The IP state attribute modifier method.
     * 
     * @param ipAddress IP address of attack target.
     */
    public void setIP(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * The IP state attribute accessor method.
     * 
     * @return String IP address of attack target.
     */
    public String getIP() {
        return this.ipAddress;
    }

    /**
     * The port state attribute modifier method.
     * 
     * @param port Connection port of attack target.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * The port state attribute accessor method.
     * 
     * @return int Connection port of attack target.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * The threadAmount state attribute modifier method.
     * 
     * @param threadAmount Thread amount that will be used by DDOS attack.
     */
    public void setThreadAmount(int threadAmount) {
        this.threadAmount = threadAmount;
    }

    /**
     * The threadAmount state attribute accessor method.
     * 
     * @return int Thread amount that will be used by DDOS attack.
     */
    public int getThreadAmount() {
        return this.threadAmount;
    }

    /**
     * The connectionTimeOut state attribute modifier method.
     * 
     * @param connectionTimeOut Connection time out limit.
     */
    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    /**
     * The connectionTimeOut state attribute accessor method.
     * 
     * @return int Connection time out limit.
     */
    public int getConnectionTimeOut() {
        return this.connectionTimeOut;
    }
}