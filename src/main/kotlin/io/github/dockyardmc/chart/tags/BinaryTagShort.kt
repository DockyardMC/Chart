package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf

class BinaryTagShort(val value: Short): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeShort(value.toInt())
    }

    companion object: BinaryTagReadable<BinaryTagShort> {

        override fun read(buffer: ByteBuf): BinaryTagShort {
            return BinaryTagShort(buffer.readShort())
        }

    }

    override fun toSNBT(): String {
        return "${value}S"
    }

}