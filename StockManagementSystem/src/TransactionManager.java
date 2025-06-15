import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TransactionManager {
    private static final HashMap<Long, ArrayList<SalesTransaction>>transactionRecord=new HashMap<>();
    public String sellProduct(Scanner sc) {
        System.out.print("Enter ProductID: ");
        long id = sc.nextLong();
        if(StockManager.inventory.containsKey(id)){
            System.out.print("Enter Quantity of Sale: ");
            long sellQuantity = sc.nextLong();
            sc.nextLine();
            Product product=StockManager.inventory.get(id);
            long newQuantity=((product.getQuantity()-sellQuantity)<0 ? 0:(product.getQuantity()-sellQuantity));
            product.setQuantity(newQuantity);
            SalesTransaction salesTransaction=new SalesTransaction(product,"SELL",sellQuantity, LocalDate.now());

            transactionRecord.putIfAbsent(id, new ArrayList<>());
            transactionRecord.get(id).add(salesTransaction);
            return "Sale Success";
        }
        return "Sale Failed";
    }
    public String PurchaseProduct(Scanner sc) {
        System.out.print("Enter ProductID: ");
        long id = sc.nextLong();
        if(StockManager.inventory.containsKey(id)){
            System.out.print("Enter Quantity of Purchase: ");
            long BuyQuantity = sc.nextLong();
            sc.nextLine();
            Product product=StockManager.inventory.get(id);
            long newQuantity=(product.getQuantity()+BuyQuantity);
            product.setQuantity(newQuantity);
            SalesTransaction salesTransaction=new SalesTransaction(product,"BUY",BuyQuantity, LocalDate.now());
            transactionRecord.putIfAbsent(id, new ArrayList<>());
            transactionRecord.get(id).add(salesTransaction);
            return "Purchase Success";
        }
        return "Purcahse Failes";
    }
    public  void displayTransactions(){
        transactionRecord.forEach((id, transactions) -> {
            System.out.println("Transactions for Product ID: " + id);
            for (SalesTransaction transaction : transactions) {
                System.out.println(transaction.toString());
            }
        });


    }

}
