package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;


public class TransferTest {
    DataHelper user;
    DashboardPage dashboardPage;

    @BeforeEach
    public void auth() {
        open("http://localhost:9999/");
        LoginPage login = new LoginPage();
        user = new DataHelper();
        VerificationPage verificationPage = login.login(user);
        dashboardPage = verificationPage.verify(user);
    }

    @Test
    public void transferThereAndBack() {
        TransferPage transferPage = dashboardPage.transferClick(0);
        transferPage.transfer(user, 200, 1);
        dashboardPage.assertBalance(0, 10_200);
        dashboardPage.assertBalance(1, 9_800);

        dashboardPage.transferClick(1);
        transferPage.transfer(user, 200, 0);
        dashboardPage.assertBalance(0, 10_000);
        dashboardPage.assertBalance(1, 10_000);
    }

}
