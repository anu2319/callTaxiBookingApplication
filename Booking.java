import java.util.*;

public class Booking {

    public static void bookTaxi(int customerId, char PickupPoint, char DropPoint,  int PickupTime, ArrayList<Taxi>freeTaxis) {
        int minValue = Integer.MIN_VALUE;
        int distanceBetweenPickupAndDropPoint = 0;
        int earning = 0;
        Taxi booked = new Taxi();
        char nextPoint = 'Z';
        int currentTime = 0;
        String taxiDetails = "";


        for(Taxi t : freeTaxis)
        {
            int distanceBetweenPointAndTaxi = Math.abs((t.initialPoint - '0') - (PickupPoint - '0')) * 15;
            if(distanceBetweenPointAndTaxi < minValue)
            {
                booked = t;
                distanceBetweenPickupAndDropPoint = Math.abs((DropPoint - '0') - (PickupPoint - '0'))*15;
                earning = (distanceBetweenPickupAndDropPoint-5)*10 + 100;
                int dropTime = PickupTime + distanceBetweenPickupAndDropPoint/15;
                currentTime = dropTime;
                nextPoint = DropPoint;
                taxiDetails = customerId+"   "+customerId+" "+PickupPoint+"  "+DropPoint+"   "+PickupTime+"  "+  dropTime+"  "+earning;
                minValue = distanceBetweenPointAndTaxi;
            }
        }
        int earn = booked.earnings;
        booked.setDetails(nextPoint,currentTime,earn+earning,taxiDetails);
        System.out.println("Taxi"+" " +booked.taxiId+" "+"booked");

    }


    public static  ArrayList<Taxi> generateTaxi(int n)
    {
        ArrayList<Taxi> taxis = new ArrayList<>();
        for(int i=1; i<=n; i++)
        {
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static ArrayList<Taxi> freeTaxi(ArrayList<Taxi> taxis, char PickupPoint, int PickupTime)
    {
        ArrayList<Taxi> freeTaxis = new ArrayList<>();
        for(Taxi t : taxis)
        {
            int distanceBetweenInitialAndPickupPoint = (t.initialPoint - '0') - (PickupPoint - '0');
            int distanceBetweenFreeAndPickupTime =  PickupTime - t.freeTime ;
            if(t.freeTime <= PickupTime && (Math.abs((distanceBetweenInitialAndPickupPoint)) <= distanceBetweenFreeAndPickupTime))
            {
                freeTaxis.add(t);
            }
        }
        return freeTaxis;
    }


    public static void main(String[] args) throws NullPointerException
    {

        ArrayList<Taxi> taxis = generateTaxi(10);
        Scanner sc = new Scanner(System.in);
        int id = 1;
        int customerId;
        while(true)
        {
            System.out.println("1. Book Taxi");
            System.out.println("2. Print TaxiDetails");
            int choice = sc.nextInt();
            switch (choice)
            {
                case 1 : {
                    customerId = id;
                    int bookingId = id;
                    System.out.println("Enter Pickup Point : ");
                    char PickupPoint = sc.next().charAt(0);
                    System.out.println("Enter Drop Point : ");
                    char DropPoint = sc.next().charAt(0);
                    System.out.println("Enter Pickup Time : ");
                    int PickupTime = sc.nextInt();

                    ArrayList<Taxi> freeTaxis = freeTaxi(taxis, PickupPoint, PickupTime);
                    if (freeTaxis.size() == 0) {
                        System.out.println("All taxis are busy");
                        return;
                    }
                    Collections.sort(freeTaxis, (a, b) -> a.earnings - b.earnings);
                    bookTaxi(id, PickupPoint, DropPoint, PickupTime, freeTaxis);
                    id++;
                    break;
                }
                case 2:
                {
                    for(Taxi t : taxis)
                    {
                        t.printDetails();
                    }
                    break;
                }
                default:{
                    return;
                }

            }
        }
    }
}
