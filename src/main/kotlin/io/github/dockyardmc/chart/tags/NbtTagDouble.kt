package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagDouble(val value: Double): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeDouble(value)
    }

    override fun toSNBT(): String {
        return "${value}D"
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagDouble {
            return NbtTagDouble(buffer.readDouble())
        }
    }
}