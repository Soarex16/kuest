package com.soarex.kuest.engine.zircon.graphics

import com.soarex.kuest.engine.Entity
import com.soarex.kuest.engine.forEach
import com.soarex.kuest.engine.graphics.GraphicsRenderer
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.screen.Screen

class ZirconRenderer(val screen: Screen) : GraphicsRenderer {
    override fun paint(sceneRoot: Entity) {
        screen.getLayerAtOrNull(0)?.clear()
        screen.fill(Tile.defaultTile())
        sceneRoot.forEach {
            if (it is ZirconDrawable) {
                it.draw(this)
            }
        }
    }
}