import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierLoginTest {
    private CourierAPI courierAPI;
    private CourierCreateDeserialization createdCourier;
    private ValidatableResponse responseLogin;


    @Before
    public void setURL() {
        courierAPI = new CourierAPI();
        createdCourier = CourierCreateData.getNewCourier();
    }
    @Test
    @Step("Создание курьера и авторизация")
    public void courierLogin(){
        courierAPI.create(createdCourier);
        responseLogin = courierAPI.login(CourierData.from(createdCourier));
        int statusCode = responseLogin.extract().statusCode();
        int id = responseLogin.extract().path("id");
        assertEquals(200, statusCode);
        assertNotNull(id);
        if (statusCode == 200){
            System.out.println("code " + statusCode + "\n" + "id " + id);
        } else {
            System.out.println(false);
        }
    }
    @After
    @Step("Удаление созданного курьера")
    public void delete() {
        int courierId = responseLogin.extract().path("id");
        courierAPI.delete(courierId);
    }
}
