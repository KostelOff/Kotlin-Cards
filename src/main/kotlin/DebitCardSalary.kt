import kotlin.math.roundToInt

const val BONUS_POINTS_DEBIT = 0.01

class DebitCardSalary(balance: Double) : DebitCard() {

    override var ownFunds: Double = balance
    var points: Int = 100 // Приветственные баллы

    override fun payment(money: Double) {
        if (ownFunds - money >= 0) {
            ownFunds = ownFunds - money
            points = points + (money * BONUS_POINTS_DEBIT).roundToInt()
        } else {
            println("Недостаточно средств")
        }
    }

    override fun balanceInformation() {
        println("Ваш уровень - DebitCard.")
        super.balanceInformation()
    }

    override fun availableFundsInformation() {
        println("Ваш уровень - DebitCard.")
        println("Информация о бонусных баллах: $points")
    }
}