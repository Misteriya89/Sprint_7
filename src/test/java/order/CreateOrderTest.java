package order;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.order.Order;
import org.example.util.Colors;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Order order;

    public CreateOrderTest(Order order){
        this.order = order;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        RestAssured.filters(new AllureRestAssured());
    }

    @Parameterized.Parameters(name="Тестовые данные: {0}")
    public static Object[][] getOrderParameters(){
        return new Object[][] {
                {new Order("Ольга", "Иванова", "Москва, Минская, 2Ж", "2",
                        "8-925-925-25-25", "2023-09-29", "Hello, world!",
                        List.of(Colors.GRAY.name()), 6)},
                {new Order("Юлия", "Попова", "Дмитров, Школьная, 9", "4",
                        "+7 926 333 33 33", "2028-06-12",
                        "I can buy myself flowers", List.of(Colors.BLACK.name()), 5)},
                {new Order("Питер","Паркер","Нью-Йорк","10",
                        "8-000-000-00-00", "2012-01-01", "",
                        List.of(Colors.GRAY.name(), Colors.BLACK.name()), 2)},
                {new Order("Дмитрий", "Медведев", "Париж", "1",
                        "+7-333-666-88-22", "2222-02-22",
                        "Держитесь!", Collections.emptyList(), 8)}
        };
    }

    @Test
    @DisplayName("Create order test.")
    @Description("Successful creation of an order with different parameters.")
    public void createOrderTest(){
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
        response.then().log().all()
                .assertThat().body("track", Matchers.notNullValue()).and().statusCode(201);
    }

}
