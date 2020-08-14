package ru.netology.Page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.Data.DataHelper;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardsPage {
    private final static int COUNT_CARDS;

    static {
        COUNT_CARDS = DataHelper.HardcodedCards.length;
    }

    private final SelenideElement reloadButton = $("[data-test-id=action-reload]");
    private final ElementsCollection balanceLine = $$("ul.CardList_cardBlock__gEjoa div[data-test-id]");
    private final ElementsCollection depositButtons =
            $$("ul.CardList_cardBlock__gEjoa button[data-test-id=action-deposit]");

    private static void validateIndexOfCard(int index) {
        assert index >= 0;
        assert index < COUNT_CARDS;
    }

    public CardsPage() {
        reloadButton.shouldBe(visible);
        depositButtons.shouldHaveSize(COUNT_CARDS);
        balanceLine.shouldHaveSize(COUNT_CARDS);
        for (int i = 0; i < COUNT_CARDS; ++i) {
            depositButtons.get(i).shouldBe(visible).shouldBe(enabled);
            balanceLine.get(i).shouldBe(visible);
        }
    }

    public int getBalanceOfCard(int indexOfCard) {
        validateIndexOfCard(indexOfCard);
        return Integer.parseInt(balanceLine
                .get(indexOfCard)
                .getText().replaceAll("\\s", "")
                .replaceAll("^.*баланс:", "")
                .replaceAll("р\\..*$", ""));
    }

    public DepositPage clickDeposit(int indexOfCard) {
        validateIndexOfCard(indexOfCard);
        depositButtons.get(indexOfCard).click();
        return new DepositPage();
    }

    public CardsPage reload() {
        reloadButton.click();
        return new CardsPage();
    }
}
