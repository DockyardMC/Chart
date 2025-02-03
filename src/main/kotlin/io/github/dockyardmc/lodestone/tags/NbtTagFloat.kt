package io.github.dockyardmc.lodestone.tags

import io.github.dockyardmc.lodestone.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagFloat(val value: Float): BinaryTag {

    override fun write(buffer: ByteBuf) {
        buffer.writeFloat(value)
    }

    override fun toSNBT(): String {
        return "${value}F"
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagFloat {
            return NbtTagFloat(buffer.readFloat())
        }
    }
}