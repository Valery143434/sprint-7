import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourierCreateWithoutDataTest {
    private CourierAPI courierAPI;
    @Before
    public void getURL() {
        courierAPI = new CourierAPI();
    }
    @Test

    @Step("Создание курьера без логина")
    public void courierWithoutLogin(){
        CourierCreateDeserialization courier = CourierCreateData.getCourierWithoutLogin();
        ValidatableResponse responseCreate = courierAPI.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        String responseMessage = responseCreate.extract().path("message");
        assertEquals(400, actualStatusCodeCreate);
        assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }
    @Test
    @Step("Создание курьера без пароля")
    public void courierWithoutPassword(){
        CourierCreateDeserialization courier = CourierCreateData.getCourierWithoutPassword();
        ValidatableResponse responseCreate = courierAPI.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        String responseMessage = responseCreate.extract().path("message");
        assertEquals(400, actualStatusCodeCreate);
        assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }
    @Test
    @Step("Создание курьера без имени")
    public void courierWithoutName(){
        CourierCreateDeserialization courier = CourierCreateData.getCourierWithoutName();
        ValidatableResponse responseCreate = courierAPI.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        String responseMessage = responseCreate.extract().path("message");
        assertEquals(400, actualStatusCodeCreate);
        assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }
}
