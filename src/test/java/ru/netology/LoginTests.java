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
        BankData newUser = DataGenerator.generateUser("active");
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(newUser);
    }

    @Test
    void loginBlockedUser() {
        BankData newUser = DataGenerator.generateUser("blocked");
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLogin(newUser);
    }

    @Test
    void loginNotSavedUser() {
        BankData newUser = DataGenerator.generateUser("active");
        loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLoginForNotSavedUser(newUser);
    }

    @Test
    void canLoginWithChangedPassword() {
        BankData newUser = DataGenerator.generateUser("active");
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(newUser);
        BankData userWithChangedPassword = DataGenerator.generateUserWithNewPassword(newUser);
        SendData.sendUser(userWithChangedPassword);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(userWithChangedPassword);
    }

    @Test
    void canLoginWithChangedStatus() {
        BankData newUser = DataGenerator.generateUser("active");
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(newUser);
        BankData userWithChangedStatus = DataGenerator.generateUserWithNewStatus(newUser, "blocked");
        SendData.sendUser(userWithChangedStatus);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(userWithChangedStatus);
    }

    @Test
    void canLoginWithChangedLogin() {
        BankData newUser = DataGenerator.generateUser("active");
        SendData.sendUser(newUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(newUser);
        BankData userWithChangedLogin = DataGenerator.generateUserWithNewLogin(newUser);
        SendData.sendUser(userWithChangedLogin);
        loginPage = open("http://localhost:9999", LoginPage.class);
        dashboardPage = loginPage.login(userWithChangedLogin);
    }

    @Test
    void shouldGetErrorWithWrongLoginUser() {
        BankData activeRegisteredUser = DataGenerator.generateRegisteredUser("active");
        BankData changedLoginUser = DataGenerator.generateUserWithNewLogin(activeRegisteredUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLoginForNotSavedUser(changedLoginUser);
    }

    @Test
    void shouldGetErrorWithWrongPassword() {
        BankData activeRegisteredUser = DataGenerator.generateRegisteredUser("active");
        BankData changedPasswordUser = DataGenerator.generateUserWithNewLogin(activeRegisteredUser);
        loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.invalidLoginForNotSavedUser(changedPasswordUser);
    }

}