package blackjack.domain

class Player(val name: PlayerName, val hand: Hand = Hand.createEmpty()) {

    val score: Score
        get() = hand.score

    fun draw(deck: Deck) {
        check(canHit()) { "카드를 뽑을 수 없습니다." }

        hand.add(deck.drawCard())
    }

    fun draw(deck: Deck, answer: PlayerAnswer): DrawResult {
        if (!answer.hit) {
            return DrawResult(false)
        }
        val success = runCatching { draw(deck) }.isSuccess
        return DrawResult(success)
    }

    fun canHit(): Boolean = hand.canHit()

    @JvmInline
    value class DrawResult(val success: Boolean)
}
