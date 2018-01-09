
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
import app.TurboFireMaster;
import app.TurboFireSlave;
import view.GUI;

/**
 * 
 */
public class TurboFire {

    public static void main(String[] args) {
        GUI.clean();
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("master")) {
                TurboFireMaster master = new TurboFireMaster();
            }else {
                GUI.errorMessage("Please, type if you want a master or slave version. \n Ex: $ turbofire master \n Ex: $ turbofire slave <master_ip_address> <master_port>");
            }
        }else if(args.length == 3 && args[0].equalsIgnoreCase("slave")) {
            TurboFireSlave slave = new TurboFireSlave(args[1], Integer.parseInt(args[2]));
        }else {
            GUI.errorMessage("Please, if you want start a slave zoombie, then you must pass a master IP address and its port. \n Ex. $ turbofire slave 192.168.0.1 2525");
        }
    }
}