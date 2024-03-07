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

public class CreditUITest {
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
    @DisplayName("Validation failure: Card with APPROVED status")
    void shouldFailValidationCardWithApprovedStatus() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        creditPage.inputData(cardInfo);
        creditPage.getSuccessNotification();
        var actualStatus = DataBaseHelper.getStatusCreditRequest();
        assertEquals("APPROVED", actualStatus);
    }

    @Test
    @DisplayName("Validation failure: Card with DECLINED status")
    void shouldFailValidationCardWithDeclinedStatus() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        creditPage.inputData(cardInfo);
        creditPage.getErrorNotification();
        var actualStatus = DataBaseHelper.getStatusCreditRequest();
        assertEquals("DECLINED", actualStatus);
    }

   @Test
    @DisplayName("Validation failure: Card with empty fields")
    void shouldFailValidationCardWithEmptyFields() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyFields();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Transaction decline: Card with random number")
    void shouldDeclineTransactionCardWithRandomNumber() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithRandomNumber();
        creditPage.inputData(cardInfo);
        creditPage.getErrorNotification();
    }

    @Test
    @DisplayName("Validation failure: Card with empty card number")
    void shouldFailValidationWithEmptyCardNumber() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with 15-digit number")
    void shouldFailValidationWithCard15Numbers() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith15();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

     @Test
    @DisplayName("Validation failure: Card with 17-digit number")
    void shouldFailValidationWithCard17Numbers() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith17();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with special characters in number")
    void shouldFailValidationCardWithSpecialCharacters() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with empty month")
    void shouldFailValidationCardWithEmptyMonth() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullMonth();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with month '00'")
    void shouldFailValidationCardWithZeroInMonth() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithTwoZero();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with month number 13")
    void shouldFailValidationWithMonthAbove12() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith13Month();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongDateFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with month with one number")
    void shouldFailValidationCardWith1NumberMonth() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWith1Number();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with month with special characters")
    void shouldFailValidationCardWithMonthSpecialCharacters() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with empty year")
    void shouldFailValidationCardWithEmptyYear() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with last year")
    void shouldFailValidationCardWithLastYear() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithLastYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingCardEnded();
    }

    @Test
    @DisplayName("Validation failure: Card with special characters in year")
    void shouldFailValidationCardWithSpecialCharactersYear() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharactersYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with one-digit year")
    void shouldFailValidationCardWith1NumberYear() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith1NumberYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with empty owner")
    void shouldFailValidationCardWithEmptyOwner() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullOwner();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }
    @Test
    @DisplayName("Validation failure: Card with owner name consisting of numbers")
    void shouldFailValidationCardWithOwnerWithNumbers() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerWithNumbers();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with owner name in Cyrillic")
    void shouldFailValidationCardWithNameWithCyrillic() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerCyrillic();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with owner name containing special characters")
    void shouldFailValidationCardWithNameSpecialCharacters() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with empty CVC")
    void shouldFailValidationCardWithEmptyCVC() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    @Test
    @DisplayName("Validation failure: Card with 2-digit CVC")
    void shouldFailValidationCardWith2NumbersInCVC() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC2Numbers();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with special characters in CVC")
    void shouldFailValidationCardWithWithCVCSpecialCharacters() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    @Test
    @DisplayName("Validation failure: Card with letters in CVC")
    void shouldFailValidationCardWithCVCLetter() {
        page = open("http://localhost:8080/", DashboardPage.class);
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCLetter();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }
}
