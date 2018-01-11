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
package view;

import java.util.Scanner;
import java.net.Socket;
import utilies.DateHandler;
import java.util.List;
import java.util.ArrayList;
import java.net.InetAddress;
import utilies.AttackPattern;


/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class represents a Graphical user interface object,
 * that in this program, is a simple text command interface.
 * It contains a lot of static methods and all of methods that
 * interact with the user.
 */
public class GUI {

    /**
     * The Java scanner object for capturing all
     * data inputed by the user.
     */
    private Scanner scanner;

    /**
     * A date handler object that returns date formmated by text.
     */
    private DateHandler dateHandler;

    /**
     * The bash string color code Black bright.
     */
    public static final String BLACK_BRIGHT  = "\033[0;90m";  // BLACK

    /**
     * The bash string color code Red bright.
     */
    public static final String RED_BRIGHT    = "\033[0;91m";    // RED

    /**
     * The bash string color code Green bright.
     */
    public static final String GREEN_BRIGHT  = "\033[0;92m";  // GREEN

    /**
     * The bash string color code Yellow bright.
     */
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    
    /**
     * The bash string color code Blue bright.
     */
    public static final String BLUE_BRIGHT   = "\033[0;94m";   // BLUE
    
    /**
     * The bash string color code Purple bright.
     */
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    
    /**
     * The bash string color code Cyan bright.
     */
    public static final String CYAN_BRIGHT   = "\033[0;96m";   // CYAN
    
    /**
     * The bash string color code White bright.
     */
    public static final String WHITE_BRIGHT  = "\033[0;97m";  // WHITE
    
    /**
     * The bash string color code Reset for original font style bright.
     */
    public static final String RESET         = "\033[0m";  // Text Reset
    
    /**
     * The graphical user interface object constructor.
     * It only instances the scanner and date handler objects
     * attribute state.
     */
    public GUI() {
        this.scanner = new Scanner(System.in);
        this.dateHandler = new DateHandler();
    }

    /**
     * This method is responsible for shows a welcome message
     * when the user starts the program.
     * It receives a type or version of turbo fire, slave or 
     * master and a port to shows the user.
     * 
     * @param type The turbo fire version (Slave or Master).
     * @param portOrIp Information for shows to the user.
     */
    public void welcome(String type, int portOrIp) {
        clean();
        System.out.println(red()+"\n\n\n                (           )    (    (   (        ");
        System.out.println("    *   )      )\\ )  (  ( /\\(    )\\ ) )\\ ))\\ )     ");
        System.out.println("  ` )  /(   ( (()/(( )\\ )\\())  (()/((()/(()/((    ");
        System.out.println("   ( )(_))  )\\ /(_))((_|(_)\\    /(_))/(_))(_))\\   ");
        System.out.println("  (_(_())_ ((_|_))((_)_  ((_)  (_))_(_))(_))((_)  ");
        System.out.println(yellow()+"  |_   _| | | | _ \\| _ )/ _ \\  | |_ |_ _| _ \\ __| ");
        System.out.println("    | | | |_| |   /| _ \\ (_) | | __| | ||   / _|  ");
        System.out.println(purple()+"    |_|  \\___/|_|_\\|___/\\___/  |_|  |___|_|_\\___| \n\n\n");
        if(type.equalsIgnoreCase("master")) {
            System.out.println(red()+"+----------------------------------------------------+");
            System.out.println("+   WELCOME TO TURBO FIRE NETWORK STRESS SOFTWARE    +");
            System.out.println("+                                                    +");
            System.out.println("+   Written by:                                      +");
            System.out.println("+              Lucas Fonseca dos Santos              +");
            System.out.println("+              Otavio Andrade                        +");
            System.out.println("+----------------------------------------------------+");
            System.out.println("+ [!] CONNECTION ESTABLISHED ON THE PORT: "+portOrIp+"       +");
            System.out.println("+----------------------------------------------------+\n");
        }else {
            System.out.println("                    uuuuuuu                    ");
            System.out.println("                uu$$$$$$$$$$$uu                ");
            System.out.println("             uu$$$$$$$$$$$$$$$$$uu             ");
            System.out.println("            u$$$$$$$$$$$$$$$$$$$$$u            ");
            System.out.println("           u$$$$$$$$$$$$$$$$$$$$$$$u           ");
            System.out.println("          u$$$$$$$$$$$$$$$$$$$$$$$$$u          ");
            System.out.println("          u$$$$$$$$$$$$$$$$$$$$$$$$$u          ");
            System.out.println("          u$$$$$$\"   \"$$$\"   \"$$$$$$u          ");
            System.out.println("           $$$$\"      u$u       $$$$\"          ");
            System.out.println("           $$$u       u$u       u$$$           ");
            System.out.println("           $$$u      u$$$u      u$$$           ");
            System.out.println("             $$$$uu$$$   $$$uu$$$$\"            ");
            System.out.println("              $$$$$$$\"   \"$$$$$$$\"             ");
            System.out.println("               u$$$$$$$u$$$$$$$u               ");
            System.out.println("                u$\"$\"$\"$\"$\"$\"$u                ");
            System.out.println("     uuu        $$u$ $ $ $ $u$$       uuu      ");
            System.out.println("    u$$$$        $$$$$u$u$u$$$       u$$$$     ");
            System.out.println("     $$$$$uu      \"$$$$$$$$$\"     uu$$$$$$     ");
            System.out.println("   u$$$$$$$$$$$uu    \"\"\"\"\"    uuuu$$$$$$$$$$   ");
            System.out.println("   $$$$\"\"\"$$$$$$$$$$uuu   uu$$$$$$$$$\"\"\"$$$\"   ");
            System.out.println("    \"\"\"\"      \"\"$$$$$$$$$$$uu \"\"$\"\"\"           ");
            System.out.println("               uuuu \"\"$$$$$$$$$$uuu            ");
            System.out.println("     u$$$uuu$$$$$$$$$uu \"\"$$$$$$$$$$$uuu$$$    ");
            System.out.println("     $$$$$$$$$$\"\"\"\"           \"\"$$$$$$$$$$$\"   ");
            System.out.println("      \"$$$$$\"                      \"\"$$$$\"\"    ");
            System.out.println("        $$$\"                         $$$$\"     ");
        }
    }

