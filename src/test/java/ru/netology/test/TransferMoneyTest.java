package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;


public class TransferMoneyTest {

    DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(getAuthData());
        dashboardPage = verificationPage.validVerify(getVerificationCode());
    }

    @ParameterizedTest
    @CsvSource ({
            "1, 0",
            "0, 1"
    })
    @DisplayName("Should successfully transfer money from card to card")
    void shouldTransfer1to2(int cardIndexFrom, int cardIndexTo) {
        var fromCardBalance = dashboardPage.getCardBalance(cardIndexFrom);
        var toCardBalance = dashboardPage.getCardBalance(cardIndexTo);
        var amount = generateValidAmount(fromCardBalance);
        var transferPage = dashboardPage.reliff(cardIndexTo);
        dashboardPage = transferPage.validTransfer(Integer.toString(amount), cardIndexFrom);
        var expectedFromCardBalance = fromCardBalance - amount;
        var expectedToCardBalance = toCardBalance + amount;
        var actualFromCardBalance = dashboardPage.getCardBalance(cardIndexFrom);
        var actualToCardBalance = dashboardPage.getCardBalance(cardIndexTo);
        Assertions.assertEquals(expectedFromCardBalance, actualFromCardBalance);
        Assertions.assertEquals(expectedToCardBalance, actualToCardBalance);
    }

//    @Test
//    @DisplayName("Should show errorMessage when amount for transfer more then balance")
//    void shouldShowErrorMessage() {
//        int cardIndexFrom = 0;
//        int cardIndexTo = 1;
//        var fromCardBalance = dashboardPage.getCardBalance(cardIndexFrom);
//        var toCardBalance = dashboardPage.getCardBalance(cardIndexTo);
//        var amount = generateInValidAmount(fromCardBalance);
//        var transferPage = dashboardPage.reliff(cardIndexTo);
//        transferPage.validTransfer(Integer.toString(amount), cardIndexFrom);
//        transferPage.findErrorMessage("Ошибка! Недостаточно средств для перевода");
//        var actualFromCardBalance = dashboardPage.getCardBalance(cardIndexFrom);
//        var actualToCardBalance = dashboardPage.getCardBalance(cardIndexTo);
//        Assertions.assertEquals(fromCardBalance, actualFromCardBalance);
//        Assertions.assertEquals(toCardBalance, actualToCardBalance);
//    }

}
