package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardPage {
    private static ElementsCollection cards = $$x(".//li[@class=\"list__item\"]/div");
    private static ElementsCollection actionButton = $$x(".//button[@data-test-id=\"action-deposit\"]");
    private static SelenideElement reloadButton = $x(".//button[@data-test-id=\"action-reload\"]");
    public static SelenideElement errorNotification = $x(".//div[@data-test-id=\"error-notification\"]");

    public static TransferPage transferClick(int indexCardTo) {
        actionButton.get(indexCardTo).click();
        return new TransferPage();
    }


    public static int getBalance(int index) {
        reloadButton.click();
        String[] card = cards.get(index).toString().split(" ");
        int balance = valueOf(card[6]);
        return balance;
    }



}
