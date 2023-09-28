import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PR131hashmap implements Serializable {
    private HashMap<String, String> hashMap;

    public PR131hashmap(){
        super();

        hashMap = new HashMap<>();
        hashMap.put("Espanya", "Madrid");
        hashMap.put("Italia", "Roma");
        hashMap.put("França", "Paris");
        hashMap.put("Anglaterra", "Londres");

    }

    public void escriu() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PR131HashMapData.ser"));
        oos.writeObject(this);

        oos.close();

    }

    public void llegeix() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PR131HashMapData.ser"));
        PR131hashmap readClass = (PR131hashmap) ois.readObject();

        ois.close();

        for (Map.Entry<String, String> entry : readClass.hashMap.entrySet()){
            System.out.println("Pais: " + entry.getKey() + " Capital: " + entry.getValue());
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PR131hashmap test = new PR131hashmap();
        test.escriu();
        test.llegeix();
    }
}
