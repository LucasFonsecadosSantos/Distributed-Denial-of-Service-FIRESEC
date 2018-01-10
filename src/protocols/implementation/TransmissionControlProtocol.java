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

import utilies.AttackPattern;
import utilies.SlaveResponse;
import view.GUI;
import protocols.interfaces.TransmissionControlProtocolInterface;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * The implementation class of Transmission Control Protocol (TCP)
 * extended of TransmissionControlProtocolInterface .
 * 
 * This class overwrites the writeMessage method.
 */
public class TransmissionControlProtocol extends TransmissionControlProtocolInterface {

    /**
     * The Transmission control protocol object constructor.
     * It receives a attack pattern, master server IP address and
     * master server connection port value and sends these parameters
     * to TransmissionControlProtocolInterface constructor super class.
     * 
     * @param attackPattern The attackPattern object with all informaton about the DDOS operation.
     * @param masterAddress The master server IP text address.
     * @param masterPort The master server connection port integer value. 
     */
    public TransmissionControlProtocol(AttackPattern attackPattern, String masterAddress, int masterPort) {
        super(attackPattern, masterAddress, masterPort);
    }

    /**
     * Write method overwrited by the Transmission control protocol interface.
     * It writes a message into protocol and sends it back a response with data
     * about this operation.
     * 
     * @param message A text string message will be sended by the protocol.
     */
    @Override
    public void writeMessage(String message) {
        SlaveResponse.successfullyAttackResponse(getMasterAddress(), getMasterPort(), getAttackPattern());
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(getSocket().getOutputStream()))) {
            bw.write(getAttackPattern().getMessage());
            bw.flush();
        } catch (IOException ex) {
            GUI.showExceptionLog(ex.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}