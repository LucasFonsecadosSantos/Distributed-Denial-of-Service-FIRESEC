
import app.TurboFireMaster;
import app.TurboFireSlave;

public class TurboFire {

    public static void main(String[] args) {
        view.GUI.clean();
        if(args.length == 1) {
            if(args[0].equals("master")) {
                TurboFireMaster master = new TurboFireMaster();
            }else if(args[0].equals("slave")) {
                TurboFireSlave slave = new TurboFireSlave();
            }else {
                System.out.println("[X] Please, type if you want a master or slave version. \n Ex: $ turbofire master \n Ex: $ turbofire slave");
            }
        }else {
            System.out.println("[X] Please, type if you want a master or slave version. \n Ex: $ turbofire master \n Ex: $ turbofire slave");
        }
    }
}