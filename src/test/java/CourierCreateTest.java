import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierCreateTest {
    private CourierAPI courierAPI;
    private CourierCreateDeserialization createdCourier;
    private ValidatableResponse responseLogin;


    @Before
    public void getURL() {
        courierAPI = new CourierAPI();
        createdCourier = CourierCreateData.getNewCourier();
    }
    @Test
    @Step("Регистрируем нового курьера и логинимся")
    public void courierCreate(){
        ValidatableResponse createCourier = courierAPI.create(createdCourier);
        int statusCode = createCourier.extract().statusCode();
        boolean statusOk = createCourier.extract().path("ok");
        assertEquals(201, statusCode);
        assertTrue(statusOk);
        if (statusOk){
            System.out.println("code " + statusCode + "\n" + "status " + statusOk);
        } else {
            System.out.println(false);
        }
    }
    @After
    @Step("Удаление созданного курьера")
    public void deleteCourier() {
        responseLogin = courierAPI.login(CourierData.from(createdCourier));
        int courierId = responseLogin.extract().path("id");
        courierAPI.delete(courierId);
    }
}
