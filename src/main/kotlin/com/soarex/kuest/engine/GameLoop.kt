package com.soarex.kuest.engine

import com.soarex.kuest.engine.event.Event
import com.soarex.kuest.engine.event.KeyPressedEvent
import com.soarex.kuest.engine.event.KeyType
import com.soarex.kuest.engine.graphics.GraphicsRenderer
import com.soarex.kuest.engine.input.InputManager
import com.soarex.kuest.engine.physics.PhysicsManager

private const val FPS = 60
private const val REFRESH_TIME = 1000L / FPS

open class GameLoop(
    private val sceneRoot: Entity,
    private val inputManager: InputManager,
    private val physicsManager: PhysicsManager,
    private val renderer: GraphicsRenderer,
    private val exitKeys: List<KeyType> = listOf(KeyType.Escape)
) {
    fun start() {
        var currentTime = System.currentTimeMillis()
        while (true) {
            // request input
            val inputEvent = inputManager.next()

            // process input
            if (exitRequested(inputEvent))
                break

            if (inputEvent != null) {
                sceneRoot.forEach { it.input(inputEvent) }
            }

            val newTime = System.currentTimeMillis()
            val delta = (newTime - currentTime) / 1000.0
            currentTime = newTime

            physicsManager.tick(sceneRoot)

            sceneRoot.forEach { it.update(delta) }

            //redraw
            renderer.paint(sceneRoot)
            Thread.sleep(REFRESH_TIME)
        }
    }

    private fun exitRequested(event: Event?): Boolean =
        event != null && event is KeyPressedEvent && event.key in exitKeys
}