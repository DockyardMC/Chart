package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled

class BinaryTagByteArray(val value: ByteBuf): BinaryTag, Iterable<Byte> {

    constructor(value: ByteArray): this(Unpooled.copiedBuffer(value))

    override fun write(buffer: ByteBuf) {
        buffer.writeInt(value.readableBytes())
        buffer.writeBytes(value.copy())
    }

    operator fun get(index: Int) = value.getByte(index)

    override fun toSNBT(): String {
        val list = value.copy().array().joinToString(",") { "${it}B" }
        return "[B;$list]"
    }

    override fun toString(): String {
        return toSNBT()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BinaryTagByteArray

        return value.copy().array() contentEquals other.value.copy().array()
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun iterator(): Iterator<Byte> {
        return value.copy().array().iterator()
    }

    companion object: BinaryTagReadable<BinaryTagByteArray> {
        override fun read(buffer: ByteBuf): BinaryTagByteArray {
            val size = buffer.readInt()
            return BinaryTagByteArray(buffer.readBytes(size))
        }
    }
}