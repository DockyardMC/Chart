package io.github.dockyardmc.chart

import io.netty.buffer.ByteBuf

interface BinaryTagReadable <T: BinaryTag> {
    fun read(buffer: ByteBuf): T
}