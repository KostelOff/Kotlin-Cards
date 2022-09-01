import kotlin.math.roundToInt

abstract class DebitCard() : BankCard() {

    override fun replenishment(money: Double) {
        ownFunds = ownFunds + money
    }

    override fun balanceInformation() {
        println("Информация о балансе: ${(ownFunds * 100.00).roundToInt() / 100.00}")
    }
}