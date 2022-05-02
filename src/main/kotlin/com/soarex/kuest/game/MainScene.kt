package com.soarex.kuest.game

import com.soarex.kuest.engine.Entity
import com.soarex.kuest.engine.event.InputEvent
import com.soarex.kuest.engine.event.KeyPressedEvent
import com.soarex.kuest.engine.event.KeyType
import com.soarex.kuest.engine.geometry.Size
import com.soarex.kuest.engine.geometry.Vec2
import com.soarex.kuest.engine.graphics.Drawable
import com.soarex.kuest.engine.physics.KinematicBody
import com.soarex.kuest.engine.physics.PointCollider
import com.soarex.kuest.engine.zircon.graphics.SymbolSprite

class MainScene(size: Size) : Entity {
    override var position: Vec2 = Vec2.ZERO
    override val children: List<Entity>
        get() = listOf(maze, player)

    private val maze: Entity = GameLevel(size)
    private val player: Entity = Player()
}

class Player : Entity, KinematicBody {
    override var position: Vec2 = Vec2(1, 1)
    override val collider: PointCollider
        get() = PointCollider(position)

    override var velocity: Vec2 = Vec2.ZERO

    private val sprite: Drawable = SymbolSprite(position, '@')

    override val children: List<Entity>
        get() = listOf(sprite)

    override fun update(delta: Double) {
        sprite.position = position
    }

    override fun input(event: InputEvent) {
        if (event !is KeyPressedEvent) return
        velocity = when (event.key) {
            KeyType.ArrowLeft -> Vec2(-1, 0)
            KeyType.ArrowRight -> Vec2(1, 0)
            KeyType.ArrowUp -> Vec2(0, -1)
            KeyType.ArrowDown -> Vec2(0, 1)
            else -> return
        }
    }
}