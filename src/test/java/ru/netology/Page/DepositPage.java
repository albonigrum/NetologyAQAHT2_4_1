package ru.netology.Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DepositPage {
    private final SelenideElement amountInput = $("[data-test-id=amount] input");
    private final SelenideElement cardFromInput = $("[data-test-id=from] input");
    private final SelenideElement cardToInput = $("[data-test-id=to] input");
    private final SelenideElement depositButton = $("[data-test-id=action-transfer]");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public DepositPage() {
        amountInput.shouldBe(visible).shouldBe(enabled);
        cardFromInput.shouldBe(visible).shouldBe(enabled);
        cardToInput.shouldBe(visible).shouldBe(disabled);
        depositButton.shouldBe(visible).shouldBe(enabled);
        cancelButton.shouldBe(visible).shouldBe(enabled);
    }

    public CardsPage deposit(String cardFrom, int amount) {
        amountInput.sendKeys(Integer.toString(amount));

        assert cardFrom.replaceAll("\\s", "").matches("[\\d]{16}");
        cardFromInput.sendKeys(cardFrom);

        depositButton.click();
        return new CardsPage();
    }

    public CardsPage cancel() {
        cancelButton.click();
        return new CardsPage();
    }
}