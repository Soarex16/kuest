package com.soarex.kuest.engine.input

import com.soarex.kuest.engine.event.InputEvent

interface InputManager {
    fun next(): InputEvent?
}