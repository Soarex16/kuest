package com.soarex.kuest.engine.geometry

data class Size(val width: Int, val height: Int) {
    init {
        require(width >= 0)
        require(height >= 0)
    }

    operator fun div(d: Int) = Size(width / d, height / d)
}