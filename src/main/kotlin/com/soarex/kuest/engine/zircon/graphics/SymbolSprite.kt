package com.soarex.kuest.engine.zircon.graphics

import com.soarex.kuest.engine.geometry.Vec2
import org.hexworks.zircon.api.color.ANSITileColor
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Tile

class SymbolSprite(override var position: Vec2, char: Char) : ZirconDrawable {
    private val tile = Tile.newBuilder()
        .withBackgroundColor(ANSITileColor.BLACK)
        .withForegroundColor(ANSITileColor.WHITE)
        .withCharacter(char)
        .buildCharacterTile()

    override fun draw(renderer: ZirconRenderer) {
        renderer.screen.draw(tile, Position.create(position.x, position.y))
    }
}