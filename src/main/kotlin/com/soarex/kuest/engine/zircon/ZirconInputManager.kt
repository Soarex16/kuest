package com.soarex.kuest.engine.zircon

import com.soarex.kuest.engine.event.InputEvent
import com.soarex.kuest.engine.event.KeyPressedEvent
import com.soarex.kuest.engine.event.KeyType
import com.soarex.kuest.engine.input.InputManager
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Pass
import org.hexworks.zircon.api.uievent.Processed
import java.util.concurrent.ConcurrentLinkedQueue

class ZirconInputManager(tileGrid: TileGrid): InputManager {
    private val eventQueue = ConcurrentLinkedQueue<InputEvent>()

    init {
        tileGrid.handleKeyboardEvents(KeyboardEventType.KEY_PRESSED) { event, _ ->
            val keyCode = translateKey(event.code)
            if (keyCode != null) {
                eventQueue.add(KeyPressedEvent(keyCode))
                Processed
            } else {
                Pass
            }
        }
    }

    private fun translateKey(code: KeyCode): KeyType? = when (code) {
        KeyCode.LEFT -> KeyType.ArrowLeft
        KeyCode.RIGHT -> KeyType.ArrowRight
        KeyCode.UP -> KeyType.ArrowUp
        KeyCode.DOWN -> KeyType.ArrowDown
        KeyCode.ESCAPE -> KeyType.Escape
        KeyCode.ENTER -> KeyType.Enter
        KeyCode.BACKSPACE -> KeyType.Backspace
        KeyCode.SPACE -> KeyType.Space
        else -> null
    }

    override fun next(): InputEvent? = eventQueue.poll()
}