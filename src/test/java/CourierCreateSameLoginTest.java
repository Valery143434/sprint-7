import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierCreateSameLoginTest {
    private CourierAPI courierAPI;
    private CourierCreateDeserialization createdCourier;
    private CourierCreateDeserialization createdCourierSameLogin;
    private ValidatableResponse responseLogin;

    @Before
    public void getURL() {
        courierAPI = new CourierAPI();
        createdCourier = CourierCreateData.getNewCourier();
        createdCourierSameLogin = CourierCreateData.getNewCourierSameLogin();
    }
    @Test
    @Step("Регистрируем нового курьера и логинимся")
    public void courierCreateSameLoginTest(){
        ValidatableResponse createCourier = courierAPI.create(createdCourier);
        int statusCode = createCourier.extract().statusCode();
        boolean statusOk = createCourier.extract().path("ok");
        assertEquals(201, statusCode);
        assertTrue(statusOk);

        ValidatableResponse createCourierAgain = courierAPI.create(createdCourierSameLogin);
        int statusBadCode = createCourierAgain.extract().statusCode();
        String message = createCourierAgain.extract().path("message");
        assertEquals(201, statusCode);
        assertEquals("Этот логин уже используется. Попробуйте другой.", message);
        if (statusBadCode == 409){
            System.out.println("Проверка успешна: создать курьера с уже существующим логином нельзя. " + "\n" + "code: " + statusBadCode + "\n" + "message: " + message);
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
