import java.io.*;
import java.util.Map;

public class PR132persona implements Serializable {
    private String nom;
    private String cognom;
    private int edat;

    public PR132persona(String nom, String cognom, int edat){
        super();

        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PR132persona firstPerson = new PR132persona("Alex", "Chica", 20);
        PR132persona secondPerson = new PR132persona("Mario", "Urendez", 20);
        PR132persona thirdPerson = new PR132persona("Fernando", "Alonso", 33);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PR132people.dat"));
        oos.writeObject(firstPerson);
        oos.writeObject(secondPerson);
        oos.writeObject(thirdPerson);

        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PR132people.dat"));
        for(int i=0;i<3;i++){
            PR132persona object = (PR132persona) ois.readObject();

            System.out.println("Nom: " + object.nom + " Cognom: " + object.cognom + " Edat: " + object.edat);
        }

        ois.close();
    }

}
