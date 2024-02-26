package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;

public class DashboardPage {
    private SelenideElement personalAccount = $("[data-test-id='dashboard']");
    private ElementsCollection cardInfo = $$("[class='list__item']");
    private ElementsCollection refillButton = $$("[data-test-id='action-deposit']");

    public DashboardPage() {
        personalAccount.shouldBe(visible);
    }

    public int getCardBalance(int cardIndex) {
        String cardInfoAll = cardInfo.get(cardIndex).getText();
        String balance = StringUtils.substringBetween(cardInfoAll, "баланс:", "р.").trim();
        return parseInt(balance);
    }

    public TransferPage reliff(int cardIndexTo) {
        refillButton.get(cardIndexTo).click();
        return new TransferPage();
    }

}