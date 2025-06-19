import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StockManager stockManager=new StockManager();
        TransactionManager transactionManager=new TransactionManager();
        while(true){
            System.out.println("""
                    StockManagementSystem Portal
                    Choose Your option\n
                    1.Add a Product
                    2.Update Stock Quantity
                    3.Search Product
                    4.Sell Product
                    5.Purchase Product
                    6.DisplayProducts
                    7.DisplayTransaction
                    8.GenerateReports
                    9.Exit
                    """);
            System.out.print("Enter your Choice:");
            int choice=sc.nextInt();
          switch (choice){
              case 1:
                  //Add Product
                  System.out.println("Add product");
                  Product product=stockManager.createProduct(sc);
                  stockManager.AddProduct(product);
                  stockManager.displayInventory();
                  break;
              case 2:
                  //Update Product
                  // TodO :change to add or remove x quantity(also manage negative quantity)
                  System.out.println("Update product Quantity:");
                  System.out.print("Enter Product ID:");
                  long id=sc.nextLong();
                  sc.nextLine();
                  System.out.print("Enter new Product Quantity:");
                  long quantity=sc.nextLong();
                  System.out.println(stockManager.updateStock(id,quantity));
                  break;
              case 3:
                  System.out.println("Search Product:");
                  stockManager.searchProduct(sc);
                  break;
              case 4:
                  System.out.println("Sell Product:");
                  System.out.println(transactionManager.sellProduct(sc));
                  break;
              case 5:
                  System.out.println("Purchase Product:");
                  System.out.println(transactionManager.PurchaseProduct(sc));
                  break;
              case 6:
                  stockManager.displayInventory();
                  break;
              case 8:
                  transactionManager.displayTransactions();
                  break;
              case 9:
                  return;
              default:
                  System.out.println("Invalid choice");
          }
        }
    }
}
