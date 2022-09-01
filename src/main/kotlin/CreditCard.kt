import kotlin.math.roundToInt

const val CREDIT_CARD_LIMIT = 10_000.00

abstract class CreditCard() : BankCard() {

    open var creditLimit: Double = 10_000.0
    override var ownFunds = 0.0
    open var balance = creditLimit + ownFunds

    override fun replenishment(money: Double) {

        if (creditLimit < CREDIT_CARD_LIMIT) { // на кредитной карте 8000
            if (creditLimit + money > CREDIT_CARD_LIMIT) { // если 8000+3000 > 10000
                val x =
                    (money + creditLimit - CREDIT_CARD_LIMIT) * 100.0.roundToInt() / 100.0  // 3000+8000-10000=1000 вычисляем сколько пополнить надо дебит
                ownFunds = ownFunds + x
                val y = money - x // сумма которую кладем на кредитную карту
                creditLimit = creditLimit + y
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
                val x = (money - ownFunds) * 100.0.roundToInt() / 100.0    // 5000-1=4999 сколько брать в долг
                val y = (money - x) * 100.0.roundToInt() / 100.0          // 5000-4999 = 1 сколько мы можем потратить
                ownFunds = ownFunds - y                     // тратим наше
                creditLimit = creditLimit - x               // тратим в долг
            } else {
                ownFunds = ownFunds - money
            }
        } else {
            creditLimit = creditLimit - money
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