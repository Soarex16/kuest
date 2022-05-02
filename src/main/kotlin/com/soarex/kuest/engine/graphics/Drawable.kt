package com.soarex.kuest.engine.graphics

import com.soarex.kuest.engine.Entity

interface Drawable : Entity {
    fun draw(renderer: GraphicsRenderer)
}