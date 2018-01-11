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

import protocols.interfaces.Protocol;
import protocols.implementation.TransmissionControlProtocol;
import protocols.implementation.UserDatagramProtocol;

/**
 * @author Lucas Fonseca dos Santos
 * @version 1.0
 * 
 * This class is a proect desgin pattern factory.
 * It returns a DDOS object by user specifications.
 */
public class AttackFactory {

    /**
     * This method is the main of attack factory class.
     * It returns a specfic protocol object by second the
     * user specificatios.
     * 
     * @param attackPattern The attack pattern object with all informations about DDOS operation.
     * @param masterAddress The master server machine IP address.
     * @param masterPort The master server machine port value.
     * @return Protocol A protocol object by user specifications.
     */
    public static Protocol createAttack(AttackPattern attackPattern, String masterAddress, int masterPort) {
        if(attackPattern.getProtocol().equalsIgnoreCase("tcp")) {
            return new TransmissionControlProtocol(attackPattern, masterAddress, masterPort);
        }else if(attackPattern.getProtocol().equalsIgnoreCase("udp")) {
            return new UserDatagramProtocol(attackPattern, masterAddress, masterPort);
        }else {
            return new TransmissionControlProtocol(attackPattern, masterAddress, masterPort);
        }
    }
}