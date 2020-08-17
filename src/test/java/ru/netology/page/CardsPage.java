package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardsPage {
    private final SelenideElement reloadButton = $("[data-test-id=action-reload]");
    private final ElementsCollection balanceLine = $$("ul.CardList_cardBlock__gEjoa div[data-test-id]");
    private final ElementsCollection depositButtons =
            $$("ul.CardList_cardBlock__gEjoa button[data-test-id=action-deposit]");

    public CardsPage() {
        reloadButton.shouldBe(visible);
        balanceLine.shouldHaveSize(depositButtons.size());
        for (int i = 0; i < depositButtons.size(); ++i) {
            depositButtons.get(i).shouldBe(visible).shouldBe(enabled);
            balanceLine.get(i).shouldBe(visible);
        }
    }

    public int getBalanceOfCard(int indexOfCard) {
        return Integer.parseInt(balanceLine
                .get(indexOfCard)
                .getText().replaceAll("\\s", "")
                .replaceAll("^.*баланс:", "")
                .replaceAll("р\\..*$", ""));
    }

    public DepositPage clickDeposit(int indexOfCard) {
        depositButtons.get(indexOfCard).click();
        return new DepositPage();
    }

    public CardsPage reload() {
        reloadButton.click();
        return new CardsPage();
    }
}
