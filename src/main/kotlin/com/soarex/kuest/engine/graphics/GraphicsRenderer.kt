package com.soarex.kuest.engine.graphics

import com.soarex.kuest.engine.Entity

interface GraphicsRenderer {
    fun paint(sceneRoot: Entity)
}