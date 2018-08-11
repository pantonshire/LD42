package com.game.maths

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import java.awt.Point

/* 2D vector class with some operator overloading methods for convenience. */
class Vector(var x: Double = 0.0, var y: Double = 0.0) {

    constructor(gdxVector: Vector2): this(gdxVector.x.toDouble(), gdxVector.y.toDouble())

    constructor(gdxVector: Vector3): this(gdxVector.x.toDouble(), gdxVector.y.toDouble())


    /* New vector with the same x and y values. */
    fun copy(): Vector = Vector(x, y)

    /* Make a new Vector2 instance with the x and y values. */
    fun toVector2(): Vector2 = Vector2(x.toFloat(), y.toFloat())

    /* Make a new Vector3 instance with the x and y values and z = 0. Mainly for camera stuff. */
    fun toVector3(): Vector3 = Vector3(x.toFloat(), y.toFloat(), 0f)

    /* X value as a single-precision floating-point number. */
    fun xFloat(): Float = x.toFloat()

    /* Y value as a single-precision floating-point number. */
    fun yFloat(): Float = y.toFloat()

    /* Return a new Point instance with x and y values rounded. */
    fun round(): Point = Point(Math.round(x).toInt(), Math.round(y).toInt())

    /* Return a new Point instance with x and y values floored. */
    fun floor(): Point = Point(Math.floor(x).toInt(), Math.floor(y).toInt())


    /* Returns a boolean indicating whether this is a zero vector. */
    fun isZero(): Boolean = x == 0.0 && y == 0.0

    /* Squared magnitude of this vector. */
    fun magnitudeSqr(): Double = x * x + y * y

    /* Magnitude of this vector using Pythagoras' theorem. */
    fun magnitude(): Double = Math.sqrt(magnitudeSqr())

    /* Compute the angle of this vector. */
    fun angle(): Angle = Angle(Math.atan2(-x, y))


    /* Make this vector a unit vector. */
    fun normalise(): Vector {
        val mag = magnitude()
        if(mag != 0.0) { this /= mag }
        return this
    }


    /* Initialise the vector to have the specified angle and magnitude then return the vector. */
    fun setAngle(angle: Angle, magnitude: Double = magnitude()): Vector {
        x = -magnitude * angle.sin()
        y = magnitude * angle.cos()
        return this
    }

    /* Set the angle of this vector, preserving its magnitude. */
    fun rotateTo(newAngle: Angle): Vector = setAngle(newAngle, magnitude())


    /* Set the x and y values. */
    fun set(setX: Double, setY: Double) {
        x = setX
        y = setY
    }

    /* Assimilate the specified vector onto this vector. */
    fun set(setTo: Vector) = set(setTo.x, setTo.y)

    /* Add the specified x and y values to this vector. */
    fun add(addX: Double = 0.0, addY: Double = 0.0): Vector {
        x += addX
        y += addY
        return this
    }


    //Operator overloading
    operator fun plus(vector: Vector): Vector = Vector(x + vector.x, y + vector.y)
    operator fun minus(vector: Vector): Vector = Vector(x - vector.x, y - vector.y)
    operator fun times(scalar: Double): Vector = Vector(x * scalar, y * scalar)
    operator fun div(denominator: Double): Vector = Vector(x / denominator, y / denominator)
    operator fun plusAssign(vector: Vector) { add(vector.x, vector.y) }
    operator fun minusAssign(vector: Vector) { add(-vector.x, -vector.y) }
    operator fun timesAssign(scalar: Double) { x *= scalar; y *= scalar }
    operator fun divAssign(denominator: Double) { x /= denominator; y /= denominator }


    /* Returns the difference in angle between this vector and the other vector. */
    infix fun deltaAngle(other: Vector): Angle = (other - this).angle()

    /* Dot product of this vector and the other vector. */
    infix fun dot(other: Vector): Double = x * other.x + y * other.y

    /* Difference in x between this vector and the other vector. */
    infix fun xDelta(other: Vector): Double = x - other.x

    /* Difference in y between this vector and the other vector. */
    infix fun yDelta(other: Vector): Double = y - other.y

    /* Returns the squared distance between teh other vector. */
    infix fun distSq(other: Vector): Double {
        val deltaX = this xDelta other
        val deltaY = this yDelta other
        return deltaX * deltaX + deltaY * deltaY
    }

    /* Distance between the two vectors. */
    infix fun dist(other: Vector): Double = Math.sqrt(this distSq other)
}