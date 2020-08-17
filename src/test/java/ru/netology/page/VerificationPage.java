package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

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

    public CardsPage verifyCode(String verificationCode) {
        codeInput.sendKeys(verificationCode);
        verifyButton.click();
        return new CardsPage();
    }
}
