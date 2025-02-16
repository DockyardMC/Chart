package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagEnd: BinaryTag {

    override fun write(buffer: ByteBuf) {}

    override fun toSNBT(): String {
        return ""
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagEnd {
            return NbtTagEnd()
        }
    }

}