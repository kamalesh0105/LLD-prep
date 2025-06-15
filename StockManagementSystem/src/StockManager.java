import java.util.HashMap;
import java.util.Scanner;

public class StockManager {
    public static HashMap<Long,Product>inventory=new HashMap<>();


    public void AddProduct(Product product){
        inventory.put(product.getId(), product);

    }
    public void displayInventory() {
        inventory.forEach((id, product) -> {
            System.out.println(product.toString());
        });
    }
    public Product createProduct(Scanner sc) {
        String name;
        String category;
        long quantity;
        double price;
        String supplier_name;
        sc.nextLine();
        System.out.print("Enter the Product Name:");
        name=sc.nextLine();
        System.out.print("Enter the Product Category:");
        category=sc.nextLine();
        System.out.print("Enter the Product Quantity:");
        quantity=sc.nextLong();
        sc.nextLine();
        System.out.print("Enter the Product Price:");
        price= sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter the Product SupplierName:");
        supplier_name=sc.nextLine();
        return new Product(name,category,quantity,price,supplier_name);

    }
    public void searchProduct(Scanner sc) {
        System.out.println("\nSearch by:");
        System.out.println("1. Product ID");
        System.out.println("2. Product Name");
        System.out.println("3. Product Category");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Product ID: ");
                long id = sc.nextLong();
                sc.nextLine();
                Product product = inventory.get(id);
                if (product != null) {
                    System.out.println("Product Found: " + product);
                } else {
                    System.out.println("No product found with ID " + id);
                }
            }
            case 2 -> {
                System.out.print("Enter Product Name: ");
                String name = sc.nextLine().trim();
                boolean found = false;
                for (Product p : inventory.values()) {
                    if (p.getName().toLowerCase().contains(name)) {
                        System.out.println(p);
                        found = true;
                    }
                }
                if (!found) System.out.println("No product found with name: " + name);
            }
            case 3 -> {
                System.out.print("Enter Product Category: ");
                String category = sc.nextLine().trim();
                boolean found = false;
                for (Product p : inventory.values()) {
                    if (p.getCategory().toLowerCase().contains(category)) {
                        System.out.println(p);
                        found = true;
                    }
                }
                if (!found) System.out.println("No product found in category: " + category);
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    public String updateStock(long id, long quantity) {
        if(inventory.containsKey(id)){
            Product product=inventory.get(id);
            product.setQuantity(quantity);
            return "Stock Quantity Updated Succcessfully";
        }
        return "Product Not Found";
    }


}
