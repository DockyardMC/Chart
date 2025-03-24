package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf

class BinaryTagDouble(val value: Double): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeDouble(value)
    }

    override fun toSNBT(): String {
        return "${value}D"
    }

    companion object: BinaryTagReadable<BinaryTagDouble> {
        override fun read(buffer: ByteBuf): BinaryTagDouble {
            return BinaryTagDouble(buffer.readDouble())
        }
    }
}