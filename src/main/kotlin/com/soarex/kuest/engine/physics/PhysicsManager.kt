package com.soarex.kuest.engine.physics

import com.soarex.kuest.engine.Entity

interface PhysicsManager {
    fun tick(sceneRoot: Entity)
}