package io.github.dockyardmc.lodestone.tags

import io.github.dockyardmc.lodestone.BinaryTag
import io.github.dockyardmc.lodestone.NbtTagRegistry
import io.netty.buffer.ByteBuf

class NbtTagList<T: BinaryTag>(val values: Collection<T>): BinaryTag {

    override fun write(buffer: ByteBuf) {
        val id = NbtTagRegistry.getFromClass(values.first()::class)

        buffer.writeByte(id)
        buffer.writeInt(values.size)
        values.forEach { tag ->
            tag.write(buffer)
        }
    }

    override fun toSNBT(): String {
        return "[${values.joinToString(",") { tag -> tag.toSNBT() }}]"
    }
}