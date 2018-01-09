
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
            }else if(args[0].equalsIgnoreCase("slave")) {
                TurboFireSlave slave = new TurboFireSlave();
            }else {
                GUI.errorMessage("Please, type if you want a master or slave version. \n Ex: $ turbofire master \n Ex: $ turbofire slave");
            }
        }else {
            GUI.errorMessage("Please, type if you want a master or slave version. \n Ex: $ turbofire master \n Ex: $ turbofire slave");
        }
    }
}