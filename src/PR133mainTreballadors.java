import java.io.File;
import java.util.List;
import java.util.Scanner;

public class PR133mainTreballadors {
    public static void main(String[] args){
        String filePath = "./src/PR133treballadors.csv";
        Scanner sc = new Scanner(System.in);

        if(!new File(filePath).exists()){
            System.out.println("L'arxiu PR133treballadors no existeix");
        }

        String menu = """
                1)Introduir ID
                2)Modificar el nom
                3)Modificar el cognom
                4)Modificar el departament
                5)Modificar el salari
                6)Sortir
                """;

        int id = -1;

        boolean running = true;
        while(running){
            System.out.println(menu);

            int option = getOption(sc);
            List<String> csv = UtilsCSV.read(filePath);

            switch (option){
                case 1: id = getID(id, sc); break;
                case 2: modifyValue(csv, id, "Nom", sc, filePath); break;
                case 3: modifyValue(csv, id, "Cognom", sc, filePath); break;
                case 4: modifyValue(csv, id, "Departament", sc, filePath); break;
                case 5: modifyValue(csv, id, "Salari", sc, filePath); break;
                case 6: sc.close(); running = false; break;
            }

        }

    }

    static int getID(int id, Scanner sc){
        System.out.println("Introdueix la ID");
        while(!sc.hasNextInt()){
            sc.nextLine();
        }
        id = sc.nextInt();

        return id;
    }

    static int getOption(Scanner sc){
        int option;

        System.out.println("Opcio:");
        while(!sc.hasNextInt()){
            System.out.println("Opcio:");
            sc.nextLine();
        }

        option = sc.nextInt();

        return option;
    }

    static int getColumnId(List<String> csv, String columnName){
        String[] columns = UtilsCSV.getKeys(csv);

        for(int i=0;i<columns.length;i++){
            if (columns[i].equals(columnName)) return i;
        }
        return 0;
    }

    static String[] getColumnValues(List<String> csv){
        return UtilsCSV.getColumnData(csv, "Id");
    }

    static String[] getValue(List<String > csv, int id, String columnName){
        int column = getColumnId(csv, columnName);

        if (column==0){
            return new String[]{};
        }

        String[] columnData = getColumnValues(csv);
        int row = -1;

        for(int i=0;i<columnData.length;i++){
            if(columnData[i].equals(Integer.toString(id))){
                row = i;
                break;
            }
        }

        if(row==-1){
            return new String[]{};
        }

        String value = UtilsCSV.getLineArray(csv.get(row))[column];
        return new String[]{value, Integer.toString(row)};
    }

    static void modifyValue(List<String> csv, int id, String columnName, Scanner sc, String filePath){
        String[] values = getValue(csv, id, columnName);
        if (values.length<2){
            System.out.println("L'id no existeix");
            return;
        }

        System.out.println("Segur que vols modificar la clau " + columnName + " = " + values[0] + "?");
        System.out.println("Opcio(Si=Y): ");
        String option = sc.next();

        if(!option.equals("Y") && !option.equals("y")){
            System.out.println("Modificacio cancelada");
            return;
        }

        System.out.println("Introdueix el nou valor: ");
        String value = sc.next();

        UtilsCSV.update(csv, Integer.parseInt(values[1]), columnName, value);
        UtilsCSV.write(filePath, csv);

        System.out.println(columnName + " s'ha modificat de " + values[0] + " a " + value + "\n");
    }

}
