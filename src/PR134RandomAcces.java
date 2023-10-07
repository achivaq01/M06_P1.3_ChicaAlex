import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class PR134RandomAcces {
    private static final int ID_SIZE = 4;
    private static final int CHAR_SIZE = 2;
    private static final int NAME_SIZE = 40;
    private static final int GRADE_SIZE = 4;

    private static int id;
    private static String name;
    private static float grade;

    public static void main(String[] args){
        try (RandomAccessFile raf = new RandomAccessFile("./src/alumnes.dat", "rw")) {

            String menu = "1)Afegir alumne\n2)Actualitzar nota\n3)Consultar nota\n4)Mostrar alumno\n5)Sortir";
            Scanner sc = new Scanner(System.in);
            String inp = "opcio:";
            int opt;

            boolean running = true;
            while(running){
                System.out.println(menu);

                System.out.println(inp);
                while(!sc.hasNextInt()){
                    System.out.println(inp);
                    sc.nextLine();
                }
                opt = sc.nextInt();

                switch (opt) {
                    case 1 -> afegirAlumne(raf, sc);
                    case 2 -> actualitzarNota(raf, sc);
                    case 3 -> mostrarNota(raf, sc);
                    case 4 -> mostrarAlumno(raf, sc);
                    case 5 -> running = false;
                }

            }

        } catch (IOException e){
            e.printStackTrace();

        }

    }

    public static void afegirAlumne(RandomAccessFile raf, Scanner sc) throws IOException {
        getUserInput(sc);
        id = (int) getNewId(raf);

        raf.seek(raf.length());

        String paddedName = getPaddedName(name);
        if (paddedName.equals("-1")){
            System.out.println("Error al afegir:\nEl nom es molt llarg\n");
            return;
        }

        raf.writeInt(id);
        raf.writeChars(paddedName);
        raf.writeFloat(grade);
    }

    private static String getPaddedName(String name) {
        if (name.length() > NAME_SIZE) {
            return "-1";
        }

        return String.format("%1$-" + NAME_SIZE + "s", name).replace(' ', '\0');    }

     private static void getUserInput(Scanner sc){
        sc.nextLine();
        System.out.println("Nom del nou alumne:");
        name = sc.nextLine();

        System.out.println("Nota del alumne:");
        while (!sc.hasNextFloat()){
            System.out.println("Nota del alumne:");
            sc.nextLine();
        }

        grade = sc.nextFloat();
     }

     private static long getNewId(RandomAccessFile raf) throws IOException {
        long nElements = raf.length() / (ID_SIZE + NAME_SIZE * CHAR_SIZE);

        return nElements + 1;
     }

     private static void actualitzarNota(RandomAccessFile raf, Scanner sc) throws IOException {
        sc.nextLine();
        getId(sc, raf);

        System.out.println("Nova nota:");
        while(!sc.hasNextFloat()){
            System.out.println("Nova nota:");
            sc.nextLine();
        }
        grade = sc.nextFloat();

        raf.seek(getSeekPosition(id) + ID_SIZE);
        raf.writeFloat(grade);

        System.out.println("La nota del alumno con Id " + id + " ha sido actualizada por " + grade);

     }

     private static int getSeekPosition(int id) {
         return (id - 1) * (ID_SIZE + NAME_SIZE * CHAR_SIZE + GRADE_SIZE);
    }

    private static void getId(Scanner sc, RandomAccessFile raf) throws IOException {
        System.out.println("Id del alumne:");
        while(!sc.hasNextInt()){
            System.out.println("Id del alumne:");
            sc.nextLine();
        }

        if(id > getNewId(raf) - 1){
            System.out.println("L'Id no existeix");
            return;
        }
        id = sc.nextInt();
    }

    private static void mostrarNota(RandomAccessFile raf, Scanner sc) throws IOException {
        getId(sc, raf);

        raf.seek(getSeekPosition(id) + ID_SIZE + NAME_SIZE * CHAR_SIZE);
        float grade = raf.readFloat();

        System.out.println("La nota del alumne amb id " + id + " es " + grade);
    }

    public static void mostrarAlumno(RandomAccessFile raf, Scanner sc) throws IOException {
        getId(sc, raf);

        raf.seek(getSeekPosition(id));
        raf.readInt();

        String name = "";
        for (int i = 0; i < NAME_SIZE; i++) {
            char c = raf.readChar();
            if (c != '\0') {
                name += c;
            }
        }

        float grade = raf.readFloat();

        System.out.println("Id: " + id);
        System.out.println("Nombre: " + name);
        System.out.println("Nota: " + grade);
    }

}
