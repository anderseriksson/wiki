import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Anders Eriksson.
 */
public class ReadMD {

    public static void main(String[] args) throws Exception {
        ReadMD.getDataFromMD("root.md");
    }

    public static String getDataFromMD(String mdFile)  {

        File source = null;
        Scanner scanner = null;
        try {
            source = new File(mdFile);
            scanner = new Scanner(source);
            return scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "error " + e.getMessage() + " when reading " + mdFile;
        } catch (IOException e) {
            e.printStackTrace();
            return "error " + e.getMessage() + " when reading " + mdFile;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
