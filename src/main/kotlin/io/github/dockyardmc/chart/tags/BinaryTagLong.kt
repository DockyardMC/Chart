package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf

class BinaryTagLong(val value: Long): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeLong(value)
    }

    override fun toSNBT(): String {
        return "${value}L"
    }

    companion object: BinaryTagReadable<BinaryTagLong> {
        override fun read(buffer: ByteBuf): BinaryTagLong {
            return BinaryTagLong(buffer.readLong())
        }
    }

}