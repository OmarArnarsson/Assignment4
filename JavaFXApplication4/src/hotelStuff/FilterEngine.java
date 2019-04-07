package hotelStuff;

import java.sql.*;
import java.util.ArrayList;


public class FilterEngine {
    private ArrayList<Hotel> namefilteredlist;
    private ArrayList<Hotel> typefilteredlist;
    private ArrayList<Hotel> locfilteredlist;
    private ArrayList<Hotel> ratfilteredlist;
    private ArrayList<Hotel> roomfilteredlist;
    private ArrayList<Hotel> allHotels;
    private HotelDAO hdao;


    public ArrayList<Hotel> findHotelName(String name) throws SQLException {
        allHotels = hdao.getAllHotels();
        namefilteredlist = new ArrayList<>();
        for (Hotel hotel : allHotels) {
            if (hotel.getName().equals(name)) {
                namefilteredlist.add(hotel);
            }
        }
        return namefilteredlist;
    }

    public ArrayList<Hotel> findHotelType(String type) throws SQLException {
        allHotels = hdao.getAllHotels();
        typefilteredlist = new ArrayList<>();
        for (Hotel hotel : allHotels) {
            if (hotel.getType().equals(type)) {
                typefilteredlist.add(hotel);
            }
        }
        return typefilteredlist;
    }

    public ArrayList<Hotel> findHotelLoc(String location) throws SQLException {
        allHotels = hdao.getAllHotels();
        locfilteredlist = new ArrayList<>();
        for (Hotel hotel : allHotels) {
            if (hotel.getLocation() == location) {
                locfilteredlist.add(hotel);
            }
        }
        return locfilteredlist;
    }

    public ArrayList<Hotel> findHotelRat(int rating) throws SQLException {
        allHotels = hdao.getAllHotels();
        ratfilteredlist = new ArrayList<>();
        for (Hotel hotel : allHotels) {
            if (hotel.getRating() == rating) {
                ratfilteredlist.add(hotel);
            }
        }
        return ratfilteredlist;
    }

    public ArrayList<Hotel> findHotelNoR(int rooms) throws SQLException {
        allHotels = hdao.getAllHotels();
        roomfilteredlist = new ArrayList<>();
        for (Hotel hotel : allHotels) {
            if (hotel.getNoRooms() == rooms) {
                roomfilteredlist.add(hotel);
            }
        }
        return roomfilteredlist;
    }
}