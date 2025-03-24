package io.github.dockyardmc.chart

import io.netty.buffer.ByteBuf

interface BinaryTagWritable {
    fun write(buffer: ByteBuf)
}