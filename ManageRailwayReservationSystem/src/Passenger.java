
import java.util.ArrayList;
import java.util.Random;

public class Passenger {

    private String name;
    private String address;
    private long phoneNumber;
    private static ArrayList<Reservation> reservationsOfPassenger;
    private String password;
    private int accountId = 1000;
    private static int count = 0;

    public Passenger(String name, String address, long phoneNumber, String password) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountId = this.accountId + count;
        count++;
        reservationsOfPassenger = new ArrayList<Reservation>();
        if (checkPassword(password)) {
            this.password = password;
        } else {
            this.password = "638149";
        }
    }

    private boolean checkPassword(String pwd) {
        if (pwd.length() != 6 || pwd.charAt(0) == '0') {
            return false;
        }
        for (int i = 0; i < pwd.length() - 1; i++) {
            for (int j = 1; j < pwd.length(); j++) {
                if (pwd.charAt(i) == pwd.charAt(j)) {
                    return false;
                }
            }
        }
        int numOfDigits = 0;
        for (int i = 0; i < pwd.length(); i++) {
            if (Character.isDigit(pwd.charAt(i))) {
                numOfDigits++;
            }
        }
        return numOfDigits == 6;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountId() {
        return accountId;
    }

    public boolean Login(String pwd) {
        if (this.password.equals(pwd)) {
            return true;
        } else {
            return false;
        }
    }

    public Reservation findReservation(int reservationCodee) {
        for (int i = 0; i < reservationsOfPassenger.size(); i++) {
            if (reservationCodee == reservationsOfPassenger.get(i).getReservationId()) {
                return reservationsOfPassenger.get(i);
            }
        }
        return null;
    }

    public int newReservation(RailTrip mytrip, int numberOfReservedSeats, int typeOfSeats) {
        Reservation rsrv = new Reservation(mytrip, numberOfReservedSeats, typeOfSeats);
        rsrv.approveReservation();
        if (rsrv.approveReservation()) {
            reservationsOfPassenger.add(rsrv);
            return rsrv.getReservationId();
        } else {
            System.out.println("The new reservation cannot be made!");
            return -1;
        }
    }

    public boolean cancelReservation(int reservationCodee) {
        findReservation(reservationCodee);
        if (this.findReservation(reservationCodee) != null) {
            reservationsOfPassenger.remove(this);
            return true;
        } else {
            return false;
        }
    }

    public void printListOfReservation() {
        for (int i = 0; i < reservationsOfPassenger.size(); i++) {
            Reservation rsrvt = reservationsOfPassenger.get(i);
            System.out.println("Trip is: " + rsrvt.getMytrip() + ", Reservation date: " + rsrvt.getDateOfReservation() + ", Reserved Seats: "
                    + rsrvt.getNumberOfReservedSeats() + "Seats' type: " + rsrvt.getTypeOfSeats() + "Reservation Code: " + rsrvt.getReservationId()
                    + "Approved or not approved?: " + rsrvt.isApproved());
        }
    }

}