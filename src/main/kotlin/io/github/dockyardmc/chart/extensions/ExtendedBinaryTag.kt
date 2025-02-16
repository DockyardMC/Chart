package io.github.dockyardmc.chart.extensions

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.NbtNetworkReadable
import io.netty.buffer.ByteBuf
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject

fun KClass<out BinaryTag>.read(buffer: ByteBuf): BinaryTag {
    val companionObject =
        this.companionObject ?: throw IllegalStateException("${this.simpleName} does not have a companion object!")
    if (companionObject !is NbtNetworkReadable<out BinaryTag>) throw IllegalStateException("companion object is not NbtNetworkReadable!")

    return companionObject.read(buffer)

}