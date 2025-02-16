package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagInt(val value: Int): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeInt(value)
    }

    override fun toSNBT(): String {
        return "$value"
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagInt {
            return NbtTagInt(buffer.readInt())
        }
    }
}