package test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBaseHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayUITest {
    private DashboardPage page;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Successful payment with an APPROVED card")
    void shouldSuccessfullyProcessPaymentByCard() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.getSuccessNotification();
        var actualStatus = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("APPROVED", actualStatus);
    }

    @Test
    @DisplayName("Payment declined with a DECLINED card")
    void shouldDeclinePaymentByCard() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.getErrorNotification();
        var actualStatus = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("DECLINED", actualStatus);
    }

    @Test
    @DisplayName("Validation failure: Empty fields")
    void shouldFailValidationCardWithEmptyFields() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyFields();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Transaction decline: Random card number")
    void shouldDeclineTransactionCardWithRandomNumber() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithRandomNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.getErrorNotification();
    }

    @Test
    @DisplayName("Validation failure: Empty card number")
    void shouldFailValidationWithEmptyCardNumber() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with 15 numbers")
    void shouldFailValidationWithCard15Numbers() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith15();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with 17 numbers")
    void shouldFailValidationWithCard17Numbers() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith17();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

  @Test
    @DisplayName("Validation failure: Card with zero in month")
    void shouldFailValidationCardWithZeroInMonth() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithTwoZero();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with month number 13")
    void shouldFailValidationWithMonthAbove12() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith13Month();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with month with one number")
    void shouldFailValidationCardWith1NumberMonth() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWith1Number();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //Cо значением, состоящим из спецсимвола в поле Месяц
    @Test
    @DisplayName("Card with month with special characters")
    void shouldFailValidationCardWithMonthSpecialCharacters() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //С пустым полем Год
    @Test
    @DisplayName("Card with empty year")
    void shouldFailValidationCardWithEmptyYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with last year")
    void shouldFailValidationCardWithLastYear() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithLastYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingCardEnded();
    }

    @Test
    @DisplayName("Validation failure: Card with special characters in year")
    void shouldFailValidationCardWithSpecialCharactersYear() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharactersYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with one-digit year")
    void shouldFailValidationCardWith1NumberYear() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith1NumberYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with empty owner")
    void shouldFailValidationCardWithEmptyOwner() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullOwner();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with owner name consisting of numbers")
    void shouldFailValidationCardWithOwnerWithNumbers() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerWithNumbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }
}
    @Test
    @DisplayName("Validation failure: Card with owner name containing special characters")
    void shouldFailValidationCardWithNameSpecialCharacters() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with owner name in Cyrillic")
    void shouldFailValidationCardWithNameWithCyrillic() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerCyrillic();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with empty CVC")
    void shouldFailValidationCardWithEmptyCVC() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with 2-digit CVC")
    void shouldFailValidationCardWith2NumbersInCVC() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC2Numbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }
}

    @Test
    @DisplayName("Validation failure: Card with special characters in CVC")
    void shouldFailValidationCardWithWithCVCSpecialCharacters() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with letters in CVC")
    void shouldFailValidationCardWithCVCLetter() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCLetter();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }
}
