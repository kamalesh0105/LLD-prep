package Models;

import Models.Booking;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private int id;
    private int freeTime=6;
    private char currentLocation='A';
    private int earnings=0;
    private List<Booking>bookings=new ArrayList<>();

    public Taxi(int id){
        this.id=id;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
        freeTime=booking.getDropTime();
        currentLocation=booking.getDropPoint();
        earnings+=booking.getAmount();
    }
    public boolean isFree(int pickupTime){
        return freeTime<=pickupTime;
    }
    public void printDetails() {
        System.out.println("Taxi-" + id + " Total Earnings: Rs. " + earnings);
        System.out.println("BookingID\tCustomerID\tFrom\tTo\tPickupTime\tDropTime\tAmount");
        for (Booking b : bookings) {
            System.out.println(b.toString());
        }
        System.out.println("----------------------------------------------------------");
    }
    @Override
    public String toString() {
        return "Models.Taxi{" +
                "id=" + id +
                ", freeTime=" + freeTime +
                ", currentLocation=" + currentLocation +
                ", earnings=" + earnings +
                ", bookings=" + bookings +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public char getCurrentLocation() {
        return currentLocation;
    }

    public int getEarnings() {
        return earnings;
    }
}
