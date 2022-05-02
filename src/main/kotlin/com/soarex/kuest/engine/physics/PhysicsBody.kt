package com.soarex.kuest.engine.physics

import com.soarex.kuest.engine.Entity
import com.soarex.kuest.engine.geometry.Vec2

interface PhysicsBody: Entity {
    val collider: Collider

    fun collision(other: PhysicsBody) { }
}

interface StaticBody : PhysicsBody

/**
 * Предполагается, что все, что двигается размером с одну клетку
 * (потому что я не смог придумать простой алгоритм разрешения коллизий
 * в случае, когда статические объекты имеют сложную форму)
 */
interface KinematicBody : PhysicsBody {
    override val collider: PointCollider

    var velocity: Vec2
}