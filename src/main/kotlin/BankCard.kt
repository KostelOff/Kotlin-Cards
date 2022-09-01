abstract class BankCard() {

    abstract var ownFunds: Double
    abstract fun replenishment(money: Double) // пополнение
    abstract fun payment(money: Double)
    abstract fun balanceInformation() // проверить баланс
    open fun availableFundsInformation() {}
}