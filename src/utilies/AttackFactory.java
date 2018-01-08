package utilies;

import protocols.interfaces.Protocol;
import protocols.implementation.TransmissionControlProtocol;
import protocols.implementation.UserDatagramProtocol;

public class AttackFactory {

    public static Protocol createAttack(AttackPattern attackPattern) {
        if(attackPattern.getProtocol().equalsIgnoreCase("tcp")) {
            return new TransmissionControlProtocol(attackPattern);
        }else if(attackPattern.getProtocol().equalsIgnoreCase("udp")) {
            return new UserDatagramProtocol(attackPattern);
        }else {
            return new TransmissionControlProtocol(attackPattern);
        }
    }
}