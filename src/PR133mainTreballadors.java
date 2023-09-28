import javax.swing.*;
import java.util.Scanner;

public class PR133mainTreballadors {
    public static void main(String[] args){
        int id;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce the ID");
        while(!sc.hasNextInt()){
            sc.nextLine();
        }
        id = sc.nextInt();

    }
}
