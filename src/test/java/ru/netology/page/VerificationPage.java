package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private SelenideElement codeInput = $x(".//span[@data-test-id=\"code\"]//input");
    private SelenideElement veryButton = $x(".//button[@data-test-id=\"action-verify\"]");

    public DashboardPage verify(DataHelper user) {
        codeInput.val(user.getVerificationCode());
        veryButton.click();
        return new DashboardPage();
    }
}
