package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("ru"));
    private static String[] status = {"active", "blocked"};

    private static String generateName() {
        return faker.name().username();

    }

    private static String generatePassword() {

        return faker.internet().password();
    }


    public static BankData generateActiveUser() {
        BankData user = new BankData();
        user.setPassword(DataGenerator.generatePassword());
        user.setLogin(DataGenerator.generateName());
        user.setStatus(status[0]);
        return user;
    }

    public static BankData generateBlockedUser() {
        BankData user = generateActiveUser();
        user.setStatus(status[1]);
        return user;
    }

    public static BankData generateUserWithNewPassword(BankData newUser) {
        newUser.setPassword(DataGenerator.generatePassword());
        return newUser;
    }

     public static BankData generateUserWithNewStatus (BankData newUser) {
        newUser.setStatus(status[1]);
         return newUser;
     }

}
