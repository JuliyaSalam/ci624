package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Value;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Data
public class VerificationPage {
    private SelenideElement codeField = $x(".//span[@data-test-id=\"code\"]//input");
    private SelenideElement veryfyButton = $x(".//button[@data-test-id=\"action-verify\"]");

    public VerificationPage() {
        codeField.shouldBe(visible);

    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.val(verificationCode.getCode());
        veryfyButton.click();
        return new DashboardPage();
    }


}
