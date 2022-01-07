import java.util.*;

public class Taxi
{
    static int taxicount = 0;
    int taxiId;
    char initialPoint;
    int pickUpTime, dropTime, earnings, freeTime;
    ArrayList<String> taxi;

    public Taxi()
    {
        initialPoint = 'A';
        freeTime = 6;
        earnings = 0;
        taxi = new ArrayList<>();
        taxiId = taxiId + 1;


    }

    public void setDetails(char initialPoint, int freeTime, int earnings, String taxiDetails)
    {
        this.initialPoint = initialPoint;
        this.freeTime = freeTime;
        this.earnings = earnings;
        this.taxi.add(taxiDetails);
    }

    public void printDetails()
    {
        System.out.println("Taxi No : " + this.taxiId + "Total Earnings : Rs. " + this.earnings);
        System.out.println("BookingId       CustomerId      From        To      PickupTime      DropTime        Amount");
        for(String Taxi : taxi)
        {
            System.out.println(taxiId+"       " +taxi);
        }
    }
}
