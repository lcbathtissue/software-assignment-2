import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class DataBase {
    public static Vector<String> getProductInfo(int id) {
        Vector<String> return_vector = new Vector<String>();
        try {
            File myObj = new File("database");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(Integer.toString(id))){
                    String[] parts = data.split(", ");
                    return_vector.add(parts[0]); // ID
                    return_vector.add(parts[1]); // product_name
                    return_vector.add(parts[2]); // double price = Double.parseDouble(parts[2]); // will convert later easier to return 3 strings
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred opening database file.");
            e.printStackTrace();
        }
        return return_vector;
    }
}
