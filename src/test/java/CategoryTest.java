import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CategoryTest {
    Response response;
    RequestSpecification httpRequest;
    JsonPath jsonPathEvaluator;
    private String categoryName;
    private Boolean canRelist;

    @BeforeSuite
    public void Setup(){
        RestAssured.baseURI = "https://api.tmsandbox.co.nz/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/Categories/6327/Details.json?catalogue=false");
        jsonPathEvaluator = response.jsonPath();

        // TODO
        // create Category object to store JSON incl promotions
        // create config file  to store baseURI etc
        // move checkDescription method

    }

    @Test
    public void ConfirmCategoryNameEqualsCarbonCredits() {
        // get json element "Name"
        categoryName = jsonPathEvaluator.get("Name");
        // confirm name is "Carbon credits"
        Assert.assertEquals(categoryName, "Carbon credits", "Incorrect name received in the Response");
        System.out.println("Category name is =>  " + categoryName);
    }

    @Test
    public void ConfirmCanRelist(){
        // get json element "CanRelist" value and store as boolean
        canRelist = jsonPathEvaluator.get("CanRelist");
        // confirm "CanRelist" is true
        Assert.assertTrue(canRelist, "Expected to be able to relist");
        System.out.println("Item can be relisted =>  " + canRelist);
    }

    @Test
    public void ConfirmCategoryPromotionGalleryDescription(){
        // convert json array "Promotions" to java array
        Promotions[] promotions = response.jsonPath().getObject("Promotions", Promotions[].class);
        // confirm promotion called "Gallery" has a description which contains "2x larger image"
        Assert.assertTrue(checkDescription(promotions, "Gallery").contains("2x larger image"), "Incorrect description returned");
    }


    // returns description of promotion [array] based on name
    public String checkDescription(Promotions[] promotions, String name){
        for (Promotions p : promotions){
            if (p.getName().equals(name)){
                return p.getDescription();
                  }
        }
        return "";
    }


}