    /**
     * This method only shows to the user a skull by ASCII art.
     */
    public void showSkull() {
        System.out.println("                    uuuuuuu                    ");
        System.out.println("                uu$$$$$$$$$$$uu                ");
        System.out.println("             uu$$$$$$$$$$$$$$$$$uu             ");
        System.out.println("            u$$$$$$$$$$$$$$$$$$$$$u            ");
        System.out.println("           u$$$$$$$$$$$$$$$$$$$$$$$u           ");
        System.out.println("          u$$$$$$$$$$$$$$$$$$$$$$$$$u          ");
        System.out.println("          u$$$$$$$$$$$$$$$$$$$$$$$$$u          ");
        System.out.println("          u$$$$$$\"   \"$$$\"   \"$$$$$$u          ");
        System.out.println("           $$$$\"      u$u       $$$$\"          ");
        System.out.println("           $$$u       u$u       u$$$           ");
        System.out.println("           $$$u      u$$$u      u$$$           ");
        System.out.println("             $$$$uu$$$   $$$uu$$$$\"            ");
        System.out.println("              $$$$$$$\"   \"$$$$$$$\"             ");
        System.out.println("               u$$$$$$$u$$$$$$$u               ");
        System.out.println("                u$\"$\"$\"$\"$\"$\"$u                ");
        System.out.println("     uuu        $$u$ $ $ $ $u$$       uuu      ");
        System.out.println("    u$$$$        $$$$$u$u$u$$$       u$$$$     ");
        System.out.println("     $$$$$uu      \"$$$$$$$$$\"     uu$$$$$$     ");
        System.out.println("   u$$$$$$$$$$$uu    \"\"\"\"\"    uuuu$$$$$$$$$$   ");
        System.out.println("   $$$$\"\"\"$$$$$$$$$$uuu   uu$$$$$$$$$\"\"\"$$$\"   ");
        System.out.println("    \"\"\"\"      \"\"$$$$$$$$$$$uu \"\"$\"\"\"           ");
        System.out.println("               uuuu \"\"$$$$$$$$$$uuu            ");
        System.out.println("     u$$$uuu$$$$$$$$$uu \"\"$$$$$$$$$$$uuu$$$    ");
        System.out.println("     $$$$$$$$$$\"\"\"\"           \"\"$$$$$$$$$$$\"   ");
        System.out.println("      \"$$$$$\"                      \"\"$$$$\"\"    ");
        System.out.println("        $$$\"                         $$$$\"     ");
    }

    /**
     * The white attribute state accessor method.
     */
    private static String white() {
        return WHITE_BRIGHT;
    }

    /**
     * The reset attribute state accessor method.
     */
    private static String reset() {
        return RESET;
    }

    /**
     * The red attribute state accessor method.
     */
    private static String red() {
        return RED_BRIGHT;
    }

    /**
     * The purple attribute state accessor method.
     */
    private static String purple() {
        return PURPLE_BRIGHT;
    }

    /**
     * The yellow attribute state accessor method.
     */
    private static String yellow() {
        return YELLOW_BRIGHT;
    }

    /**
     * The green attribute state accessor method.
     */
    private static String green() {
        return GREEN_BRIGHT;
    }

    /**
     * The blue attribute state accessor method.
     */
    private static String blue() {
        return BLUE_BRIGHT;
    }

    /**
     * This method is reponsible for cleaning the console.
     * It uses a bash code for to do clean the console.
     */
    public static void clean() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    /**
     * This method returns a command entered by the user
     * for the method caller.
     * 
     * @return String[] String array with all arguments entered by the user.
     */
    public String[] getCommand() {
        try {
            System.out.print(green()+"[~"+red()+"F1R3"+green()+": "+ System.getProperty("user.name") +"@"+InetAddress.getLocalHost().getHostAddress().toString() + "]"+red()+"$ "+green());
            String command = this.scanner.nextLine().toLowerCase();
            return command.split(" ");
        }catch(Exception e) {
            return null;
        }
    }

