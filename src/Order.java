import java.util.Vector;

public class Order {
    private Display display;
    private double order_total = 0.0;
    private double total_remaining = 0.0;
    private Vector<Product> order_products = new Vector<Product>();
    private Vector<Double> order_product_quantities = new Vector<Double>();
    public void setDisplay(Display display){
        this.display = display;
    }
    public void updateDisplay(){
        assert order_products != null;
        assert order_product_quantities != null;
        this.display.updateProductList(total_remaining, order_products, order_product_quantities);
    }
    public void clearOrder(){
        this.total_remaining = 0.0;
        this.order_total = 0.0;
        this.order_products = new Vector<Product>();
        this.order_product_quantities = new Vector<Double>();
        updateDisplay();
    }
    public void applyPayment(double payment){
        if(payment <= total_remaining){
            total_remaining -= payment;

            System.out.println("new order total after payment\n" + total_remaining);
            updateDisplay();
        }
    }
    public boolean addItem(int product_ID){
        if(product_ID == -1){
            return true;
        }
        Vector<String> product_vector = DataBase.getProductInfo(product_ID);
        System.out.println(product_vector.toString());
        assert product_vector != null;
        Product product = new Product();
        if(product_vector.size() > 2){
            product.setProductID(Integer.parseInt(product_vector.get(0)));
            product.setProductName(product_vector.get(1));
            product.setProductPrice(Double.parseDouble(product_vector.get(2)));
        } else {
            return false;
        }
        for(int i = 0; i< order_products.size(); i++){
            if(product.getProductID() == this.order_products.get(i).getProductID()){
                // if the product is already on the order
                this.order_product_quantities.set(i, order_product_quantities.get(i) + 1);
                total_remaining += product.getProductPrice();
                order_total += product.getProductPrice();
                updateDisplay();
                return true;
            }
        }
        // if the product is not already on the order
        order_products.add(product);
        order_product_quantities.add(1.0);
        total_remaining += product.getProductPrice();
        order_total += product.getProductPrice();
        updateDisplay();
        return true;
    }
    public double getTotalRemaining(){
        return this.total_remaining;
    }
    public double getTotal(){
        return this.order_total;
    }
    public boolean isOrderEmpty(){
        return order_products.size() != 0;
    }
    public Vector<Product> getItems(){
        return this.order_products;
    }
    public Vector<Double> getQuantities(){
        return this.order_product_quantities;
    }
}
