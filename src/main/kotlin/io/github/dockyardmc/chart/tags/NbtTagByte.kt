package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.netty.buffer.ByteBuf

class NbtTagByte(val value: Byte): BinaryTag {

    constructor(value: Boolean): this(if(value) 1 else 0)

    fun toBoolean(): Boolean = value != 0.toByte()

    override fun write(buffer: ByteBuf) {
        buffer.writeByte(value.toInt())
    }

    override fun toSNBT(): String {
        return "${value}B"
    }

    companion object {
        val ONE = NbtTagByte(1)
        val ZERO = NbtTagByte(1)

        fun read(buffer: ByteBuf): NbtTagByte {
            return NbtTagByte(buffer.readByte())
        }
    }
}