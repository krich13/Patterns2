package ru.netology.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.BankData;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login]");
    private SelenideElement errorBanner = $(Selectors.withText("Пользователь заблокирован"));
    private SelenideElement errorBannerForNotSavedUser = $(Selectors.withText("Неверно указан логин или пароль"));

    public DashboardPage login(BankData user) {
        login.setValue(user.getLogin());
        password.setValue(user.getPassword());
        button.click();
        return new DashboardPage();
    }

    public void invalidLogin(BankData user) {
        login(user);
        errorBanner.shouldBe(Condition.appear);
    }

    public void invalidLoginForNotSavedUser (BankData user) {
        login (user);
        errorBannerForNotSavedUser.shouldBe(Condition.appear);

    }
}
