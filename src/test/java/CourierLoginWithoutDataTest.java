import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierLoginWithoutDataTest {
    private CourierAPI courierAPI;
    private CourierCreateDeserialization courier;
    private ValidatableResponse login;

    @Before
    public void getURL() {
        courierAPI = new CourierAPI();
        courier = CourierCreateData.getNewCourier();
    }
    @Test
    @Step("Авторизация без логина")
    public void courierWithoutLogin(){
        courierAPI.create(courier);
        login = courierAPI.login(CourierData.from(courier));
        ValidatableResponse responseLogin = courierAPI.login(new CourierData(null, courier.getPassword()));
        int statusCode = responseLogin.extract().statusCode();
        String message = responseLogin.extract().path("message");
        assertEquals(400, statusCode);
        assertEquals("Недостаточно данных для входа",message);
    }
    @Test(timeout=10000)
    @Step("Авторизация без пароля")
    public void courierWithoutPassword(){
        courierAPI.create(courier);
        login = courierAPI.login(CourierData.from(courier));
        ValidatableResponse responseLogin = courierAPI.login(new CourierData(courier.getLogin(), null));
        int statusCode = responseLogin.extract().statusCode();
        String message = responseLogin.extract().path("message");
        assertEquals(400, statusCode);
        assertEquals("Недостаточно данных для входа",message);

    }
    @Test
    @Step("Авторизация с нереальным логином и паролем")
    public void courierUnrealLoginAndPassword(){
        courierAPI.create(courier);
        login = courierAPI.login(CourierData.from(courier));
        ValidatableResponse responseLogin = courierAPI.login(new CourierData(courier.getLogin()+"И", courier.getPassword()+"3"));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        String messageLogin = responseLogin.extract().path("message");
        assertEquals(404, actualStatusCodeLogin);
        assertEquals("Учетная запись не найдена",messageLogin);
    }
    @Test
    @Step("Авторизация с нереальным логином")
    public void courierUnrealLogin(){
        courierAPI.create(courier);
        login = courierAPI.login(CourierData.from(courier));
        ValidatableResponse responseLogin = courierAPI.login(new CourierData(courier.getLogin()+"И", courier.getPassword()));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        String messageLogin = responseLogin.extract().path("message");
        assertEquals(404, actualStatusCodeLogin);
        assertEquals("Учетная запись не найдена",messageLogin);
    }
    @Test
    @Step("Авторизация с нереальным паролем")
    public void courierUnrealPassword(){
        courierAPI.create(courier);
        login = courierAPI.login(CourierData.from(courier));
        ValidatableResponse responseLogin = courierAPI.login(new CourierData(courier.getLogin(), courier.getPassword()+"3"));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        String messageLogin = responseLogin.extract().path("message");
        assertEquals(404, actualStatusCodeLogin);
        assertEquals("Учетная запись не найдена",messageLogin);
    }

    @After
    @Step("Удаление курьера")
    public void delete() {
        int id = login.extract().path("id");
        courierAPI.delete(id);
    }
}
