import javax.swing.*;
import java.text.NumberFormat;
import java.util.Vector;

public class Receipt {

//    public static NumberFormat money_formatter = NumberFormat.getCurrencyInstance();
//    money_formatter.setGroupingUsed(true);
//    money_formatter.setMinimumFractionDigits(2);
//    money_formatter.setMaximumFractionDigits(2);

    private TicketPrinter printer;
    public void newReceipt(Order order){
        double order_total = order.getTotal();
        Vector<Product> order_products = order.getItems();
        Vector<Double> order_product_quantities = order.getQuantities();

        StringBuilder HTML = new StringBuilder();
        HTML.append("<html>");
        for(int i=0; i<order_products.size(); i++){
            double line_price = (order_product_quantities.get(i))*(order_products.get(i).getProductPrice());
            HTML
                    .append("<h4>")
                    .append(order_product_quantities.get(i))
                    .append(" ")
                    .append(order_products.get(i).getProductID())
                    .append(", ")
                    .append(order_products.get(i).getProductName())
                    .append(", $")
                    .append(order_products.get(i).getProductPrice())
                    .append(", $")
                    .append(line_price)
                    .append("</h4><br>");
        }
        HTML.append("<h3>Total: $");
        String display_total = String.format("%.2f", order_total);
        HTML.append(display_total);
        HTML.append("</h3><br>");
        HTML.append("</html>");
        printer.printTicket(HTML.toString());
    }

    public void setPrinter(TicketPrinter printer){
        this.printer = printer;
    }
    private void updatePrinter(String text){
        printer.printTicket(text);
    }
}
