package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagFloat(val value: Float): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeFloat(value)
    }

    override fun toSNBT(): String {
        return "${value}F"
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagFloat {
            return NbtTagFloat(buffer.readFloat())
        }
    }
}