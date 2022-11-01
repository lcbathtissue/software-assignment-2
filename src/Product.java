public class Product {
    private int product_ID;
    private String product_name;
    private double product_price;
    public Product(){
        this.product_ID = 0;
        this.product_name = null;
        this.product_price = 0.0;
    }
    public Product(int product_ID, String product_name, double product_price){
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.product_price = product_price;
    }
    public int getProductID(){
        return this.product_ID;
    }
    public String getProductName(){
        return this.product_name;
    }
    public double getProductPrice(){
        return this.product_price;
    }
    public void setProductID(int product_ID){
        this.product_ID = product_ID;
    }
    public void setProductName(String product_name){
        this.product_name = product_name;
    }
    public void setProductPrice(double product_price){
        this.product_price = product_price;
    }
}
