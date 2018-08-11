package com.game.maths

class Angle(rad: Double = 0.0) {

    var radians = clamp(rad)

    /* Create a copy of this angle. */
    fun copy(): Angle = Angle(radians)

    /* Set angle in radians. */
    fun set(angle: Angle) { radians = clamp(angle.radians) }

    /* Assimilate the specified angle onto this angle. */
    fun set(angle: Double) { radians = clamp(angle) }

    /* Convert degrees to radians and reassign the radians value. */
    fun setDegrees(degrees: Double): Angle {
        set(degrees / Const.TO_DEGREES)
        return this
    }

    /* Sine function. */
    fun sin(): Double = Math.sin(radians)
    /* Cosine function. */
    fun cos(): Double = Math.cos(radians)
    /* Tangent function. */
    fun tan(): Double = Math.tan(radians)

    /* Angle in degrees. */
    fun deg(): Double = radians * Const.TO_DEGREES

    /* Clamp the passed value to be a valid angle between 0 and 2π. */
    private fun clamp(toClamp: Double): Double {
        var clamped = toClamp
        while(clamped < 0) { clamped += Const.TAU }
        while(clamped >= Const.TAU) { clamped -= Const.TAU }
        return clamped
    }

    /* Clamp the angle between 0 and 2π. */
    fun clamp(): Angle {
        radians = clamp(radians)
        return this
    }

    /* Returns 1 if the shortest rotation between the two angles is clockwise, -1 if it is
    anticlockwise. */
    fun bestDirectionTo(other: Angle): Int {
        val clockwiseAngle = clamp(other.radians - radians)
        val anticlockwiseAngle = clamp(radians - other.radians)
        return if(anticlockwiseAngle < clockwiseAngle) -1 else 1
    }

    operator fun plus(rad: Double): Angle = Angle(clamp(radians + rad))
    operator fun plus(angle: Angle): Angle = this + angle.radians
    operator fun plusAssign(rad: Double) { radians = clamp(radians + rad) }
    operator fun plusAssign(angle: Angle) { radians = clamp(radians + angle.radians) }
    operator fun minus(rad: Double): Angle = Angle(clamp(radians - rad))
    operator fun minus(angle: Angle): Angle = this - angle.radians
    operator fun minusAssign(rad: Double) { radians = clamp(radians - rad) }
    operator fun minusAssign(angle: Angle) { radians = clamp(radians - angle.radians) }
    operator fun unaryMinus(): Angle = Angle(-radians)
    operator fun compareTo(angle: Angle): Int = if(radians == angle.radians) 0 else if(radians < angle.radians) - 1 else 1
}