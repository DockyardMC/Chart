package io.github.dockyardmc.lodestone.tags

import io.github.dockyardmc.lodestone.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagShort(val value: Short): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeShort(value.toInt())
    }

    override fun toSNBT(): String {
        return "${value}S"
    }

}