import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private OrderAPI orderApi;
    private OrderData order;
    private int track;
    private final List<String> colour;

    public OrderCreateTest(List<String> colour){
        this.colour = colour;
    }
    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Object[][] Colour() {
        return new Object[][]{
                {List.of("BLACK", "GREY")},
                {List.of("GREY")},
                {List.of()}
        };
    }
    @Before
   public void getURL() {
        orderApi = new OrderAPI();
        order = OrderGenerator.getNewOrder(colour);
    }
    @Test
    @Step("Создаем заказ")
    public void orderCreate(){
        ValidatableResponse response = orderApi.create(order);
        int status = orderApi.create(order).extract().statusCode();
        track = response.extract().path("track");
        assertEquals(201, status);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        OrderTrackDesserialization trackResponse = response.extract().body().as(OrderTrackDesserialization.class);
        String trackJson = gson.toJson(trackResponse);
        assertTrue(trackJson.contains("track"));
    }
    @After
    @Step("Отменяем заказ")
    public void delete() {
        orderApi.delete(track);
    }
}
