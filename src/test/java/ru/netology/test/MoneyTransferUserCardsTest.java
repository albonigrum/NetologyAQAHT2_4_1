package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferUserCardsTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Nested
    class HappyPathTests {
        @ParameterizedTest
        @CsvSource(value = {
                "0, 1, 1",
                "1, 0, 1",
                "0, 1, 5000",
                "1, 0, 5000",
                "0, 1, 10000",
                "1, 0, 10000"
        })
        void shouldTransfer(String sIndexCardFrom, String sIndexCardTo, String sAmount){
            int indexCardFrom = Integer.parseInt(sIndexCardFrom);
            int indexCardTo = Integer.parseInt(sIndexCardTo);
            int amount = Integer.parseInt(sAmount);

            val beforeDepositCardsPage =
                    new LoginPage()
                    .loginWithUser(getHardcodedUser())
                    .verifyCode(getHardcodedVerifyCode());

            int[] beforeDepositAmount = {
                    beforeDepositCardsPage.getBalanceOfCard(indexCardFrom),
                    beforeDepositCardsPage.getBalanceOfCard(indexCardTo)
            };

            val afterDepositCardsPage =
                    beforeDepositCardsPage
                    .clickDeposit(indexCardTo)
                    .deposit(getHardcodedCards()[indexCardFrom], amount);
            assertEquals(beforeDepositAmount[0] - amount, afterDepositCardsPage.getBalanceOfCard(indexCardFrom));
            assertEquals(beforeDepositAmount[1] + amount, afterDepositCardsPage.getBalanceOfCard(indexCardTo));
        }
    }

    @Nested
    class SadPathTests {
        @ParameterizedTest
        @CsvSource(value = {
                "0, 1, 10001",
                "1, 0, 10001"
        })
        void shouldNotTransferIfAmountMoreThanBalance(String sIndexCardFrom, String sIndexCardTo, String sAmount){
            int indexCardFrom = Integer.parseInt(sIndexCardFrom);
            int indexCardTo = Integer.parseInt(sIndexCardTo);
            int amount = Integer.parseInt(sAmount);

            val beforeDepositCardsPage =
                    new LoginPage()
                            .loginWithUser(getHardcodedUser())
                            .verifyCode(getHardcodedVerifyCode());

            int[] beforeDepositAmount = {
                    beforeDepositCardsPage.getBalanceOfCard(indexCardFrom),
                    beforeDepositCardsPage.getBalanceOfCard(indexCardTo)
            };

            val afterDepositCardsPage =
                    beforeDepositCardsPage
                            .clickDeposit(indexCardTo)
                            .deposit(getHardcodedCards()[indexCardFrom], amount);
            // Ошибки не должно быть, значения балансов не должны изменяться
            assertEquals(beforeDepositAmount[0], afterDepositCardsPage.getBalanceOfCard(indexCardFrom));
            assertEquals(beforeDepositAmount[1], afterDepositCardsPage.getBalanceOfCard(indexCardTo));
        }
    }
}
