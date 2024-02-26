package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton= $("[data-test-id='action-login']");

    public VerificationPage validLogin (DataGenerator.AuthData data){
        loginField.setValue(data.getLogin());
        passwordField.setValue(data.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

}

