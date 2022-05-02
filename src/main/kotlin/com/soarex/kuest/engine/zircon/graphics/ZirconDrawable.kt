package com.soarex.kuest.engine.zircon.graphics

import com.soarex.kuest.engine.graphics.Drawable
import com.soarex.kuest.engine.graphics.GraphicsRenderer
import com.soarex.kuest.engine.graphics.UnsupportedRendererException

interface ZirconDrawable : Drawable {
    fun draw(renderer: ZirconRenderer)

    // Я не придумал как лучше это сделать
    override fun draw(renderer: GraphicsRenderer) {
        if (renderer !is ZirconRenderer) {
            throw UnsupportedRendererException()
        }

        draw(renderer)
    }
}