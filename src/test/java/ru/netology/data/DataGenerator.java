package ru.netology.data;

import com.github.javafaker.Faker;
import ru.netology.SendData;

import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("ru"));

    private static String generateName() {
        return faker.name().username();

    }

    private static String generatePassword() {
        return faker.internet().password();
    }

    public static BankData generateUser(String status) {
        return new BankData(generateName(), generatePassword(), status);
    }

    public static BankData generateRegisteredUser(String status) {
        BankData user = generateUser(status);
        return user;
    }

    public static BankData generateUserWithNewPassword(BankData user) {
        return new BankData(user.getLogin(), generatePassword(), user.getStatus());
    }

    public static BankData generateUserWithNewLogin(BankData user) {
        return new BankData(generateName(), user.getPassword(), user.getStatus());
    }

    public static BankData generateUserWithNewStatus(BankData user, String status) {
        return new BankData(user.getLogin(), user.getPassword(), status);
    }
}

