import Models.Booking;
import Models.Taxi;

import java.util.*;

public class TaxiManager {
    private final int taxiLimit=6;
    private Map<Character, PriorityQueue<Taxi>>taxiLocation;
    private List<Taxi> allTaxis = new ArrayList<>();



    //initializing taxi with limit
    public TaxiManager(int taxiLimit){
        taxiLocation=new HashMap<>();
        for(char c='A';c<='F';c++){
            taxiLocation.put(c,new PriorityQueue<>((a,b)->{
               if(a.getEarnings()!=b.getEarnings()){
                   return Integer.compare(a.getEarnings(),b.getEarnings());
               }
               return Integer.compare(a.getId(),b.getId());
            }));
        }

        for(int i=1;i<=taxiLimit;i++){
            Taxi t = new Taxi(i);
            taxiLocation.get('A').add(t);
            allTaxis.add(t);
        }
    }
    public String bookTaxi(int cutomerId,char pickupPoint,char dropPoint,int pickupTime) {
        Taxi bestTaxi = null;
        for (int d = 0; d <= 5; d++) {
            for (char loc : List.of((char) (pickupPoint+d), (char) (pickupPoint-d ))) {
                if (loc < 'A' || loc > 'F') continue;
                PriorityQueue<Taxi> pq = taxiLocation.get(loc);
                List<Taxi> tempList = new ArrayList<>();
                Taxi foundTaxi = null;
                while (!pq.isEmpty()) {
                    Taxi taxi = pq.poll();

                    if (taxi.isFree(pickupTime)) {
                        foundTaxi = taxi;
                        break;
                    } else {
                        tempList.add(taxi);
                    }
                }
                pq.addAll(tempList);
                if (foundTaxi != null) {
                    if (bestTaxi == null ||
                            foundTaxi.getEarnings() < bestTaxi.getEarnings() ||
                            (foundTaxi.getEarnings() == bestTaxi.getEarnings() &&
                                    foundTaxi.getId() < bestTaxi.getId())) {
                        bestTaxi = foundTaxi;
                    }
                }
            }
            if(bestTaxi!=null){
                //booking logic and break
                Booking booking=new Booking(cutomerId,pickupPoint,dropPoint,pickupTime);
                bestTaxi.addBooking(booking);
                taxiLocation.get(dropPoint).add(bestTaxi);
                return "Booking Successfull"+"and TAXI ID: "+bestTaxi.getId();
            }
        }
        return "Booking Failed No Taxi Available";
    }
    public void printAllTaxiDetails() {
        allTaxis.sort(Comparator.comparingInt(Taxi::getId));
        for (Taxi t : allTaxis) {
            t.printDetails();
        }
    }



}
