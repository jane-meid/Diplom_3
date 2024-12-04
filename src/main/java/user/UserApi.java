package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    public static String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static String LOGIN_URL = "/api/auth/login";
    public static String DELETE_URL = "/api/auth/user";
    public static String REGISTRATION_URL = "/api/auth/register";

    @Step("Создание пользователя")
    public static Response createNewUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(REGISTRATION_URL);
    }

    @Step("Логин пользователя")
    public static Response loginUser(UserLogin userLogin) {
        return given()
                .header("Content-type", "application/json")
                .body(userLogin)
                .when()
                .post(LOGIN_URL);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_URL);
    }
}
