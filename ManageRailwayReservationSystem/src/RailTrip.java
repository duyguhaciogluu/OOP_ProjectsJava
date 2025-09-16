
import java.util.Date;


public class RailTrip {

    private String fromLocation;
    private String toLocation;
    private Date tripDate;
    private int avfirstClassNumber;
    private int avregularSeats;
    private int railTripId;
    private static int count = 0;

    public RailTrip(String fromLocation, String toLocation) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.avfirstClassNumber = 30;
        this.avregularSeats = 70;
        this.railTripId = this.railTripId + count;
        count++;
        this.tripDate = new Date();
    }

    public RailTrip(String fromLocation, String toLocation, int avfirstClassNumber, int avregularSeats) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.avfirstClassNumber = avfirstClassNumber;
        this.avregularSeats = avregularSeats;
        this.tripDate = new Date();
        this.railTripId = this.railTripId + count;
        count++;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public int getAvfirstClassNumber() {
        return avfirstClassNumber;
    }

    public int getAvregularSeats() {
        return avregularSeats;
    }

    public int getRailTripId() {
        return railTripId;
    }

    public boolean reserveSeats(int type, int seatsNumbers) {
        if (type == 0) {
            while (seatsNumbers > 0 && seatsNumbers <= avregularSeats) {
                avregularSeats--;
                return true;
            }
        } else if (type == 1) {
            while (seatsNumbers > 0 && seatsNumbers <= avfirstClassNumber) {
                avfirstClassNumber--;
                return true;
            }
        }
        return false;
    }

    public boolean releaseSeats(int seatsNumbers, int type) {
        if (type == 0) {
            while (seatsNumbers > 0) {
                avregularSeats++;
                return true;
            }
        } else if (type == 1) {
            while (seatsNumbers > 0) {
                avfirstClassNumber++;
                return true;
            }
        }
        return false;
    }

    public String getRailTripInfo() {
        return "fromLocation: " + fromLocation + ", toLocation: " + toLocation + ", Trip Date: " + tripDate
                + ", avfirstClassNumber: " + avfirstClassNumber + ", avregularSeats:" + avregularSeats
                + ", railTripId: " + railTripId;
    }
}