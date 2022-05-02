package com.soarex.kuest.game

import com.soarex.kuest.engine.Entity
import com.soarex.kuest.engine.geometry.Size
import com.soarex.kuest.engine.geometry.Vec2
import com.soarex.kuest.engine.physics.Collider
import com.soarex.kuest.engine.physics.StaticBody
import com.soarex.kuest.engine.zircon.graphics.ImageSprite
import java.util.*
import kotlin.random.Random

typealias Maze = Array<IntArray>

class GameLevel(size: Size): Entity, StaticBody {
    override var position: Vec2 = Vec2(0, 0)

    private val sprite: ImageSprite
    override val collider: Collider
    private val mazeSize: Size = Size((size.width - 1) / 4, (size.height) / 2)

    init {
        // TODO: corridor width
        val maze = MazeGenerator(mazeSize, Random.nextInt()).generate()
        sprite = createSprite(maze)
        collider = createCollider()
    }

    override val children: List<Entity>
        get() = listOf(sprite)

    private fun createCollider(): Collider = object : Collider {
        override fun collides(point: Vec2): Boolean =
            point.y in 0 until sprite.image.size &&
            point.x in 0 until sprite.image.first().size &&
            sprite.image[point.y][point.x] != ' '
    }

    // TODO: refactor
    private fun createSprite(maze: Maze): ImageSprite = buildList {
        for (i in 0 until mazeSize.height) {
            add(
                buildList {
                    for (j in 0 until mazeSize.width) {
                        addAll(if (maze[i][j] and 1 == 0) "+---".toList() else "+   ".toList())
                    }
                    add('+')
                }
            )
            add(
                buildList {
                    for (j in 0 until mazeSize.width) {
                        addAll(if (maze[i][j] and 8 == 0) "|   ".toList() else "    ".toList())
                    }
                    add('|')
                }
            )
        }
        /*add(
            buildString {
                for (j in 0 until mazeSize.width) {
                    append("+---")
                }
                append('+')
            }
        )*/
    }.let { ImageSprite(position, it) }
}

private class MazeGenerator(val fieldSize: Size, seed: Int) {
    private val random = Random(seed)
    // true if this cell of maze can be passed through
    private lateinit var grid: Array<IntArray>

    // http://weblog.jamisbuck.org/2011/1/27/maze-generation-growing-tree-algorithm
    fun generate(initialPoint: Vec2 = Vec2(0, 0)): Array<IntArray> {
        grid = Array(fieldSize.height) { IntArray(fieldSize.width) }
        require(initialPoint.isInsideField)

        val cells = generatePoints()
        val directions = Direction.values()

        var point = initialPoint

        while (cells.isNotEmpty()) {
            directions.shuffle(random)
            directions.forEach { dir ->
                val newPoint = Vec2(point.x + dir.dx, point.y + dir.dy)
                if (newPoint.isInsideField && grid[newPoint.y][newPoint.x] == 0) {
                    grid[point.y][point.x] = grid[point.y][point.x] or dir.mask
                    grid[newPoint.y][newPoint.x] = grid[newPoint.y][newPoint.x] or dir.opposite.mask
                    cells.remove(point)
                }
            }

            point = cells.random(random)
            cells.remove(point)
        }

        return grid
    }

    private fun generatePoints(): MutableSet<Vec2> {
        val points = mutableSetOf<Vec2>()
        repeat(fieldSize.height) { y ->
            repeat(fieldSize.width) { x ->
                points.add(Vec2(x, y))
            }
        }
        return points
    }

    private val Vec2.isInsideField
        get() = x in 0 until fieldSize.width && y in 0 until fieldSize.height

    private fun nextPoint(points: LinkedList<Vec2>): Vec2 {
        val getFromFront = random.nextBoolean()
        return if (getFromFront)
            points.pollFirst()
        else
            points.pollLast()
    }

    private enum class Direction(val mask: Int, val dx: Int, val dy: Int) {
        N(1, 0, 1),
        S(2, 0, -1),
        E(4, 1, 0),
        W(8, -1, 0);

        val opposite
            get() = when(this) {
                N -> S
                S -> N
                E -> W
                W -> E
            }
    }
}