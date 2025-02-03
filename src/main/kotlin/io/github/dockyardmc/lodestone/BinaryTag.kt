package io.github.dockyardmc.lodestone

import io.netty.buffer.ByteBuf

interface BinaryTag {

    fun write(buffer: ByteBuf)

    fun toSNBT(): String
}