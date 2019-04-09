package hotelStuff;

import java.sql.*;
import java.util.ArrayList;

public class HotelDAO {
    public static String hotelid ="G";
    public static String hoteltype="H";
    public static int hotelrating = 4;
    public static String loc = "R";
    public static int availableH = 1;
    public static int gym = 1;
    public static int pool = 0;
    public static int breakfast = 1;
    public static int lprice = 1;
    public static int hprice = 5;
    public static int Rbeds = 1;
    public static int Rwifi = 1;
    public static int RLprice = 100;
    public static int RHprice = 300;
    public static int availableR = 1;
    public static int rID;

    public static ArrayList<Hotel> getAllHotels() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:sqlite:db/hotel.db");
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("Select * from hotels");
        ArrayList<Hotel> hotelList = new ArrayList<>();
        while(rs.next()){
            Hotel hot = new Hotel();
            hot.setName(rs.getString("name"));
            hot.setType(rs.getString("type"));
            hot.setRating(rs.getInt("rating"));
            hot.setLocation(rs.getString("location"));
            hot.setNoRooms(rs.getInt("noRooms"));
            hot.setGym(rs.getInt("gym"));
            hot.setPool(rs.getInt("pool"));
            hot.setBreakfast(rs.getInt("breakfast"));
            hot.setPricerange(rs.getInt("pricerange"));
            hotelList.add(hot);
        }
        return hotelList;
    }
    public static ArrayList<Hotel> getFilteredHotels()throws SQLException{
        if(hotelrating>5 || hotelrating < 1)
        {
            hotelrating = 0;
        }
        if(availableH < 1)
        {
            availableH = -1;
        }
        Connection con = DriverManager.getConnection("jdbc:sqlite:db/hotel.db");
        ArrayList<Hotel> filteredHList = new ArrayList<>();
        String sql = "Select * from hotels where name Like ? AND type Like ?  AND rating = ? AND location Like ? AND noRooms >= ? AND gym = ? AND pool = ? AND breakfast = ? AND pricerange >= ? AND pricerange <= ? ";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, hotelid+"%");
            pst.setString(2, hoteltype+"%");
            pst.setInt(3, hotelrating);
            pst.setString(4, loc+"%");
            pst.setInt(5, availableH);
            pst.setInt(6, gym);
            pst.setInt(7, pool);
            pst.setInt(8, breakfast);
            pst.setInt(9, lprice);
            pst.setInt(10,hprice);
            rs = pst.executeQuery();
            while(rs.next()){
                Hotel hot = new Hotel();
                hot.setName(rs.getString("name"));
                hot.setType(rs.getString("type"));
                hot.setRating(rs.getInt("rating"));
                hot.setLocation(rs.getString("location"));
                hot.setNoRooms(rs.getInt("noRooms"));
                hot.setGym(rs.getInt("gym"));
                hot.setPool(rs.getInt("pool"));
                hot.setBreakfast(rs.getInt("breakfast"));
                hot.setPricerange(rs.getInt("pricerange"));
                filteredHList.add(hot);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return filteredHList;
    }

    public static ArrayList<Room> getFilteredRooms() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:sqlite:db/hotel.db");
        ArrayList<Room> filteredRList = new ArrayList<>();
        String sql = "Select * from rooms where hotelId like (Select name from hotels where name LIKE ? AND type Like ?  AND rating = ? AND location Like ? AND noRooms >= ? AND gym = ? AND pool = ? AND breakfast = ? AND pricerange >= ? AND pricerange <= ?) AND beds = ? AND wifi = ? AND price >= ? AND price <= ? AND availableR = ? ";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, hotelid+"%");
            pst.setString(2, hoteltype+"%");
            pst.setInt(3, hotelrating);
            pst.setString(4, loc+"%");
            pst.setInt(5, availableH);
            pst.setInt(6, gym);
            pst.setInt(7, pool);
            pst.setInt(8, breakfast);
            pst.setInt(9, lprice);
            pst.setInt(10,hprice);
            pst.setInt(11, Rbeds);
            pst.setInt(12, Rwifi);
            pst.setInt(13, RLprice);
            pst.setInt(14, RHprice);
            pst.setInt(15, availableR);
            rs = pst.executeQuery();
            while (rs.next()){
                Room ro = new Room();
                ro.setHotelId(rs.getString("hotelId"));
                ro.setBeds(rs.getInt("beds"));
                ro.setWifi(rs.getInt("wifi"));
                ro.setPrice(rs.getInt("price"));
                ro.setAvailableR(rs.getInt("availableR"));
                ro.setRoomId(rs.getInt("roomId"));
                filteredRList.add(ro);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return filteredRList;
    }

    public static void bokaHandler(int rId, String hId) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:sqlite:db/hotel.db");
        String sql = "UPDATE rooms SET availableR = 0 WHERE roomId = ? AND hotelId = ?";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, rId);
            pst.setString(2, hId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        minnkaHotel(hId);
    }

    public static void afbokaHandler(int rId, String hId) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:sqlite:db/hotel.db");
        String sql = "UPDATE rooms SET availableR = 1 WHERE roomId = ? AND hotelId = ?";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, rId);
            pst.setString(2, hId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void minnkaHotel(String hId) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:sqlite:db/hotel.db");
        String sql = "UPDATE hotels SET noRooms = noRooms - 1 WHERE name = ?";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, hId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
