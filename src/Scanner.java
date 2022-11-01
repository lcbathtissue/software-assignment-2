import javax.swing.*;
import java.util.Vector;

public class Scanner {
    public int scanBarcode(){
        int barcode = -1;
        try {
            barcode = Integer.parseInt(JOptionPane.showInputDialog("Enter a barcode: (ie: 1001)"));
        } catch(Exception error) {
            System.out.println(error);
            barcode = -2;
        }
        return barcode;
    }
}
