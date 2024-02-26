package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TransferPage {
    private SelenideElement cardRefill = $x("//*[text()='Пополнение карты']");
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public TransferPage() {
        cardRefill.shouldBe(visible);
    }

    public void enterTransferData(String amount, int cardIndexFrom) {
        amountField.setValue(amount);
        if (cardIndexFrom == 0) {
            fromField.sendKeys(DataGenerator.getInfoAboutFirstCard().getCardNumber());
        } else {
            fromField.sendKeys(DataGenerator.getInfoAboutSecondCard().getCardNumber());
        }
        transferButton.click();
    }

    public DashboardPage validTransfer(String amount, int cardIndexFrom) {
        enterTransferData(amount, cardIndexFrom);
        return new DashboardPage();
    }



    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(text(expectedText)).shouldBe(visible);
    }
}