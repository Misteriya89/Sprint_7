package courier;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Courier enters the system.")
    @Description("Check status code when the courier enters the system(successful request).")
    public void authorizationTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin("ninja1453");
        courier.setPassword("1234");
        Response response = clientStep.sendPostRequestApiV1CourierLogin(courier);
        response.then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", Matchers.notNullValue());

    }

    @Test
    @DisplayName("Courier enters the system without login.")
    @Description("Check status code when the courier enters the system without login (bad request).")
    public void authorizationWithoutLoginTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setPassword("12345678");
        Response response = clientStep.sendPostRequestApiV1CourierLogin(courier);
        response.then().log().all()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", Matchers.is("Недостаточно данных для входа"));

    }

    /**
     Баг в авторизации.Тест падает.
     **/
    @Test
    @DisplayName("Courier enters the system without password.")
    @Description("Check status code when the courier enters the system without password (bad request).")
    public void authorizationWithoutPasswordTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin("bestofthebest");
        Response response = clientStep.sendPostRequestApiV1CourierLogin(courier);
        response.then().log().all()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", Matchers.is("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Courier enters the system with wrong password.")
    @Description("Check status code when the courier enters the system with wrong password.")
    public void authorizationWithWrongPasswordTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin("ninja1453");
        courier.setPassword("1234000");
        Response response = clientStep.sendPostRequestApiV1CourierLogin(courier);
        response.then().log().all()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", Matchers.is("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Courier enters the system with wrong login.")
    @Description("Check status code when the courier enters the system with wrong login.")
    public void authorizationWithWrongLoginTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin("ninjabest");
        courier.setPassword("1234");
        Response response = clientStep.sendPostRequestApiV1CourierLogin(courier);
        response.then().log().all()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", Matchers.is("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Courier enters the system with wrong login and password.")
    @Description("Check status code when the courier enters the system with wrong login and password.")
    public void authorizationWithWrongLoginAndPasswordTest(){
        CourierClient clientStep = new CourierClient();
        Courier courier = new Courier();
        courier.setLogin("hjhjhkhk");
        courier.setPassword("vvhhhgvhgyh");
        Response response = clientStep.sendPostRequestApiV1CourierLogin(courier);
        response.then().log().all()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", Matchers.is("Учетная запись не найдена"));
    }


}