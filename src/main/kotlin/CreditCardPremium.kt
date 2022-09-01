import kotlin.math.roundToInt

class CreditCardPremium(balance: Double) : CreditCard() {

    val accumulationCreditPremium = 0.000_05 // пополнение накопления
    val bonusPoints = 0.01
    val cashBack = 0.05 // кэшбэк если покупки > 5000
    val doorStepCashBack = 5_000.00

    // override var creditLimit = 10_000.00
    override var ownFunds = balance

    override var balance: Double = creditLimit + ownFunds

    var points = 0

    override fun replenishment(money: Double) {
        if (creditLimit < CREDIT_CARD_LIMIT) { // на кредитной карте 8000
            if (creditLimit + money > CREDIT_CARD_LIMIT) { // если 8000+3000 > 10000
                val x =
                    (money + creditLimit - CREDIT_CARD_LIMIT) * 100.0.roundToInt() / 100.0  // 3000+8000-10000=1000 вычисляем сколько пополнить надо дебит
                ownFunds =
                    ownFunds + x + (money * accumulationCreditPremium) // пополняем дебит + накопления от пополняемой суммы
                val y = money - x // сумма которую кладем на кредитную карту
                creditLimit = creditLimit + y
            } else if (creditLimit + money + (money * accumulationCreditPremium) < CREDIT_CARD_LIMIT) {
                creditLimit = creditLimit + money + (money * accumulationCreditPremium)
            } else if (creditLimit + money + (money * accumulationCreditPremium) > CREDIT_CARD_LIMIT) {
                val z = money * accumulationCreditPremium  // деньги от пополнения 1000*0,00005=0,05
                val x = creditLimit + money + z - CREDIT_CARD_LIMIT // Излишек
                creditLimit = creditLimit + money + z - x // пополняем кредитку до 10_000
                ownFunds = ownFunds + x // пополняем дебит + накопления от пополняемой суммы
            }
        } else {
            ownFunds = ownFunds + money + (money * accumulationCreditPremium)
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
                val x = money - ownFunds    // скок надо денег в долг  5000-1=4999
                val y = money - x           // узнаем скольк мы сможем заплатить 5000-4999 = 1
                ownFunds = ownFunds - y     // списываем возможные наши деньги
                creditLimit = creditLimit - x // писываем с кредитки (в долг)
            } else {
                ownFunds = ownFunds - money
            }
        } else {
            creditLimit = creditLimit - money
        }

        when {
            money >= doorStepCashBack -> ownFunds = ownFunds + (money * cashBack)
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