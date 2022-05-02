package com.soarex.kuest.engine

import com.soarex.kuest.engine.event.InputEvent
import com.soarex.kuest.engine.geometry.Vec2

interface Entity {
    var position: Vec2

    val children: List<Entity>
        get() = emptyList()

    fun update(delta: Double) {}

    fun input(event: InputEvent) {}
}

fun Entity.forEach(cb: (Entity) -> Unit) {
    cb(this)
    children.forEach { it.forEach(cb) }
}