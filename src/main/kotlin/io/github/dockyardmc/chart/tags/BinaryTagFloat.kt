package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf

class BinaryTagFloat(val value: Float): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeFloat(value)
    }

    override fun toSNBT(): String {
        return "${value}F"
    }

    companion object: BinaryTagReadable<BinaryTagFloat> {
        override fun read(buffer: ByteBuf): BinaryTagFloat {
            return BinaryTagFloat(buffer.readFloat())
        }
    }
}