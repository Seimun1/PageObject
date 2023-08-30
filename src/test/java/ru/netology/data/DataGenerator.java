package ru.netology.data;

import lombok.Value;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class DataGenerator {

    private DataGenerator() {}

    @Value
    public static class AuthData {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static AuthData getAuthData() {
        return new AuthData("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardData {
        String cardNumber;
    }

    public static CardData getInfoAboutFirstCard() {
        return new CardData("5559 0000 0000 0001");
    }

    public static CardData getInfoAboutSecondCard() {
        return new CardData("5559 0000 0000 0002");
    }

    public static int generateValidAmount(int balance) {
        return new Random().nextInt(Math.abs(balance)) + 1;
    }

    public static int generateInValidAmount(int balance) {
        return Math.abs(balance) + new Random().nextInt(10000) + 1;
    }
}
