package com.soarex.kuest.engine.physics

import com.soarex.kuest.engine.Entity
import com.soarex.kuest.engine.forEach
import com.soarex.kuest.engine.geometry.Vec2

class DefaultPhysicsManager : PhysicsManager {
    override fun tick(sceneRoot: Entity) {
        val physicsBodies = collectPhysicsBodies(sceneRoot)
        applyForces(physicsBodies)
    }

    private fun applyForces(physicsBodies: List<PhysicsBody>) {
        val kinematicBodies = physicsBodies.filterIsInstance<KinematicBody>()
        physicsBodies.forEach { body1 ->
            kinematicBodies.forEach inner@{ body2 ->
                if (body1 == body2) return@inner

                if (body2.velocity == Vec2.ZERO) return@inner

                val newBodyPosition = body2.position + body2.velocity
                body2.velocity = Vec2.ZERO // TODO: временно, пока считаем, что это импульс
                if (body1.collider.collides(newBodyPosition)) {
                    // moving object receives the collision event first
                    body2.collision(body1)
                    body1.collision(body2)
                } else {
                    body2.position = newBodyPosition
                }
            }
        }
    }

    private fun collectPhysicsBodies(sceneRoot: Entity): List<PhysicsBody> {
        val entities: MutableList<PhysicsBody> = mutableListOf()
        sceneRoot.forEach { e ->
            if (e is PhysicsBody) entities.add(e)
        }
        return entities
    }
}