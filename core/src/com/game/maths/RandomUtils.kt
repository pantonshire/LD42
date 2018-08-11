package com.game.maths

import java.util.Random

object RandomUtils {

    val rng: Random = Random()

    /* Has the specified probability of returning true. chance should be between 0 and 1 inclusive.
     * Returns false otherwise. */
    fun chance(chance: Double): Boolean = rng.nextDouble() < chance

    /* 50% chance to return true, 50% change to return false. */
    fun flipCoin(): Boolean = rng.nextBoolean()

    /* Random integer value in the specified range. */
    fun randRange(range: IntRange): Int =
            rng.nextInt(range.endInclusive - range.start + 1) + range.start

}