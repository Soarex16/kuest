package com.soarex.kuest.game

import com.soarex.kuest.engine.geometry.Size
import com.soarex.kuest.engine.zircon.ZirconGameManager

fun main() {
    val size = Size(70, 50)
    val game = ZirconGameManager(
        "Rougelike",
        size,
        MainScene(size)
    )
    game.start()
}