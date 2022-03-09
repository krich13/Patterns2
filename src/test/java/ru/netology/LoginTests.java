package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.data.BankData;
import ru.netology.data.DataGenerator;
import ru.netology.pageObjects.DashboardPage;
import ru.netology.pageObjects.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Test
    void loginCorrectUser() {
        BankData newUser = DataGenerator.generateActiveUser();
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(newUser);
    }

    @Test
    void loginBlockedUser() {
        BankData newUser = DataGenerator.generateBlockedUser();
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLogin(newUser);
    }

    @Test
    void loginNotSavedUser() {
        BankData newUser = DataGenerator.generateActiveUser();
        loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLoginForNotSavedUser(newUser);
    }

    @Test
    void canLoginWithChangedPassword () {
        BankData newUser = DataGenerator.generateActiveUser();
        SendData.sendUser(newUser);
        String password = newUser.getPassword();
        DataGenerator.generateUserWithNewPassword(newUser);
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        newUser.setPassword(password);
        loginPage.invalidLoginForNotSavedUser(newUser);
    }

   @Test
    void canLoginWithChangedStatus(){
       BankData newUser = DataGenerator.generateActiveUser();
       SendData.sendUser(newUser);
       loginPage = open("http://localhost:9999", LoginPage.class);
       dashboardPage = loginPage.login(newUser);
       DataGenerator.generateUserWithNewStatus(newUser);
       SendData.sendUser(newUser);
       loginPage = open("http://localhost:9999", LoginPage.class);
       loginPage.invalidLogin(newUser);
   }

}
