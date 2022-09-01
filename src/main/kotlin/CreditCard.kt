import kotlin.math.roundToInt

const val CREDIT_CARD_LIMIT = 10_000.00

abstract class CreditCard : BankCard() {

    open var creditLimit: Double = 10_000.0
    override var ownFunds = 0.0
    open var balance = creditLimit + ownFunds

    override fun replenishment(money: Double) {

        if (creditLimit < CREDIT_CARD_LIMIT) {
            if (creditLimit + money > CREDIT_CARD_LIMIT) {
                val x =
                    (money + creditLimit - CREDIT_CARD_LIMIT) * 100.0.roundToInt() / 100.0
                ownFunds += x
                val y = money - x
                creditLimit += y
            } else if (creditLimit + money < CREDIT_CARD_LIMIT) {
                creditLimit = (creditLimit + money) * 100.0.roundToInt() / 100.0
            }
        } else {
            ownFunds = (ownFunds + money) * 100.0.roundToInt() / 100.0
        }
        balance = creditLimit + ownFunds
    }

    override fun payment(money: Double) {
        if (creditLimit + ownFunds - money < 0) {
            println("Недостаточно средств")
            return
        }

        if (ownFunds > 0.0) {
            if (ownFunds - money < 0.0) {
                val x = (money - ownFunds) * 100.0.roundToInt() / 100.0
                val y = (money - x) * 100.0.roundToInt() / 100.0
                ownFunds -= y
                creditLimit -= x
            } else {
                ownFunds -= money
            }
        } else {
            creditLimit -= money
        }
        balance = creditLimit + ownFunds
    }

    override fun balanceInformation() {
        println("Информация о балансе: ${(ownFunds * 100.00).roundToInt() / 100.00}")
    }

    override fun availableFundsInformation() {
        println("Информация о доступных средствах:")
        balanceInformation()
        println(
            """
            Кредитные средства: ${(creditLimit * 100.00).roundToInt() / 100.00}
            Общий баланс: ${(balance * 100.00).roundToInt() / 100.00}   
        """.trimIndent()
        )
    }
}