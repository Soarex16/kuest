package com.soarex.kuest.engine.zircon

import com.soarex.kuest.engine.Entity
import com.soarex.kuest.engine.GameLoop
import com.soarex.kuest.engine.geometry.Size
import com.soarex.kuest.engine.graphics.GraphicsRenderer
import com.soarex.kuest.engine.input.InputManager
import com.soarex.kuest.engine.physics.DefaultPhysicsManager
import com.soarex.kuest.engine.zircon.graphics.ZirconRenderer
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.application.CloseBehavior
import org.hexworks.zircon.api.screen.Screen

class ZirconGameManager(title: String, size: Size, private val sceneRoot: Entity) {
    private val renderer: GraphicsRenderer
    private val inputManager: InputManager
    private val physicsManager = DefaultPhysicsManager()

    init {
        val tileGrid = SwingApplications.startTileGrid(
            AppConfig.newBuilder()
                .withTitle(title)
                .withSize(size.width, size.height)
                .withCloseBehavior(CloseBehavior.EXIT_ON_CLOSE)
                .build()
        )
        val screen = Screen.create(tileGrid)
        screen.display()

        renderer = ZirconRenderer(screen)
        inputManager = ZirconInputManager(screen)
    }

    fun start() {
        val gameLoop = GameLoop(sceneRoot, inputManager, physicsManager, renderer)
        gameLoop.start()
    }
}