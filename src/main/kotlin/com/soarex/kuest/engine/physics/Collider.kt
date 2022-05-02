package com.soarex.kuest.engine.physics

import com.soarex.kuest.engine.geometry.Vec2

interface Collider {
    fun collides(point: Vec2): Boolean
}