    /**
     * This method is responsible for shows to the user
     * a error message received by parameter.
     * 
     * @param msg A error message.
     */
    public static void errorMessage(String msg) {
        System.out.println(green()+"["+red()+"X"+green()+"] "+ red() + msg);
    }

    /**
     * This mehtod helps the user kk.
     */
    public static void help() {
        clean();
        line();
        System.out.println("+ HELP INFORMATIONS                                  +");
        line();
        System.out.println("+                                                    +");
        System.out.println("+ How can you use this software?                     +");
        System.out.println("+                                                    +");
        System.out.println("+ To begin you must choose a mode execution option:  +");
        System.out.println("+ $ turbofire <mode>                                 +");
        System.out.println("+                                                    +");
        System.out.println("+ where mode can be: slave or master.                +");
        System.out.println("+                                                    +");
        System.out.println("+                                                    +");
        System.out.println("+ # [turbofire user@host]$ <command>                 +");
        System.out.println("+                                                    +");
        line();
        System.out.println("+                                                    +");
        System.out.println("+ HOW DO I MAKE AN ATTACK?                           +");
        System.out.println("+                                                    +");
        System.out.println("+ You need to type \"fire\" followed by the            +"); 
        System.out.println("+ arguments.                                         +"); 
        System.out.println("+ [turbofire user@host]$ fire <option>               +");
        System.out.println("+                                                    +");
        line();
        System.out.println("+                                                    +");
        System.out.println("+ HOW CAN I MONITOR REAL-TIME OPERATION?             +");
        System.out.println("+                                                    +");
        System.out.println("+ After you have connected the master server and     +");
        System.out.println("+ made the attack, so you need to enter the          +");
        System.out.println("+ following command:                                 +");
        System.out.println("+ [turbofire user@host]$ monitoring                  +");
        System.out.println("+                                                    +");
        line();
        System.out.println("+                                                    +");
        System.out.println("+ ARGUMENTS TO MAKE AN ATTACK:                       +");
        System.out.println("+                                                    +"); 
        System.out.println("+ --target <ip_address> | -ip <ip_address>           +");
        System.out.println("+ --thread <amount>     | -t <amount>                +");
        System.out.println("+ --port <number>       | -p <number>                +");
        System.out.println("+ --protocol <tcp,udp>  | -pl <tcp,udp>              +");
        System.out.println("+ --timeOut <value>     | -to <value>                +");
        System.out.println("+ --message <message>   | -m <message>               +");
        System.out.println("+ --range <value>       | -r <value>                 +");
        System.out.println("+                                                    +");
        line();
    }

    /**
     * This method only shows to the user a simple system message.
     * 
     * @param msg A text message from system.
     */
    public static void showMessage(String msg) {
        System.out.println(green()+"["+blue()+"!"+green()+"] "+msg);
    }

    /**
     * This method is responsible for shows a new client connected.
     * 
     * @param clientAddress The zombie client IP address.
     */
    public static void newClient(String clientAddress) {
        System.out.println(green()+"["+blue()+"!"+green()+"] New zoombie host connected by: " + clientAddress);
    }

    /**
     * This method lists all active zombie clients connected.
     * 
     * @param zombies The arraylist with all zombies IP address.
     */
    public void showActiveZoombie(ArrayList<String> zombies) {
        line();
        System.out.println("+ ACTIVES ZOMBIE NETWORK                             +"); 
        line();
        if(zombies != null) {
            for(int i = 0 ; i < zombies.size() ; i++) {
                showMessage("ZOMBIE N° "+ (i+1) + ": " + zombies.get(i));
            }
        }
        pressEnterToContinue();
        line();
    }

    /**
     * This is a simple method that is called for get a enter key input
     * from user.
     */
    public void pressEnterToContinue(){
        System.out.println(green() + "[" + white() + "#" + green() + "]" + blue() + " Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * This method formats a java runtime exception.
     * 
     * @param exception Java exception to string.
     */
    public static void showExceptionLog(String exception) {
        System.out.println(green() + "[" + red() + "X" + green() + "] " + red() + "SOFTWARE EXCEPTION: " + yellow() + exception);
    }

    /**
     * This method shows the DDOS operation in real-time.
     * 
     * @param amount A integer shot amount.
     * @param logs The attack patterns receveid by zombies response.
     * @param hosts The java array list object with all ip address.
     * @param attackPattern The attack pattern object.
     */
    public static void showAttackActivity(int amount, List logs, ArrayList<String> hosts, AttackPattern attackPattern) {
        clean();
        line();
        System.out.println("+ ATTACK ACTIVITY UPDATE                             +");
        line();
        showMessage("DATA AMOUNT TRIGGERED: " + amount);
        AttackPattern a = (AttackPattern) logs.get(logs.size() - 1);
        showMessage("LATEST REQUEST TO TARGET BY: " + a.getAdditionalInformation());
        showMessage("ACTIVE ZOMBIES:");
        int k = 0;
        for(String s : hosts) {
            k++;
            System.out.println(k+") " + s);
        }
        line();
    }

    private static void line() {
        System.out.println("+----------------------------------------------------+");
    }
}