package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf

class BinaryTagInt(val value: Int): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeInt(value)
    }

    override fun toSNBT(): String {
        return "$value"
    }

    companion object: BinaryTagReadable<BinaryTagInt> {
        override fun read(buffer: ByteBuf): BinaryTagInt {
            return BinaryTagInt(buffer.readInt())
        }
    }
}