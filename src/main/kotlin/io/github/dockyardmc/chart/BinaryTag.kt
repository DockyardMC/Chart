package io.github.dockyardmc.chart

import io.github.dockyardmc.chart.extensions.read
import io.netty.buffer.ByteBuf
import kotlin.reflect.KClass

interface BinaryTag {

    fun write(buffer: ByteBuf)

    fun toSNBT(): String

    companion object {

        fun readNamedBinaryTag(buffer: ByteBuf): Pair<String, BinaryTag> {
            return BinaryTagWriterUtil.readFullyFormattedTag(buffer)
        }

        fun readBinaryTag(buffer: ByteBuf): BinaryTag {
            return readNamedBinaryTag(buffer).second
        }

        fun readRaw(buffer: ByteBuf, id: Int): BinaryTag {
            val type = NbtTagRegistry.getFromId(id)
            return type.read(buffer)
        }

        fun writeNamedBinaryTag(buffer: ByteBuf, name: String, tag: BinaryTag) {
            BinaryTagWriterUtil.writeFullyFormattedTag(buffer, name, tag)
        }

        fun writeRawBinaryTag(buffer: ByteBuf, tag: BinaryTag) {
            tag.write(buffer)
        }
    }
}