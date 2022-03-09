package ru.netology.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

private SelenideElement title = $(".icon");

    public DashboardPage() {
        title.shouldBe(Condition.appear);
    }
}
