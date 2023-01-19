import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierAPI extends URL {
    private static final String CREATE_PATH = "/api/v1/courier";
    private static final String LOGIN_PATH = "/api/v1/courier/login";
    private static final String DELETE_PATH = "/api/v1/courier/";

    public ValidatableResponse create(CourierCreateDeserialization createdCourier){
        return given()
                .header("Content-type", "application/json")
                .spec(getURL())
                .body(createdCourier)
                .when()
                .post(CREATE_PATH)
                .then();
    }
    public ValidatableResponse login(CourierData courierData){
        return given()
                .spec(getURL())
                .body(courierData)
                .when()
                .post(LOGIN_PATH)
                .then();
    }
    public ValidatableResponse delete(int courierId){
        Gson deleteGson = new GsonBuilder().setPrettyPrinting().create();
        CourierDeleteDeserialization courierDeleteDeserialization = new CourierDeleteDeserialization(String.valueOf(courierId));
        String deleteJson = deleteGson.toJson(courierDeleteDeserialization);
        return given()
                .spec(getURL())
                .body(deleteJson)
                .when()
                .delete(DELETE_PATH+String.valueOf(courierId))
                .then();
    }
}
