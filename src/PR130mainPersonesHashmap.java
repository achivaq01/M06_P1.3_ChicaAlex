import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PR130mainPersonesHashmap {

    public static void main(String[] args){

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alex", 20);
        hashMap.put("Marc", 32);
        hashMap.put("Andrea", 19);
        hashMap.put("Pedro", 51);
        hashMap.put("Fernando", 33);

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("PR130persones.dat"));

            dos.writeInt(hashMap.size());

            for(Map.Entry<String, Integer> entry : hashMap.entrySet()){
                String name = entry.getKey();
                int age = entry.getValue();

                dos.writeUTF(name);
                dos.writeInt(age);
            }

            dos.close();

            DataInputStream dis = new DataInputStream(new FileInputStream("PR130persones.dat"));
            int length = dis.readInt();

            for(int i = 0; i<length; i++){
                String name = dis.readUTF();
                int age = dis.readInt();

                System.out.println("Name: " + name + " Age: " + age);
            }

            dis.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
