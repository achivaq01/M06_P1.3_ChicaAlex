import java.io.IOException;
import java.util.*;


public class Main {
    static Scanner in = new Scanner(System.in);


    public static void main(String[] args) throws InterruptedException, IOException {
        boolean running = true;
        while (running) {
            String menu = "Escull una opció:";
            menu = menu + "\n 0) PR130mainPersonesHashmap";
            menu = menu + "\n 1) PR131hashmap";
            menu = menu + "\n 2) PR132persona";
            menu = menu + "\n 3) PR133mainTreballadors";
            menu = menu + "\n 100) Sortir";
            System.out.println(menu);


            int opcio = Integer.valueOf(llegirLinia("Opció:"));
            try {
                switch (opcio) {
                    case 0: PR130mainPersonesHashmap.main(args); break;
                    case 1: PR131hashmap.main(args); break;
                    case 2: PR132persona.main(args); break;
                    case 100: running = false; break;
                    default: break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        in.close();
    }


    static public String llegirLinia (String text) {
        System.out.print(text);
        return in.nextLine();
    }
}
