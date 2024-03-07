package test.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.gson.Gson;
import data.ApiHelper;
import data.DataBaseHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static data.DataBaseHelper.cleanDataBase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayApiTest {
    private static DataHelper.CardInfo cardInfo;
    private static final Gson gson = new Gson();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Validation failure: Card with approved status")
    void shouldFailValidationWithApprovedCard() {
        cardInfo = DataHelper.getValidDataWithApprovedCard();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 200, "/api/v1/pay");
        val actualStatus = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("APPROVED", actualStatus);
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(1, countOrder);
    }


   @Test
    @DisplayName("Validation failure: Card with approved status")
    void shouldFailValidationWithApprovedCard() {
        cardInfo = DataHelper.getValidDataWithApprovedCard();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 200, "/api/v1/pay");
        val actualStatus = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("APPROVED", actualStatus);
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(1, countOrder);
    }

    @Test
    @DisplayName("Validation failure: Card with declined status")
    void shouldFailValidationWithDeclinedCard() {
        cardInfo = DataHelper.getValidDataWithDeclinedCard();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val actualStatus = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("DECLINED", actualStatus);
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    @Test
    @DisplayName("Validation failure: Card with all fields empty")
    void shouldFailValidationWithAllFieldsEmpty() {
        cardInfo = DataHelper.getCardInfoWithEmptyFields();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }
     @Test
    @DisplayName("Validation failure: Card with empty number")
    void shouldFailValidationWithEmptyNumber() {
        cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    @Test
    @DisplayName("Validation failure: Card with empty month")
    void shouldFailValidationWithEmptyMonth() {
        cardInfo = DataHelper.getCardInfoWithNullMonth();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    @Test
    @DisplayName("Validation failure: Card with empty year")
    void shouldFailValidationWithEmptyYear() {
        cardInfo = DataHelper.getCardInfoWithNullYear();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

     @Test
    @DisplayName("Validation failure: Card with empty owner")
    void shouldFailValidationWithEmptyOwner() {
        cardInfo = DataHelper.getCardInfoWithNullOwner();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }

    @Test
    @DisplayName("Validation failure: Card with empty CVC")
    void shouldFailValidationWithEmptyCVC() {
        cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        var body = gson.toJson(cardInfo);
        ApiHelper.sendRequest(body, 400, "/api/v1/pay");
        val countOrder = DataBaseHelper.getCountOrderEntity();
        assertEquals(0, countOrder);
    }
}
