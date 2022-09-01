import kotlin.math.roundToInt

class CreditCardPremium(balance: Double) : CreditCard() {

    private val accumulationCreditPremium = 0.000_05
    private val bonusPoints = 0.01
    private val cashBack = 0.05
    private val doorStepCashBack = 5_000.00

    override var ownFunds = balance

    override var balance: Double = creditLimit + ownFunds

    private var points = 0

    override fun replenishment(money: Double) {
        if (creditLimit < CREDIT_CARD_LIMIT) {
            if (creditLimit + money > CREDIT_CARD_LIMIT) {
                val x =
                    (money + creditLimit - CREDIT_CARD_LIMIT) * 100.0.roundToInt() / 100.0
                ownFunds += x + (money * accumulationCreditPremium)
                val y = money - x
                creditLimit += y
            } else if (creditLimit + money + (money * accumulationCreditPremium) < CREDIT_CARD_LIMIT) {
                creditLimit += money + (money * accumulationCreditPremium)
            } else if (creditLimit + money + (money * accumulationCreditPremium) > CREDIT_CARD_LIMIT) {
                val z = money * accumulationCreditPremium
                val x = creditLimit + money + z - CREDIT_CARD_LIMIT
                creditLimit = creditLimit + money + z - x
                ownFunds += x
            }
        } else {
            ownFunds += money + (money * accumulationCreditPremium)
        }

        balance = creditLimit + ownFunds
    }

    override fun payment(money: Double) {
        if (creditLimit + ownFunds - money < 0) {
            println("Недостаточно средств")
            return
        }

        if (ownFunds > 0.00) {
            if (ownFunds - money < 0.00) {
                val x = money - ownFunds
                val y = money - x
                ownFunds -= y
                creditLimit -= x
            } else {
                ownFunds -= money
            }
        } else {
            creditLimit -= money
        }

        when {
            money >= doorStepCashBack -> ownFunds += (money * cashBack)
        }

        points = (points + money * bonusPoints).toInt()
        balance = creditLimit + ownFunds
    }

    override fun balanceInformation() {
        println("Ваш уровень - PremiumCard.")
        super.balanceInformation()
    }

    override fun availableFundsInformation() {
        println("Ваш уровень - PremiumCard.")
        super.availableFundsInformation()
        println("Информация о бонусных баллах: $points")
    }
}