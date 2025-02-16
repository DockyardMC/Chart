package io.github.dockyardmc.chart

import io.netty.buffer.ByteBuf

interface NbtNetworkReadable <T: BinaryTag> {
    fun read(buffer: ByteBuf): T
}

