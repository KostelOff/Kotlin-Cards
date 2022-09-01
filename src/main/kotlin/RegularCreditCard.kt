class RegularCreditCard(balance: Double) : CreditCard() {

    override var ownFunds = balance
    override var balance = creditLimit + ownFunds

    private val bonusPoints = 0.01
    private val cashBack = 0.01
    private val doorStepCashBack = 5_000.00
    private var points = 0

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
        println("Ваш уровень - RegularCard.")
        super.balanceInformation()
    }

    override fun availableFundsInformation() {
        println("Ваш уровень - RegularCard.")
        super.availableFundsInformation()
        println("Информация о бонусных баллах: $points")
    }
}