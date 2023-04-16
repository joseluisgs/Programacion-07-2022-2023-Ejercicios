package PokemonBattle.enums

enum class Types(val emoji: String) {
    WATER("\uD83C\uDF0A"),
    FIRE("\uD83D\uDD25"),
    PLANT("\uD83C\uDF31");

    var effectiveness: Map<Types, Double> = emptyMap()

    companion object {
        init {
            FIRE.effectiveness = mapOf(
                Pair(WATER, 0.5),
                Pair(PLANT, 2.0)
            )
            WATER.effectiveness = mapOf(
                Pair(FIRE, 2.0),
                Pair(PLANT, 0.5)
            )
            PLANT.effectiveness = mapOf(
                Pair(WATER, 2.0),
                Pair(FIRE, 0.5)
            )
        }
    }
}