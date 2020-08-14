package ru.netology.Page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.Data.DataHelper;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginInput = $("[data-test-id=login] input");
    private final SelenideElement passwordInput = $("[data-test-id=password] input");
    private final SelenideElement loginButton =  $("[data-test-id=action-login]");

    public LoginPage() {
        loginInput.shouldBe(visible).shouldBe(enabled);
        passwordInput.shouldBe(visible).shouldBe(enabled);
        loginButton.shouldBe(visible).shouldBe(enabled);
    }
    public VerificationPage loginWithHardcodedUser() {
        DataHelper.AuthorizationInfo info = DataHelper.HardcodedUser;
        loginInput.setValue(info.getLogin());
        passwordInput.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
