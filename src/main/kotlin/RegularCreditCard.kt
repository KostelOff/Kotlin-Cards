class RegularCreditCard(balance: Double) : CreditCard() {

    override var ownFunds = balance
    override var balance = creditLimit + ownFunds

    val bonusPoints = 0.01
    val cashBack = 0.01 // кэшбэк если покупки > 5000
    val doorStepCashBack = 5_000.00
    var points = 0

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
        println("Ваш уровень - RegularCard.")
        super.balanceInformation()
    }

    override fun availableFundsInformation() {
        println("Ваш уровень - RegularCard.")
        super.availableFundsInformation()
        println("Информация о бонусных баллах: $points")
    }
}