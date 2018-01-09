package view;

import java.util.Scanner;
import java.net.Socket;
import utilies.DateHandler;
import java.util.List;
import java.util.ArrayList;
import java.net.InetAddress;
import utilies.AttackPattern;


public class GUI {

    private Scanner scanner;
    private DateHandler dateHandler;
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    public static final String RESET = "\033[0m";  // Text Reset
    
    public GUI() {
        this.scanner = new Scanner(System.in);
        this.dateHandler = new DateHandler();
    }

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
                                                          
        
        if(type.equals("master")) {
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

        }
    }

    private static String white() {
        return WHITE_BRIGHT;
    }

    private static String reset() {
        return RESET;
    }

    private static String red() {
        return RED_BRIGHT;
    }

    private static String purple() {
        return PURPLE_BRIGHT;
    }

    private static String yellow() {
        return YELLOW_BRIGHT;
    }

    private static String green() {
        return GREEN_BRIGHT;
    }

    private static String blue() {
        return BLUE_BRIGHT;
    }

    public static void clean() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public String[] getCommand() {
        try {
            System.out.print(green()+"[~"+red()+"F1R3"+green()+": "+ System.getProperty("user.name") +"@"+InetAddress.getLocalHost().getHostAddress().toString() + "]"+red()+"$ "+green());
            String command = this.scanner.nextLine().toLowerCase();
            return command.split(" ");
        }catch(Exception e) {
            return null;
        }
    }

    public static void errorMessage(String msg) {
        System.out.println(green()+"["+red()+"X"+green()+"] "+ red() + msg);
    }

    public static void help() {
        clean();
        System.out.println("+----------------------------------------------------+");
        System.out.println("+ HELP INFORMATIONS                                  +");
        System.out.println("+----------------------------------------------------+");
        System.out.println("+                                                    +");
        System.out.println("+ How can you use this software?                     +");
        System.out.println("+                                                    +");
        System.out.println("+ To begin you must choose a mode execution option:  +");
        System.out.println("+ $ turbofire <mode>                                 +");
        System.out.println("+                                                    +");
        System.out.println("+ where mode can be: slave or master.                +");
        System.out.println("+                                                    +");
        System.out.println("+                                                    +");
        System.out.println("+ # turbofire > <option>                             +");
        System.out.println("+----------------------------------------------------+");
    }

    public static void showMessage(String msg) {
        System.out.println(green()+"["+blue()+"!"+green()+"] "+msg);
    }

    public static void newClient(String clientAddress) {
        System.out.println(green()+"["+blue()+"!"+green()+"] New zoombie host connected by: " + clientAddress);
    }

    public static void showActiveZoombie(Socket client) {
        System.out.println(green()+"["+blue()+"!"+green()+"] ZOOMBIE: " + client.getRemoteSocketAddress().toString());
    }

    public void pressEnterToContinue(){
        System.out.println(blue()+"Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void showExceptionLog(String exception) {
        System.out.println(green()+"["+red()+"X"+green()+"] "+ red() + "SOFTWARE EXCEPTION: " + yellow() + exception);
    }

    public static void showAttackActivity(int amount, List logs) {
        clean();
        System.out.println("+----------------------------------------------------+");
        System.out.println("+ ATTACK ACTIVITY UPDATE                             +");
        System.out.println("+----------------------------------------------------+");
        showMessage("DATA AMOUNT TRIGGERED: " + amount);
        AttackPattern a = (AttackPattern) logs.get(logs.size() - 1);
        showMessage("LATEST REQUEST TO TARGET BY: " + a.getAdditionalInformation());
        System.out.println("+----------------------------------------------------+");
    }
}