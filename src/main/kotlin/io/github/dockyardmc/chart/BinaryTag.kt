package io.github.dockyardmc.chart

import io.netty.buffer.ByteBuf

interface BinaryTag {

    fun write(buffer: ByteBuf)

    fun toSNBT(): String
}