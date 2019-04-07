package modelflight;

import java.util.Calendar;

public class Flight {

    private String depLocation;
    private Calendar depDate;
    private String arrLocation;
    private Calendar arrDate;
    private String aircraftType;
    private Boolean[][] businessSeat;
    private Boolean[][] economySeat;
    private Double economyPrice;
    private Double buisnessPrice;
    private String flightNumber;
    private int seatsLeft;

    Flight(String flightNumber, String depLocation, String arrLocation, long depDate, long arrDate,
           String aircraftType, double economyPrice, double buisnessPrice) {

        this.flightNumber = flightNumber;
        this.depLocation = depLocation;
        this.arrLocation = arrLocation;
        this.depDate = Calendar.getInstance();
        this.depDate.setTimeInMillis(depDate);
        this.arrDate = Calendar.getInstance();
        this.arrDate.setTimeInMillis(arrDate);
        this.aircraftType = aircraftType;
        this.economyPrice = economyPrice;
        this.buisnessPrice = buisnessPrice;
    }
    
    public int getSeatsLeft(Boolean economy) {
        return 180;
        /*if (economy) {
            updateSeatsLeft(economySeat);
        } else {
            updateSeatsLeft(businessSeat);
        }
        return seatsLeft;*/
    }

    private void updateSeatsLeft(Boolean[][] seats) {
        for(int i = 0; i < seats.length; i++) {
            for(int j = 0; j < seats[i].length; j++)
            {
                if(!seats[i][j])
                    seatsLeft++;
            }
        }
    }

    public String getDepLocation() {
        return depLocation;
    }

    public Calendar getDepDate() {
        return depDate;
    }

    public String getArrLocation() {
        return arrLocation;
    }

    public Calendar getArrDate() {
        return arrDate;
    }

    public Double getPrice(Boolean economy) {
        if (economy) {
            return economyPrice;
        } else {
            return buisnessPrice;
        }
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public String toString() {
        return "Frá " + getDepLocation() + " til " + getArrLocation();
    }
}
