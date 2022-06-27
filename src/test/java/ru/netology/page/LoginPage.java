package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement loginInput = $x(".//span[@data-test-id=\"login\"]//input");
    private SelenideElement passwordInput = $x(".//span[@data-test-id=\"password\"]//input");
    private SelenideElement loginButton = $x(".//button[@data-test-id=\"action-login\"]");

    public VerificationPage login(DataHelper user) {
        loginInput.val(user.getLogin());
        passwordInput.val(user.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
