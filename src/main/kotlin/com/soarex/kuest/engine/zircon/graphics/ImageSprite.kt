package com.soarex.kuest.engine.zircon.graphics

import com.soarex.kuest.engine.geometry.Vec2
import org.hexworks.zircon.api.builder.graphics.TileCompositeBuilder
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.StyleSet

class ImageSprite(override var position: Vec2, val image: List<List<Char>>) : ZirconDrawable {
    override fun draw(renderer: ZirconRenderer) {
        val tiles = mutableMapOf<Position, Tile>()
        image.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                val pos = Position.create(x, y)
                val tile = Tile.createCharacterTile(c, StyleSet.defaultStyle())
                tiles[pos] = tile
            }
        }

        val tileSize = Size.create(
            image.first().size,
            image.size
        )
        val composite = TileCompositeBuilder
            .newBuilder()
            .withSize(tileSize)
            .withTiles(tiles)
            .build()
        renderer.screen.draw(composite)
    }
}