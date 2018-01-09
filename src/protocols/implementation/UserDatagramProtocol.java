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
package protocols.implementation;

import protocols.interfaces.UserDatagramProtocolInterface;
import utilies.SlaveResponse;
import utilies.AttackPattern;
import view.GUI;
import java.io.IOException;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class is a specialization of UserDatagramProtocolInterface and
 * implements here a write message method specific of UDP treatment in
 * JVM.
 */
public class UserDatagramProtocol extends UserDatagramProtocolInterface {

    /**
     * The User datagram protocol object constructor.
     * It receives a attackPattern, master server IP address and master server
     * connection port integer value and sends these parameters to User datagram
     * protocol interface super class.
     * 
     * @param attackPattern A attack pattern object with all information about DDOS operation.
     * @param masterAddress A master server IP text address.
     * @param masterPort A master server connection port integer value.
     */
    public UserDatagramProtocol(AttackPattern attackPattern, String masterAddress, int masterPort) {
        super(attackPattern, masterAddress, masterPort);
    }

    /**
     * This is an overwrited method.
     * 
     * @param message A text string message that will be sended by this protocol.
     */
    @Override
    public void writeMessage(String message) {
        byte[] data = message.getBytes();
        getPacket().setData(data);
        getPacket().setLength(data.length);
        SlaveResponse.successfullyAttackResponse(getMasterAddress(), getMasterPort(), getAttackPattern());
        try {
            getSocket().send(getPacket());
        } catch (IOException ex) {
            GUI.showExceptionLog(ex.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}