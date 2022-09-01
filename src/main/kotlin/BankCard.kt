abstract class BankCard {

    abstract var ownFunds: Double
    abstract fun replenishment(money: Double)
    abstract fun payment(money: Double)
    abstract fun balanceInformation()
    open fun availableFundsInformation() {}
}