public class Product {
    private static long idGenerator=0;
    private final long id;
    private String name;
    private String category;
    private long quantity;
    private double price;
    private String supplier_name;


    public Product(String name, String category, long quantity, double price, String supplier_name) {
        this.id = idGenerator++;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.supplier_name = supplier_name;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", supplier_name='" + supplier_name + '\'' +
                '}';
    }
}
