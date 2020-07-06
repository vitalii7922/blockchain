package blockchain;

import java.io.*;

class SerializationUtils {

    private SerializationUtils() {
    }

    static void serialize(Object obj) throws IOException {
        FileOutputStream fos = new FileOutputStream("blockchain/file.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        }
    }

    static Object deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("blockchain/file.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        Object obj;
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            obj = ois.readObject();
        }
        return obj;
    }
}
