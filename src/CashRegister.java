public class CashRegister {
    static CashRegister cashRegister;
    Display display;
    TicketPrinter ticketPrinter;
    Scanner scanner;
    Order order;
    Receipt receipt;
    public CashRegister(){
        this.display = new Display();
        this.ticketPrinter = new TicketPrinter();
        this.scanner = new Scanner();
        this.order = new Order();
        this.receipt = new Receipt();
    }
    public void printTicket(){
        this.receipt.newReceipt(this.order);
    }
    public void processPayment(double payment){
        this.order.applyPayment(payment);
    }
    public static void main(String[] args){
        cashRegister = new CashRegister();
        cashRegister.receipt.setPrinter(cashRegister.ticketPrinter);
        cashRegister.order.setDisplay(cashRegister.display);
    }
    public void openScanner(){
        if(!this.order.addItem(this.scanner.scanBarcode())){
            openScanner();
        }
    }
    public void clearOrder(){ this.order.clearOrder(); }
}
