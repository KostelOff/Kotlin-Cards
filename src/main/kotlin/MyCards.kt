import kotlin.random.Random

class MyCards {

    val myCards = listOf(
        DebitCardSalary(1_000.0),
        CreditCardPremium(2_000.0),
        RegularCreditCard(1_000.0)
    )

    fun checkWork() {
        myCards.forEach {

            when (Random.nextInt(1, 5)) {
                1 -> it.replenishment(Random.nextDouble(1000.0, 3000.0))
                2 -> it.payment(Random.nextDouble(1000.0, 3000.0))
                3 -> it.balanceInformation()
                4 -> it.availableFundsInformation()
            }
        }
    }
}