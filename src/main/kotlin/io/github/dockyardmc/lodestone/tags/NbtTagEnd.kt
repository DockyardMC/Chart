package io.github.dockyardmc.lodestone.tags

import io.github.dockyardmc.lodestone.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagEnd: BinaryTag {

    override fun write(buffer: ByteBuf) {}

    override fun toSNBT(): String {
        return ""
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagEnd {
            return NbtTagEnd()
        }
    }

}