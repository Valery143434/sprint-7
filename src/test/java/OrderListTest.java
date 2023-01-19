import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderListTest {
    private OrderAPI orderAPI;

    @Before
    public void getURL() {
        orderAPI = new OrderAPI();
    }
    @Test
    @Step("Получаем лист заказов")
    public void getOrdersList() {
        ValidatableResponse responseCreate = orderAPI.get();
        int status = responseCreate.extract().statusCode();
        List<HashMap> body = responseCreate.extract().path("orders");
        assertEquals(200, status);
        assertNotNull(body);
    }
}
