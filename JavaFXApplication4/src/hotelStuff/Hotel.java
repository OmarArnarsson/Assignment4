
package hotelStuff;

public class Hotel {
    private String name;
    private String type;
    private int rating;
    private String location;
    private int noRooms;
    private int gym;
    private int pool;
    private int breakfast;
    private int pricerange;


    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getRating(){
        return rating;
    }

    public String getLocation(){
        return location;
    }

    public int getNoRooms(){
        return noRooms;
    }

    public int getGym(){
        return gym;
    }

    public int getPool(){
        return pool;
    }

    public int getBreakfast(){
        return breakfast;
    }

    public int getPricerange(){
        return pricerange;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public void setPricerange(int pricerange) {
        this.pricerange = pricerange;
    }
}