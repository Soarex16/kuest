package com.soarex.kuest.engine.physics

import com.soarex.kuest.engine.geometry.Vec2

class BoxCollider(private val p1: Vec2, private val p2: Vec2) : Collider {
    override fun collides(point: Vec2): Boolean = point.x in p1.x..p2.x && point.y in p1.y..p2.y
}