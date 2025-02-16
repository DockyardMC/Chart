package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagLong(val value: Long): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeLong(value)
    }

    override fun toSNBT(): String {
        return "${value}L"
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagLong {
            return NbtTagLong(buffer.readLong())
        }
    }

}