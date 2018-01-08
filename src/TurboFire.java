
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

/**
 * 
 */
public class TurboFire {

    public static void main(String[] args) {
        view.GUI.clean();
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("master")) {
                TurboFireMaster master = new TurboFireMaster();
            }else if(args[0].equalsIgnoreCase("slave")) {
                TurboFireSlave slave = new TurboFireSlave();
            }else {
                System.out.println("[X] Please, type if you want a master or slave version. \n Ex: $ turbofire master \n Ex: $ turbofire slave");
            }
        }else {
            System.out.println("[X] Please, type if you want a master or slave version. \n Ex: $ turbofire master \n Ex: $ turbofire slave");
        }
    }
}