import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

// scanner - scanner barcode enter popup window

public class Display {
    public static final String program_title = "JOHN'S WAREHOUSE";
    public static final int main_window_width = 500;
    public static final int main_window_height = 500;
    public static final int btn_width = 50;
    public static final int btn_height = 50;
    public static final int ticket_window_width = 500;
    public static final int ticket_window_height = 500;
    
    public final JFrame main_scene;
    public final JPanel info;
    public final JLabel productsHTML;
    public final JButton print_btn;
    public Display(){
        // CashRegister cash_register = new CashRegister(); // run for fun
        this.main_scene = new JFrame(program_title);
        main_scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_scene.setSize(main_window_width, main_window_height);

        // MAIN WINDOW - HEADER TEXT
        this.info = new JPanel();
        // info.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 10));
        JLabel header = new JLabel("" +
                "<html><h2 style='text-align: center;'>" + program_title + "</h2><br>" +
                "<h4 style='text-align: center;'>CASH REGISTER 1</h4></html>");

        info.add(header);

        // MAIN WINDOW - ORDER FIELD
        JLabel order_field = new JLabel();
        info.add(order_field);

        // MAIN WINDOW - BUTTONS
        JButton add_item_btn = new JButton("Add Item");
        add_item_btn.setSize(btn_width, btn_height);
        add_item_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CashRegister.cashRegister.openScanner();
            }
        });

        JButton clear_order_btn = new JButton("Clear Order");
        clear_order_btn.setSize(btn_width, btn_height);
        clear_order_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CashRegister.cashRegister.clearOrder();
            }
        });

        JButton pay_btn = new JButton("Make Payment");
        pay_btn.setEnabled(false);
        pay_btn.setSize(btn_width, btn_height);
        pay_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double payment = Double.parseDouble(JOptionPane.showInputDialog("Enter a payment amount: (ie: 24.73)"));
                CashRegister.cashRegister.processPayment(payment);

            }
        });

        print_btn = new JButton("Print Receipt");
        print_btn.setSize(btn_width, btn_height);
        print_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CashRegister.cashRegister.printTicket();
            }
        });

        JButton start_session_btn = new JButton("Start Session");
        start_session_btn.setSize(btn_width, btn_height);
        this.productsHTML = new JLabel("");
        start_session_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start_session_btn.getText().contains("Start")){
                    start_session_btn.setText("End Session");
                    info.add(productsHTML);
                    info.add(add_item_btn);
                    info.add(clear_order_btn);
                    add_item_btn.setEnabled(true);
                    SwingUtilities.updateComponentTreeUI(main_scene);
                    CashRegister.cashRegister.clearOrder();
                    CashRegister.cashRegister.openScanner();
                } else {
                    pay_btn.setEnabled(true);
                    add_item_btn.setEnabled(false);
                    clear_order_btn.setEnabled(false);
                    start_session_btn.setText("Start Session");
                }
            }
        });
        main_scene.add(start_session_btn);
        main_scene.add(pay_btn);
        main_scene.add(print_btn);
        print_btn.setEnabled(false);

        // ** rearrange stuff later **
        info.add(start_session_btn);
        info.add(clear_order_btn);
        info.add(pay_btn);
        info.add(print_btn);

        main_scene.add(info);
        // MAIN WINDOW - DISPLAY (must come last)
        main_scene.setVisible(true);
    }
    public void updateProductList(Double total, Vector<Product> productsList, Vector<Double> quantities){
        StringBuilder JLabelHTML = new StringBuilder();
        JLabelHTML.append("<html>");
        for(int i=0; i<productsList.size(); i++){
            double line_price = (quantities.get(i))*(productsList.get(i).getProductPrice());
            JLabelHTML
                    .append("<h4>")
                    .append(quantities.get(i))
                    .append(" ")
                    .append(productsList.get(i).getProductID())
                    .append(", ")
                    .append(productsList.get(i).getProductName())
                    .append(", $")
                    .append(productsList.get(i).getProductPrice())
                    .append(", $")
                    .append(line_price)
                    .append("</h4><br>");
        }
        JLabelHTML.append("<h3>Total: $");
        String display_total = String.format("%.2f", total);
        JLabelHTML.append(display_total);
        JLabelHTML.append("</h3><br>");
        JLabelHTML.append("</html>");
        productsHTML.setText(JLabelHTML.toString());
        if(total == 0.00 && productsList.size() > 0){
            print_btn.setEnabled(true);
        }
        SwingUtilities.updateComponentTreeUI(main_scene);
    }
}
