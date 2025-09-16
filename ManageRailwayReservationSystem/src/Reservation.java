
import java.util.Date;
import java.util.Random;


public class Reservation {

    private RailTrip mytrip;
    private Date dateOfReservation;
    private int numberOfReservedSeats;
    private int typeOfSeats;//1 - First class, 0 - regular
    private int reservationCode;
    private static int reservationCount = new Random().nextInt(10000);
    private boolean approved = false;

    //typeOfSeats = 1 --> first class
    //typeOfSeats = 0 --> regular class
    public Reservation(RailTrip mytrip, int numberOfReservedSeats, int typeOfSeats) {
        this.mytrip = mytrip;
        this.dateOfReservation = new Date();
        this.numberOfReservedSeats = numberOfReservedSeats;
        this.typeOfSeats = typeOfSeats;
        this.reservationCode = reservationCount;
        reservationCode++;
    }

    public boolean approveReservation() {
        if (mytrip.reserveSeats(typeOfSeats, numberOfReservedSeats)) {
            approved = true;
        }

        return approved;
    }

    public boolean cancelReservation() {
        if (mytrip.releaseSeats(typeOfSeats, numberOfReservedSeats)) {
            approved = false;
            return true;
        } else {
            return false;
        }
    }

    public String getReservationInfo() {
        return "Reservation Info: \n"
                + "trip id:" + mytrip.getRailTripId()
                + "/From " + mytrip.getFromLocation()
                + "/To " + mytrip.getToLocation()
                + "/Trip date:" + mytrip.getTripDate()
                + "\nReservation Id:" + reservationCode
                + //"/Date:" + dateOfReservation.toString() +
                "/Seat Type:" + (typeOfSeats == 1 ? "First Class" : "Regular")
                + "/Number Of Seats:" + numberOfReservedSeats
                + "/Approved:" + approved;
    }

    public String getMytrip() {
        return mytrip.toString();
    }

    public String getDateOfReservation() {
        return dateOfReservation.toString();
    }

    public int getNumberOfReservedSeats() {
        return numberOfReservedSeats;
    }

    public int getTypeOfSeats() {
        return typeOfSeats;
    }

    public int getReservationId() {
        return reservationCode;
    }

    public boolean isApproved() {
        return approved;
    }
}