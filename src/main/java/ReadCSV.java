import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anders Eriksson.
 */
public class ReadCSV {

    public static void main(String[] args) throws Exception {
        ReadCSV.getDataFromCSV("root.csv");
    }

    public static Map<String, Map<String, String>> getDataFromCSV(String csvFile) throws Exception {


        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        boolean readHeader = true;

        Map<String, Map<String, String>> response = new HashMap<String, Map<String, String>>();
        int rowNumber = 0;
        try {
            String[] header = new String[]{};

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                if (readHeader) {
                    header = line.split(cvsSplitBy);
                    Map<String, String> headerMap = new HashMap<String, String>();
                    for (String columnName : header) {
                        headerMap.put(columnName, "String");
                    }

                    response.put("header"  , headerMap);

                } else {

                    String[] data = line.split(cvsSplitBy);

                    Map<String, String> entry = new HashMap<String, String>();

                    int i = 0;
                    for (String columnName : header) {
                        System.out.print(columnName + "=" + data[i]);
                        entry.put(columnName, data[i++]);
                    }
                    response.put("row " + (rowNumber++), entry);
                }
                System.out.println();
                readHeader = false;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
        return response;
    }
}
