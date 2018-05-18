import java.io.*;
import java.util.Properties;

import static java.nio.file.Paths.get;

class FileManagement {
    Properties properties;


    FileManagement() {
        get ("IdeaProjects/BlackJack", "save.ser");
        properties = new Properties();
    }

    Properties readFile() throws IOException {
        File file = new File("save.ser");
        if (file.exists()) {
            FileInputStream in = new FileInputStream ("save.ser");
            properties.load (in);
            in.close ();
            return properties;
        }
        else return properties;
    }

    void writeFile() throws IOException {
        FileOutputStream out = new FileOutputStream("save.ser");

        properties.store(out, "---Save File---");
    }
}
