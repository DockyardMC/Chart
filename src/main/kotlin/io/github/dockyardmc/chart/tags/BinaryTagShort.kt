package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.NbtNetworkReadable
import io.netty.buffer.ByteBuf

class BinaryTagShort(val value: Short): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeShort(value.toInt())
    }

    override fun toSNBT(): String {
        return "${value}S"
    }

    companion object: NbtNetworkReadable<BinaryTagShort> {

        override fun read(buffer: ByteBuf): BinaryTagShort {
            return BinaryTagShort(buffer.readShort())
        }

    }

}