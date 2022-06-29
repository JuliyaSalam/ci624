package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TransferTest {

    private final String[] cards = new String[]{"5559 0000 0000 0001", "5559 0000 0000 0002"};

    public String getCard(int index) {
        String card = cards[index];
        return card;
    }


    @BeforeEach
    public void auth() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void transferThereAndBack() {

       TransferPage transferPage = new TransferPage();
        DashboardPage.transferClick(0);
        transferPage.transfer(200, 1);
        assertBalance(0, 10_200);
        assertBalance(1, 9_800);

        DashboardPage.transferClick(1);
        transferPage.transfer(200, 0);
        assertBalance(0, 10_000);
        assertBalance(1, 10_000);
    }

    @Test
    public void transferThereAndBackLimMax() {

        TransferPage transferPage = new TransferPage();
        DashboardPage.transferClick(0);
        transferPage.transfer(11_000, 1);
        DashboardPage.errorNotification.$x(".//div[@class=\"notification__content\"]").shouldHave(exactText("Ошибка!"));
        DashboardPage.transferClick(1);
        transferPage.transfer(11_000, 0);
        DashboardPage.errorNotification.$x(".//div[@class=\"notification__content\"]").shouldHave(exactText("Ошибка!"));
    }


    public void assertBalance(int index, int experctedBalance) {
        int actualBalance = DashboardPage.getBalance(index);
        assertEquals(experctedBalance, actualBalance);
    }

}
