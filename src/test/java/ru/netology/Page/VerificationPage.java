package ru.netology.Page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.Data.DataHelper;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeInput = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("button[data-test-id=action-verify]");

    public VerificationPage() {
        codeInput.shouldBe(visible).shouldBe(enabled);
        verifyButton.shouldBe(visible).shouldBe(enabled);
    }

    public CardsPage verifyWithHardcodedCode() {
        codeInput.sendKeys(DataHelper.HardcodedVerifyCode);
        verifyButton.click();
        return new CardsPage();
    }
}
