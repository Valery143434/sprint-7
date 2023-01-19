import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderAPI extends URL {
    private static final String CREATE_PATH = "/api/v1/orders";
    private static final String CANCEL_PATH = "/api/v1/orders/cancel";

    public ValidatableResponse create(OrderData order){
        return given()
                .spec(getURL())
                .body(order)
                .when()
                .post(CREATE_PATH)
                .then();
    }
    public ValidatableResponse delete(int track) {
        Gson cancelGson = new GsonBuilder().setPrettyPrinting().create();
        OrderTrackNumber orderTrackNumber = new OrderTrackNumber(track);
        String cancelJson = cancelGson.toJson(orderTrackNumber);
        return given()
                .spec(getURL())
                .body(cancelJson)
                .when()
                .post(CANCEL_PATH)
                .then();
    }
    public ValidatableResponse get() {
        return given()
                .spec(getURL())
                .when()
                .get(CREATE_PATH)
                .then();
    }
}
