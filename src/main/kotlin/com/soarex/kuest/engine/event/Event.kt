package com.soarex.kuest.engine.event

/**
 * Root interface for all the events
 */
interface Event

interface InputEvent : Event

/**
 * This type of action fired when key pressed
 */
data class KeyPressedEvent(val key: KeyType) : InputEvent