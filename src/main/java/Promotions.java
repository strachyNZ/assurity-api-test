//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties(ignoreUnknown = true)

public class Promotions {

    public int Id;
    public String Name;
    public String Description;
    public double Price;
    public String OriginalPrice;
    public Boolean Recommended;
    public int MinimumPhotoCount;

    // getters
    public int getId() { return Id; }
    public String getName() { return Name; }
    public String getDescription() { return Description; }
    public double getPrice() { return Price; }
    public String getOriginalPrice() { return OriginalPrice; }
    public Boolean getRecommended() { return Recommended; }
    public int getMinimumPhotoCount() { return MinimumPhotoCount; }

}
