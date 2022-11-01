import javax.swing.*;
import java.awt.*;

public class TicketPrinter {

    private String text = "";

    public TicketPrinter(){
    }
    public void printTicket(String text){
        this.text = text;

        JFrame ticket = new JFrame("Receipt");
        ticket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticket.setSize(Display.ticket_window_width, Display.ticket_window_height);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel header = new JLabel("<html><h2>" + text + "</h2></html>");
        panel.add(header);
        ticket.add(panel);
        ticket.setVisible(true);
    }
}
