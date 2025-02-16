package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.NbtNetworkReadable
import io.netty.buffer.ByteBuf

class BinaryTagEnd: BinaryTag {

    override fun write(buffer: ByteBuf) {}

    override fun toSNBT(): String {
        return ""
    }

    companion object: NbtNetworkReadable<BinaryTagEnd> {
        override fun read(buffer: ByteBuf): BinaryTagEnd {
            return BinaryTagEnd()
        }
    }

}