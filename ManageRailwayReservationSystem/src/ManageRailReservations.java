
import java.util.ArrayList;
import java.util.Scanner;


public class ManageRailReservations {

    private static ArrayList<Passenger> registeredPassengers;
    private static ArrayList<RailTrip> railTrips;
    private static final Scanner scn = new Scanner(System.in);
    private static Passenger currentPassenger = null;
    private static RailTrip currentRailTrip = null;

    public static void menu() {
        int choice, avfirstClassNumber, avregularSeats, accountId, railTripId;
        long phoneNumber;
        String name, address, password, fromLocation, toLocation;
        boolean quit = false;

        while (!quit) {
            System.out.println("1. Add a new passenger:");
            System.out.println("2. Add a new rail trip:");
            System.out.println("3. Show all passengers:");
            System.out.println("4. Show all trips:");
            System.out.println("5. Add a reservation for an existing passenger:");
            System.out.println("6. Display a reservation:");
            System.out.println("7. Cancel a reservation:");
            System.out.println("0. Quit: ");
            System.out.print("Choice: ");
            choice = scn.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the passenger name:");
                    name = scn.next();
                    System.out.println("Enter the adress:");
                    address = scn.next();
                    System.out.println("Enter the phone number:");
                    phoneNumber = scn.nextLong();
                    System.out.println("Enter the password:");
                    password = scn.next();
                    addNewPassenger(name, address, phoneNumber, password);
                    break;
                case 2:
                    System.out.println("Enter which location you will start:");
                    fromLocation = scn.next();
                    System.out.println("Enter which location you will stop:");
                    toLocation = scn.next();
                    System.out.println("Do you want to creat trip with available class numbers?");
                    boolean answer = scn.nextBoolean();
                    if (answer) {
                        System.out.println("Enter available first class number: ");
                        avfirstClassNumber = scn.nextInt();
                        System.out.println("Enter available regular class number: ");
                        avregularSeats = scn.nextInt();
                        addNewRailTrip(fromLocation, toLocation, avfirstClassNumber, avregularSeats);
                    } else {
                        addANewRailTrip(fromLocation, toLocation);
                    }
                    break;
                case 3:
                    printAllPassengers();
                    break;
                case 4:
                    printAllTrips();
                    break;
                case 5:
                    System.out.println("Enter the password:");
                    password = scn.next();
                    System.out.println("Enter the account id:");
                    accountId = scn.nextInt();
                    addNewReservationtoPassenger(password, accountId);
                    break;
                case 6:
                    System.out.println("Enter the account id:");
                    accountId = scn.nextInt();
                    displayAReservation(accountId);
                    break;
                case 7:
                    System.out.println("Enter the password:");
                    password = scn.next();
                    System.out.println("Enter the account id:");
                    accountId = scn.nextInt();
                    cancelAReservation(password, accountId);
                    break;
                case 0:
                    quit = true;
                    Quit();
                    break;
                default:
                    System.out.println("Your choice is not valid, try again!");
            }
        }
    }

    public static void main(String[] args) {
        registeredPassengers = new ArrayList<>();
        railTrips = new ArrayList<>();
        menu();
    }

    public static RailTrip findRailTripByld(int railTripId) {
        RailTrip returnStr = null;
        for (int i = 0; i < railTrips.size(); i++) {
            RailTrip rtp = railTrips.get(i);
            if (rtp != null) {
                if (railTripId == rtp.getRailTripId()) {
                    returnStr = rtp;
                }
            }
        }
        return returnStr;
    }

    public static Passenger findPassengerByld(int accountId) {
        Passenger returnStr = null;
        for (int i = 0; i < registeredPassengers.size(); i++) {
            Passenger pssngr = registeredPassengers.get(i);
            if (pssngr != null) {
                if (accountId == pssngr.getAccountId()) {
                    returnStr = pssngr;
                }
            }
        }
        return returnStr;
    }

    public static void addNewPassenger(String name, String address, long phoneNumber, String password) {
        currentPassenger = new Passenger(name, address, phoneNumber, password);
        registeredPassengers.add(currentPassenger);
        System.out.println("This passenger is added! ");
    }

    public static void addNewRailTrip(String fromLocation, String toLocation, int avfirstClassNumber, int avregularSeats) {
        currentRailTrip = new RailTrip(fromLocation, toLocation, avfirstClassNumber, avregularSeats);
        railTrips.add(currentRailTrip);
        System.out.println("This rail trip is added! ");
    }

    public static void addANewRailTrip(String fromLocation, String toLocation) {
        currentRailTrip = new RailTrip(fromLocation, toLocation);
        railTrips.add(currentRailTrip);
        System.out.println("This rail trip is added! ");
    }

    public static void printAllPassengers() {
        for (int i = 0; i < registeredPassengers.size(); i++) {
            Passenger ps = registeredPassengers.get(i);
            System.out.println("Name: " + ps.getName() + ", Adress:" + ps.getAddress() + ", Account Id:" + ps.getAccountId()
                    + ", Phone Number: " + ps.getPhoneNumber() + ", password:" + ps.getPassword());
        }
    }

    public static void printAllTrips() {
        for (int i = 0; i < railTrips.size(); i++) {
            RailTrip rtrp = railTrips.get(i);
            System.out.println(rtrp.getRailTripInfo());
        }
    }

    public static void addNewReservationtoPassenger(String password, int accountId) {
        Passenger ps = findPassengerByld(accountId);
        if (ps != null) {
            if (ps.Login(password)) {
                for (int i = 0; i < railTrips.size(); i++) {
                    RailTrip rtrp = railTrips.get(i);
                    System.out.println("Which rail trip do you want to reserve?");
                    int railTripId = scn.nextInt();
                    System.out.println("Which type of seat do you want to reserve?");
                    int type = scn.nextInt();
                    System.out.println("How many seats do you want to reserve?");
                    int seats = scn.nextInt();
                    if (i == railTripId) {
                        ps.newReservation(rtrp, type, seats);
                    } else {
                        System.out.println("The related rail trip is not found! ");
                    }
                }
            }
        }
    }

    public static void displayAReservation(int accountId) {
        Passenger ps = findPassengerByld(accountId);
        if (ps != null) {
            ps.printListOfReservation();
        }
    }

    public static void cancelAReservation(String password, int accountId) {
        Passenger ps = findPassengerByld(accountId);
        if (ps != null) {
            if (ps.Login(password)) {
                System.out.println("Please, cancel the reservation code!");
                System.out.println("enter the reservation code: ");
                int reservationCodee = scn.nextInt();
                ps.cancelReservation(reservationCodee);
                System.out.println("Your reservation is cancelled! ");
            }
        }
    }

    public static void Quit() {
        System.out.println("Your operations were done! You logged out! ");
    }
}