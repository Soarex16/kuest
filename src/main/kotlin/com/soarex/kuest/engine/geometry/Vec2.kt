package com.soarex.kuest.engine.geometry

/**
 * Represents point in space
 */
data class Vec2(val x: Int, val y: Int) {
    operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)
    operator fun plus(value: Int) = Vec2(x + value, y + value)

    operator fun minus(other: Vec2) = Vec2(x - other.x, y - other.y)
    operator fun minus(value: Int) = Vec2(x - value, y - value)

    operator fun times(value: Int) = Vec2(x * value, y * value)

    operator fun div(value: Int) = Vec2(x / value, y / value)

    operator fun unaryMinus() = Vec2(-x, -y)

    companion object {
        val ZERO = Vec2(0, 0)
    }
}
