import java.time.LocalDate;
import java.time.LocalDateTime;

public class SalesTransaction {
    private static long transactionIdGenerator=0;
    private final long transactionId;
    private Product product;
    private String type;
    private long quantity;
    private LocalDate date;

    public SalesTransaction(Product product, String type, long quantity, LocalDate date) {
        this.transactionId = transactionIdGenerator++;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.date = date;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SalesTransaction{" +
                "transactionId=" + transactionId +
                ", product=" + product +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
}
