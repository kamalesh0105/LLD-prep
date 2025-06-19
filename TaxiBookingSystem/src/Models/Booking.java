package Models;

import java.util.Map;

public class Booking {
    private static int bookingIdCounter = 1;
    private long bookingId;
    private long customerId;
    private char pickupPoint;
    private char dropPoint;
    private int pickupTime;
    private int dropTime;
    private int amount;


    public Booking(long customerId, char pickupPoint, char dropPoint, int pickupTime) {
        this.bookingId=bookingIdCounter++;
        this.customerId = customerId;
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.pickupTime = pickupTime;
        this.dropTime = pickupTime+ Math.abs(dropPoint-pickupPoint);
        int distance=Math.abs(dropPoint-pickupPoint)*15;
        this.amount = 100+Math.max(0,distance-5)*10;
    }

    public int getDropTime() {
        return dropTime;
    }

    public char getDropPoint() {
        return dropPoint;
    }

    public void setDropTime(int dropTime) {
        this.dropTime = dropTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return bookingId + "\t" + customerId + "\t" + pickupPoint + "\t" +
                dropPoint + "\t" + pickupTime + "\t" + dropTime + "\t" + amount;
    }
}
