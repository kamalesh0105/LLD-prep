import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        TaxiManager taxiManager=new TaxiManager(6);
        while(true){
            System.out.println("""
                    StockManagementSystem Portal
                    Choose Your option\n
                    1.Book Taxi
                    2.Taxi Booking Details
                    3.Exit
                    """);
            System.out.print("Enter your Choice:");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Enter CustomerID: ");
                    int customerId=sc.nextInt();
                    System.out.print("Pickup Point(A-F): ");
                    sc.nextLine();
                    char pickupPoint=sc.nextLine().charAt(0);
                    System.out.print("Drop Point(A-F):");
                    char dropPoint=sc.nextLine().charAt(0);
                    if(pickupPoint < 'A' || pickupPoint > 'F' || dropPoint < 'A' || dropPoint > 'F') {
                        System.out.println("Invalid pickup/drop point. Use A-F only.");
                        continue;
                    }
                    if(pickupPoint == dropPoint) {
                        System.out.println("Pickup and drop point cannot be the same.");
                        continue;
                    }
                    System.out.print("Enter Pickup time in hours(ex:12): ");
                    int pickupTime=sc.nextInt();
                    System.out.println(taxiManager.bookTaxi(customerId,pickupPoint,dropPoint,pickupTime));
                    break;
                case 2:
                    taxiManager.printAllTaxiDetails();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Choice Try Again");
            }
        }
    }
}