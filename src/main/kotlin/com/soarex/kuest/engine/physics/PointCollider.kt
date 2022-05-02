package com.soarex.kuest.engine.physics

import com.soarex.kuest.engine.geometry.Vec2

class PointCollider(private val point: Vec2): Collider {
    override fun collides(point: Vec2): Boolean = this.point == point
}